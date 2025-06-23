package proyectogalaxy.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaResponseDto {

    private Long id;
    private ClienteResponseDto cliente;
    private BarberoResponseDto barbero;
    private LocalDate fecha;
    private LocalTime hora;
    private LocalTime horaFin;
    private BigDecimal total;

    private List<DetalleReservaResponseDto> servicios;

    public ReservaResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResponseDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }

    public BarberoResponseDto getBarbero() {
        return barbero;
    }

    public void setBarbero(BarberoResponseDto barbero) {
        this.barbero = barbero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<DetalleReservaResponseDto> getServicios() {
        return servicios;
    }

    public void setServicios(List<DetalleReservaResponseDto> servicios) {
        this.servicios = servicios;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
