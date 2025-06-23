package proyectogalaxy.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class IntervaloHorarioResponseDto {

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;

    public IntervaloHorarioResponseDto() {
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
