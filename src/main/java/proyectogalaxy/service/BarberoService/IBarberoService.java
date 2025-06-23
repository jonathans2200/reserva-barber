package proyectogalaxy.service.BarberoService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectogalaxy.Repository.BarberoRepository;
import proyectogalaxy.dto.request.BarberoRequestDto;
import proyectogalaxy.dto.response.BarberoResponseDto;
import proyectogalaxy.entity.Barbero;
import proyectogalaxy.entity.Cliente;
import proyectogalaxy.mapper.BarberoMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class IBarberoService implements BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;
    @Autowired
    private BarberoMapper barberoMapper;

    @Override
    @Transactional
    public BarberoResponseDto createBarbero(BarberoRequestDto barbero) {
        Barbero barberoEntity = barberoMapper.toEntity(barbero);

        return barberoMapper.toDto(barberoRepository.save(barberoEntity));
    }

    @Override
    public BarberoResponseDto getBarberoById(Long barberoId) {
        return barberoRepository
                .findById(barberoId)
                .map(barberoMapper::toDto)
                .orElseThrow(
                        () -> new RuntimeException("Barbero no encontrado"));
    }

    @Override
    public List<BarberoResponseDto> getAllBarberos() {
        return barberoRepository.findAll().stream().map(barberoMapper::toDto).toList();
    }

    @Override
    public BarberoResponseDto updateBarbero(BarberoRequestDto barbero, Long barberoId) {
        Barbero barberoUpdate = barberoRepository.findById(barberoId).orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
        barberoUpdate.setNombre(barbero.getNombre());
        return barberoMapper.toDto(barberoRepository.save(barberoUpdate));
    }

    @Override
    public void deleteBarbero(Long id) {
        barberoRepository.deleteById(id);

    }
}
