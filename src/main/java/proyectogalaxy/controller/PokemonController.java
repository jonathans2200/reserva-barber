package proyectogalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectogalaxy.client.dto.PokemonResponseDto;
import proyectogalaxy.service.RestPokemonService.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/v2/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;


    @GetMapping
    public ResponseEntity<List<PokemonResponseDto>> getPokemons() {
        try {
            List<PokemonResponseDto> pokemons = pokemonService.getPokemons();
            if (pokemons.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pokemons);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
