package proyectogalaxy.service.ReservaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyectogalaxy.dto.request.ReservaRequestDto;
import proyectogalaxy.dto.response.HorarioBarberoResponseDto;
import proyectogalaxy.dto.response.PaginatedResponseDto;
import proyectogalaxy.dto.response.ReservaResponseDto;
import proyectogalaxy.entity.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaService {


    ReservaResponseDto crearReserva(ReservaRequestDto reserva);

    HorarioBarberoResponseDto getHorarioBarbero(Long barberoId, LocalDate fecha);

    HorarioBarberoResponseDto getHorarioBarberoporId(Long id);


    PaginatedResponseDto<ReservaResponseDto> getReservas(String cliente, String barbero, int page, int size);

    Optional<ReservaResponseDto> getReserva(Long id);

    ReservaResponseDto actualizarReserva(Long id,
                                         ReservaRequestDto reserva);

}
