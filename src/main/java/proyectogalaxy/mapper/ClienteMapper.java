package proyectogalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proyectogalaxy.dto.request.ClienteRequestDto;
import proyectogalaxy.dto.response.ClienteResponseDto;
import proyectogalaxy.entity.Cliente;

@Mapper(componentModel = "spring", uses = {ReservaMapper.class})
public interface ClienteMapper {
    @Mapping(target = "reservas", ignore = true)
    ClienteResponseDto toDto(Cliente cliente);

    Cliente toEntity(ClienteRequestDto clienteRequestDto);
}
