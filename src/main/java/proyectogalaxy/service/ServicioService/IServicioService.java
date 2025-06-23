package proyectogalaxy.service.ServicioService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectogalaxy.Repository.BarberoRepository;
import proyectogalaxy.Repository.ServicioRepository;
import proyectogalaxy.dto.request.ServicioRequestDto;
import proyectogalaxy.dto.response.ServicioResponseDto;
import proyectogalaxy.entity.Servicio;
import proyectogalaxy.mapper.ServicioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class IServicioService implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private ServicioMapper servicioMapper;
    @Autowired
    private BarberoRepository barberoRepository;

    @Override
    @Transactional
    public ServicioResponseDto crearServicio(ServicioRequestDto servicioRequestDto) {
        Servicio servicio = servicioMapper.toEntity(servicioRequestDto);
        return servicioMapper.toDto(servicioRepository.save(servicio));
    }

    @Override
    public ServicioResponseDto actualizarServicio(Long id, ServicioRequestDto servicioRequestDto) {
        Servicio servicioUpdater = servicioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encuentra el servicio"));
        servicioUpdater.setNombre(servicioRequestDto.getNombre());
        servicioUpdater.setDuracionMinutos(servicioRequestDto.getDuracionMinutos());
        servicioUpdater.setPrecio(servicioRequestDto.getPrecio());
        return servicioMapper.toDto(servicioRepository.save(servicioUpdater));
    }

    @Override
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Optional<ServicioResponseDto> obtenerServicioById(Long id) {
        return servicioRepository.findById(id).map(servicioMapper::toDto);
    }

    @Override
    public List<ServicioResponseDto> listarServicios() {
        return servicioRepository.findAll().stream().map(servicioMapper::toDto).toList();
    }
}
