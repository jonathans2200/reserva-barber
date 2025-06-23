package proyectogalaxy.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String telefono, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.reservas = reservas;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
