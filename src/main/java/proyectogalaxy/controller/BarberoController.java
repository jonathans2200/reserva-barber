package proyectogalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectogalaxy.dto.request.BarberoRequestDto;
import proyectogalaxy.dto.request.ClienteRequestDto;
import proyectogalaxy.dto.response.BarberoResponseDto;
import proyectogalaxy.dto.response.ClienteResponseDto;
import proyectogalaxy.service.BarberoService.BarberoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/barbero")
public class BarberoController {


    @Autowired
    private BarberoService barberoService;

    @PostMapping
    public ResponseEntity<BarberoResponseDto> crearCliente(@RequestBody BarberoRequestDto barbero) {
        try {
            BarberoResponseDto barberoSaved = barberoService.createBarbero(barbero);
            return new ResponseEntity<>(barberoSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<BarberoResponseDto> getBarberoId(@PathVariable Long id) {

        BarberoResponseDto barbero = barberoService.getBarberoById(id);
        return ResponseEntity.ok(barbero);

    }


    @GetMapping
    public ResponseEntity<List<BarberoResponseDto>> getAllBarberos() {
        try {
            List<BarberoResponseDto> barberos = barberoService.getAllBarberos();
            if (barberos.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(barberos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBarbero(@PathVariable Long id, @RequestBody BarberoRequestDto barbero) {
        try {
            BarberoResponseDto barberoNuevo = barberoService.updateBarbero(barbero, id);

            if (barberoNuevo == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(barberoNuevo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBarbero(@PathVariable Long id) {
        try {
            barberoService.deleteBarbero(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
