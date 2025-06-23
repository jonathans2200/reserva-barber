package proyectogalaxy.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectogalaxy.Repository.ClienteRepository;
import proyectogalaxy.dto.request.ClienteRequestDto;
import proyectogalaxy.dto.response.ClienteResponseDto;
import proyectogalaxy.entity.Cliente;
import proyectogalaxy.mapper.ClienteMapper;

import java.util.List;
import java.util.Optional;

@Service
public class IClienteService implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteResponseDto createCliente(ClienteRequestDto cliente) {
        Cliente clienteNuevo = clienteMapper.toEntity(cliente);
        return clienteMapper.toDto(clienteRepository.save(clienteNuevo));

    }

    @Override
    public List<ClienteResponseDto> getAllClientes() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDto).toList();
    }

    @Override
    public ClienteResponseDto getClienteById(Long id) {
        return clienteRepository.
                findById(id)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public ClienteResponseDto updateCliente(Long id, ClienteRequestDto cliente) {
        Cliente clienteUpdate = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteUpdate.setNombre(cliente.getNombre());
        clienteUpdate.setTelefono(cliente.getTelefono());
        return clienteMapper.toDto(clienteRepository.save(clienteUpdate));
    }

    ;


    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
