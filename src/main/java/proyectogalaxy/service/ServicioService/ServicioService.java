package proyectogalaxy.service.ServicioService;

import proyectogalaxy.dto.request.ServicioRequestDto;
import proyectogalaxy.dto.response.ServicioResponseDto;

import java.util.List;
import java.util.Optional;

public interface ServicioService {


    ServicioResponseDto crearServicio(ServicioRequestDto servicioRequestDto);

    ServicioResponseDto actualizarServicio(Long id, ServicioRequestDto servicioRequestDto);

    void eliminarServicio(Long id);

    Optional<ServicioResponseDto> obtenerServicioById(Long id);

    List<ServicioResponseDto> listarServicios();

}
