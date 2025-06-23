package proyectogalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proyectogalaxy.dto.response.ReservaResponseDto;
import proyectogalaxy.entity.Reserva;

@Mapper(componentModel = "spring", uses = {ServicioMapper.class, ClienteMapper.class, BarberoMapper.class})
public interface ReservaMapper {
    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "barbero", source = "barbero")

    @Mapping(target = "servicios", source = "detalles")
    ReservaResponseDto toDto(Reserva reserva);

    Reserva toEntity(ReservaResponseDto reservaRequestDto);

}
