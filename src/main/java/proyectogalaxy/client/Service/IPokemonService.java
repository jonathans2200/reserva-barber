package proyectogalaxy.client.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectogalaxy.client.config.PokemonApiClient;
import proyectogalaxy.client.dto.PokemonResponseDto;

import java.util.List;

@Service
public class IPokemonService implements PokemonService {

    @Autowired
    private PokemonApiClient pokemonApiClient;

    @Override
    public List<PokemonResponseDto> getPokemons() {
        return pokemonApiClient.listarPrimeros100().stream().map(p -> new PokemonResponseDto(p.getNombre(), p.getUrl()

        )).toList();
    }
}
