package proyectogalaxy.mapper;

import org.mapstruct.Mapper;
import proyectogalaxy.dto.request.ServicioRequestDto;
import proyectogalaxy.dto.response.ServicioResponseDto;
import proyectogalaxy.entity.Servicio;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    ServicioResponseDto toDto(Servicio servicio);

    Servicio toEntity(ServicioRequestDto servicioRequestDto);
}
