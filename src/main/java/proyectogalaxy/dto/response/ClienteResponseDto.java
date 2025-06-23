package proyectogalaxy.dto.response;

import jakarta.persistence.OneToMany;
import proyectogalaxy.entity.Reserva;

import java.util.List;

public class ClienteResponseDto {
    private Long id;
    private String nombre;
    private String telefono;
    private List<ReservaResponseDto> reservas;

    public ClienteResponseDto() {
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

    public List<ReservaResponseDto> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaResponseDto> reservas) {
        this.reservas = reservas;
    }
}
