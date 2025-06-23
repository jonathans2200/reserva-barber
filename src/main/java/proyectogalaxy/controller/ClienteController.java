package proyectogalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectogalaxy.dto.request.ClienteRequestDto;
import proyectogalaxy.dto.response.BarberoResponseDto;
import proyectogalaxy.dto.response.ClienteResponseDto;
import proyectogalaxy.service.ClienteService.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteResponseDto> crearCliente(@RequestBody ClienteRequestDto clienteRequestDto) {
        try {
            ClienteResponseDto clienteNuevo = clienteService.createCliente(clienteRequestDto);
            return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable Long id) {
        ClienteResponseDto cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
        try {
            List<ClienteResponseDto> clientes = clienteService.getAllClientes();
            if (clientes.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDto clienteRequestDto) {
        try {
            ClienteResponseDto clienteNuevo = clienteService.updateCliente(id, clienteRequestDto);
            if (clienteNuevo == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clienteNuevo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<ClienteResponseDto> eliminarCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
