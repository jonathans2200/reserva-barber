package proyectogalaxy.dto.response;

public class DetalleReservaResponseDto {
    private Long id;
    private ServicioResponseDto servicio;

    public DetalleReservaResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicioResponseDto getServicio() {
        return servicio;
    }

    public void setServicio(ServicioResponseDto servicio) {
        this.servicio = servicio;
    }
}
