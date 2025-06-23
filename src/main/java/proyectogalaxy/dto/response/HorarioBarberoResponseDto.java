package proyectogalaxy.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class HorarioBarberoResponseDto {

    private Long barberoId;
    private String barberoNombre;
    private List<IntervaloHorarioResponseDto> horariosOcupados;
    private List<LocalTime> horariosDisponibles;


    public HorarioBarberoResponseDto() {
    }

    public Long getBarberoId() {
        return barberoId;
    }

    public void setBarberoId(Long barberoId) {
        this.barberoId = barberoId;
    }

    public String getBarberoNombre() {
        return barberoNombre;
    }

    public void setBarberoNombre(String barberoNombre) {
        this.barberoNombre = barberoNombre;
    }

    public List<IntervaloHorarioResponseDto> getHorariosOcupados() {
        return horariosOcupados;
    }

    public void setHorariosOcupados(List<IntervaloHorarioResponseDto> horariosOcupados) {
        this.horariosOcupados = horariosOcupados;
    }

    public List<LocalTime> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public void setHorariosDisponibles(List<LocalTime> horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }
}
