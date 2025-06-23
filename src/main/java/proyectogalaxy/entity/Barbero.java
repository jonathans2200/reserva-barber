package proyectogalaxy.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Barbero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaDescanso;
    @OneToMany(mappedBy = "barbero")
    private List<Reserva> reservas;


    public Barbero(Long id, String nombre, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.reservas = reservas;
    }

    public Barbero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public DayOfWeek getDiaDescanso() {
        return diaDescanso;
    }

    public void setDiaDescanso(DayOfWeek diaDescanso) {
        this.diaDescanso = diaDescanso;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
