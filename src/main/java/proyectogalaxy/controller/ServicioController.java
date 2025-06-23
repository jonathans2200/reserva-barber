package proyectogalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectogalaxy.dto.request.ServicioRequestDto;
import proyectogalaxy.dto.response.ServicioResponseDto;
import proyectogalaxy.exeptions.ApiError;
import proyectogalaxy.service.ServicioService.ServicioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;


    @PostMapping
    public ResponseEntity<ServicioResponseDto> crearServicio(ServicioRequestDto servicioRequestDto) {
        try {
            ServicioResponseDto servicioResponseDto = servicioService.crearServicio(servicioRequestDto);
            return new ResponseEntity<>(servicioResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDto>> listarServicios() {
        try {
            List<ServicioResponseDto> servicios = servicioService.listarServicios();
            if (servicios != null) {
                return new ResponseEntity<>(servicios, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerServicioPorId(@PathVariable("id") Long id) {

        Optional<ServicioResponseDto> servicio = servicioService.obtenerServicioById(id);

        if (servicio.isPresent()) {
            return ResponseEntity.ok(servicio.get());
        } else {
            ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Barbero no encontrado", HttpStatus.NOT_FOUND.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarServicio(@PathVariable("id") Long id, @RequestBody ServicioRequestDto servicio) {
        try {

            ServicioResponseDto servicioResponseDto = servicioService.actualizarServicio(id, servicio);
            if (servicioResponseDto != null) {
                return new ResponseEntity<>(servicioResponseDto, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarServicio(@PathVariable("id") Long id) {
        try {
            servicioService.eliminarServicio(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}