package proyectogalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proyectogalaxy.dto.response.DetalleReservaResponseDto;
import proyectogalaxy.entity.DetalleReserva;

@Mapper(componentModel = "spring")
public interface DetalleReservaMapper {
    @Mapping(source = "servicio", target = "servicio")
    DetalleReservaResponseDto toDto(DetalleReserva detalle);

    DetalleReserva toEntity(DetalleReservaResponseDto dto);
}
