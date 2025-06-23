package proyectogalaxy.service.BarberoService;

import proyectogalaxy.dto.request.BarberoRequestDto;
import proyectogalaxy.dto.response.BarberoResponseDto;

import java.util.List;
import java.util.Optional;

public interface BarberoService {


    BarberoResponseDto createBarbero(BarberoRequestDto barbero);

    BarberoResponseDto getBarberoById(Long barberoId);

    List<BarberoResponseDto> getAllBarberos();

    BarberoResponseDto updateBarbero(BarberoRequestDto barbero, Long barberoId);

    void deleteBarbero(Long id);

}
