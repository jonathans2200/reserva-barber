package proyectogalaxy.service.ClienteService;

import proyectogalaxy.dto.request.ClienteRequestDto;
import proyectogalaxy.dto.response.ClienteResponseDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    ClienteResponseDto createCliente(ClienteRequestDto cliente);

    List<ClienteResponseDto> getAllClientes();

    ClienteResponseDto getClienteById(Long id);

    ClienteResponseDto updateCliente(Long id, ClienteRequestDto cliente);

    void deleteCliente(Long id);

}
