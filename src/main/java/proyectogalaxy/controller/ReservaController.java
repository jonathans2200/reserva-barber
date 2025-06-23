package proyectogalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectogalaxy.dto.request.ReservaRequestDto;
import proyectogalaxy.dto.response.HorarioBarberoResponseDto;
import proyectogalaxy.dto.response.PaginatedResponseDto;
import proyectogalaxy.dto.response.ReservaResponseDto;
import proyectogalaxy.service.ReservaService.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDto> crear(@RequestBody ReservaRequestDto dto) {
        return ResponseEntity.ok(reservaService.crearReserva(dto));
    }

    @GetMapping("/barbero/{barberoId}/horarios")
    public ResponseEntity<HorarioBarberoResponseDto> horariosOcupados(@PathVariable Long barberoId, @RequestParam LocalDate fecha) {
        return ResponseEntity.ok(reservaService.getHorarioBarbero(barberoId, fecha));
    }


    @GetMapping("/barbero/{barberoId}")
    public ResponseEntity<HorarioBarberoResponseDto> horariosBarbero(@PathVariable Long barberoId) {
        return ResponseEntity.ok(reservaService.getHorarioBarberoporId(barberoId));
    }


    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ReservaResponseDto>> getReservas(@RequestParam(defaultValue = "") String cliente, @RequestParam(defaultValue = "") String barbero, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(reservaService.getReservas(cliente, barbero, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> obtenerReserva(@PathVariable Long id) {
        try {
            Optional<ReservaResponseDto> reserva = reservaService.getReserva(id);
            return reserva.map(r -> new ResponseEntity<>(r, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Long id, @RequestBody ReservaRequestDto dto) {
        try {
            ReservaResponseDto reserva = reservaService.actualizarReserva(id, dto);
            if (reserva != null) {
                return new ResponseEntity<>(reserva, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
