package proyectogalaxy.dto.request;

import java.time.DayOfWeek;

public class BarberoRequestDto {
    private String nombre;
    private DayOfWeek diaDescanso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DayOfWeek getDiaDescanso() {
        return diaDescanso;
    }

    public void setDiaDescanso(DayOfWeek diaDescanso) {
        this.diaDescanso = diaDescanso;
    }

    public BarberoRequestDto() {
    }
}
