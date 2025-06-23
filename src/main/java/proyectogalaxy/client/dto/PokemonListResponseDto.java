package proyectogalaxy.client.dto;

import java.util.List;
import java.util.Optional;

public class PokemonListResponseDto {

    private List<PokemonEntry> results;

    public PokemonListResponseDto(List<PokemonEntry> results) {
        this.results = results;
    }

    public List<PokemonEntry> getResults() {
        return results;
    }

    public void setResults(List<PokemonEntry> results) {
        this.results = results;
    }
}
