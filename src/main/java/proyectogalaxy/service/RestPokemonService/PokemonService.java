package proyectogalaxy.service.RestPokemonService;

import proyectogalaxy.client.dto.PokemonResponseDto;

import java.util.List;

public interface PokemonService {

    List<PokemonResponseDto> getPokemons();
}
