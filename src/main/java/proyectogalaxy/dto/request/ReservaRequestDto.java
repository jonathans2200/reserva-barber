package proyectogalaxy.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaRequestDto {

    private Long clienteId;
    private Long barberoId;
    private LocalDate fecha;
    private LocalTime hora;

    private List<Long> servicios;

    public ReservaRequestDto() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getBarberoId() {
        return barberoId;
    }

    public void setBarberoId(Long barberoId) {
        this.barberoId = barberoId;
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

    public List<Long> getServicios() {
        return servicios;
    }

    public void setServicios(List<Long> servicios) {
        this.servicios = servicios;
    }


}
