package proyectogalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import proyectogalaxy.dto.request.BarberoRequestDto;
import proyectogalaxy.dto.response.BarberoResponseDto;
import proyectogalaxy.entity.Barbero;

@Mapper(componentModel = "spring")
public interface BarberoMapper {

    BarberoResponseDto toDto(Barbero barbero);

    Barbero toEntity(BarberoRequestDto barberoRequestDto);
}
