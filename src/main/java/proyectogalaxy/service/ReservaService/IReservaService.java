package proyectogalaxy.service.ReservaService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import proyectogalaxy.Repository.BarberoRepository;
import proyectogalaxy.Repository.ClienteRepository;
import proyectogalaxy.Repository.ReservaRepository;
import proyectogalaxy.Repository.ServicioRepository;
import proyectogalaxy.dto.request.ReservaRequestDto;
import proyectogalaxy.dto.response.*;
import proyectogalaxy.entity.*;
import proyectogalaxy.mapper.ReservaMapper;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IReservaService implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private BarberoRepository barberoRepo;
    @Autowired
    private ServicioRepository servicioRepo;
    @Autowired
    private ReservaMapper reservaMapper;


    @Override
    @Transactional
    public ReservaResponseDto crearReserva(ReservaRequestDto reserva) {
        Cliente cliente = clienteRepo.findById(reserva.getClienteId()).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Barbero barbero = barberoRepo.findById(reserva.getBarberoId()).orElseThrow(() -> new IllegalArgumentException("Barbero no encontrado"));

        DayOfWeek diaReserva = reserva.getFecha().getDayOfWeek();
        if (barbero.getDiaDescanso() != null && diaReserva.equals(barbero.getDiaDescanso())) {
            throw new RuntimeException("El barbero descansa los " + barbero.getDiaDescanso());
        }
        List<Servicio> servicios = reserva.getServicios().stream().map(id -> servicioRepo.findById(id).orElseThrow()).toList();

        int duracionTotal = servicios.stream().mapToInt(Servicio::getDuracionMinutos).sum();
        BigDecimal total = servicios.stream().map(Servicio::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);
        LocalTime horaFin = reserva.getHora().plusMinutes(duracionTotal);
        List<Reserva> reservasDelDia = reservaRepo.findByBarberoIdAndFecha(barbero.getId(), reserva.getFecha());

        boolean ocupado = reservasDelDia.stream().anyMatch(r -> {
            LocalTime inicioExistente = r.getHora();
            LocalTime finExistente = r.getHoraFin();
            return !(horaFin.isBefore(inicioExistente) || reserva.getHora().isAfter(finExistente));
        });
        if (ocupado) throw new RuntimeException("El barbero tiene otra reserva en ese horario.");
        Reserva r = new Reserva();
        r.setCliente(cliente);
        r.setBarbero(barbero);
        r.setFecha(reserva.getFecha());
        r.setHora(reserva.getHora());
        r.setHoraFin(horaFin);
        r.setTotal(total);
        List<DetalleReserva> detalles = servicios.stream().map(s -> {
            DetalleReserva dr = new DetalleReserva();
            dr.setReserva(r);
            dr.setServicio(s);
            return dr;
        }).toList();
        r.setDetalles(detalles);
        return reservaMapper.toDto(reservaRepo.save(r));
    }

    @Override
    public HorarioBarberoResponseDto getHorarioBarbero(Long barberoId, LocalDate fecha) {
        Barbero barbero = barberoRepo.findById(barberoId).orElseThrow();


        if (barbero.getDiaDescanso() != null && fecha.getDayOfWeek().equals(barbero.getDiaDescanso())) {
            throw new RuntimeException("El barbero descansa los " + barbero.getDiaDescanso());
        }

        List<Reserva> reservas = reservaRepo.findByBarberoIdAndFecha(barberoId, fecha);

        // Horarios ocupados
        List<IntervaloHorarioResponseDto> intervalos = reservas.stream().map(r -> {
            IntervaloHorarioResponseDto intervalo = new IntervaloHorarioResponseDto();
            intervalo.setHoraInicio(r.getHora());
            intervalo.setHoraFin(r.getHoraFin());
            intervalo.setFecha(r.getFecha());
            return intervalo;
        }).toList();

        // Horarios disponibles
        List<LocalTime> disponibles = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(20, 0);

        while (horaInicio.isBefore(horaFin)) {
            final LocalTime bloqueInicio = horaInicio;
            final LocalTime bloqueFin = horaInicio.plusMinutes(30);

            boolean estaOcupado = reservas.stream().anyMatch(r -> !(bloqueFin.isBefore(r.getHora()) || bloqueInicio.isAfter(r.getHoraFin())));

            if (!estaOcupado) {
                disponibles.add(bloqueInicio);
            }

            horaInicio = horaInicio.plusMinutes(30);
        }

        HorarioBarberoResponseDto dto = new HorarioBarberoResponseDto();
        dto.setBarberoId(barberoId);
        dto.setBarberoNombre(barbero.getNombre());
        dto.setHorariosOcupados(intervalos);
        dto.setHorariosDisponibles(disponibles);

        return dto;
    }

    @Override
    public HorarioBarberoResponseDto getHorarioBarberoporId(Long id) {
        Barbero barbero = barberoRepo.findById(id).orElseThrow();
        List<Reserva> reservas = reservaRepo.findByBarberoId(id);

        List<IntervaloHorarioResponseDto> intervalos = reservas.stream().map(r -> {
            IntervaloHorarioResponseDto intervalo = new IntervaloHorarioResponseDto();
            intervalo.setHoraInicio(r.getHora());
            intervalo.setHoraFin(r.getHoraFin());
            intervalo.setFecha(r.getFecha());
            return intervalo;
        }).toList();

        HorarioBarberoResponseDto dto = new HorarioBarberoResponseDto();
        dto.setBarberoId(id);
        dto.setBarberoNombre(barbero.getNombre());
        dto.setHorariosOcupados(intervalos);


        return dto;
    }

    @Override
    public PaginatedResponseDto<ReservaResponseDto> getReservas(String cliente, String barbero, int page, int size) {
        if (cliente != null && cliente.isBlank()) cliente = null;
        if (barbero != null && barbero.isBlank()) barbero = null;
        Pageable pageable = PageRequest.of(page, size);
        Page<Reserva> pageResult;
        if (cliente == null && barbero == null) {
            pageResult = reservaRepo.findAll(pageable);
        } else {
            pageResult = reservaRepo.buscarConFiltros(cliente, barbero, pageable);
        }
        List<ReservaResponseDto> content = pageResult.getContent().stream().map(reservaMapper::toDto).toList();

        return new PaginatedResponseDto<>(content, pageResult.getTotalElements(), page, size);
    }

    @Override
    public Optional<ReservaResponseDto> getReserva(Long id) {
        return Optional.ofNullable(reservaRepo.findById(id).map(reservaMapper::toDto).orElseThrow(() -> new RuntimeException("No existe el reserva con el id: " + id)));
    }

    @Override
    public ReservaResponseDto actualizarReserva(Long id, ReservaRequestDto reserva) {

        Reserva r = reservaRepo.findById(id).orElseThrow(() -> new RuntimeException("No existe la reserva con el id: " + id));

        Cliente cliente = clienteRepo.findById(reserva.getClienteId()).orElseThrow(() -> new RuntimeException("No existe el cliente con id: " + reserva.getClienteId()));

        Barbero barbero = barberoRepo.findById(reserva.getBarberoId()).orElseThrow(() -> new RuntimeException("Barbero no encontrado con id: " + reserva.getBarberoId()));

        List<Servicio> servicios = reserva.getServicios().stream().map(idServicio -> servicioRepo.findById(idServicio).orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + idServicio))).toList();

        int duracionTotal = servicios.stream().mapToInt(Servicio::getDuracionMinutos).sum();

        BigDecimal total = servicios.stream().map(Servicio::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalTime horaFin = reserva.getHora().plusMinutes(duracionTotal);

        List<Reserva> reservasDelDia = reservaRepo.findByBarberoIdAndFecha(barbero.getId(), reserva.getFecha());
        boolean ocupado = reservasDelDia.stream().anyMatch(existing -> !existing.getId().equals(id) && !(horaFin.isBefore(existing.getHora()) || reserva.getHora().isAfter(existing.getHoraFin())));

        if (ocupado) {
            throw new RuntimeException("El barbero ya tiene otra reserva en ese horario.");
        }


        r.setCliente(cliente);
        r.setBarbero(barbero);
        r.setFecha(reserva.getFecha());
        r.setHora(reserva.getHora());
        r.setHoraFin(horaFin);
        r.setTotal(total);


        List<Long> nuevosIds = servicios.stream().map(Servicio::getId).toList();


        List<DetalleReserva> detallesActuales = r.getDetalles();
        List<DetalleReserva> detallesFiltrados = detallesActuales.stream().filter(detalle -> nuevosIds.contains(detalle.getServicio().getId())).toList();

        detallesActuales.clear();
        detallesActuales.addAll(detallesFiltrados);


        List<Long> idsYaAsignados = detallesFiltrados.stream().map(d -> d.getServicio().getId()).toList();

        List<DetalleReserva> nuevosDetalles = servicios.stream().filter(s -> !idsYaAsignados.contains(s.getId())).map(s -> {
            DetalleReserva dr = new DetalleReserva();
            dr.setReserva(r);
            dr.setServicio(s);
            return dr;
        }).toList();


        detallesActuales.addAll(nuevosDetalles);

        return reservaMapper.toDto(reservaRepo.save(r));
    }
}
