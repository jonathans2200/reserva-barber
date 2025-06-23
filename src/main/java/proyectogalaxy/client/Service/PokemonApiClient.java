package proyectogalaxy.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import proyectogalaxy.client.dto.PokemonListResponseDto;
import proyectogalaxy.client.dto.PokemonResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class PokemonApiClient {

    @Autowired
    RestTemplate template;

    private static final Logger LOG = LoggerFactory.getLogger(PokemonApiClient.class);
    private final String BASE_URL = "https://pokeapi.co/api/v2";

    public List<PokemonResponseDto> listarPrimeros100() {
        String url = BASE_URL + "/pokemon?limit=100";
        PokemonListResponseDto response = template.getForObject(url, PokemonListResponseDto.class);

        if (response == null || response.getResults() == null) return List.of();

        return response.getResults().stream()

                .map(p -> {
                    PokemonResponseDto dto = new PokemonResponseDto();
                    dto.setNombre(p.getName());
                    dto.setUrl(p.getUrl());
                    return dto;
                })
                .toList();
    }

}
