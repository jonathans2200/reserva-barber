package proyectogalaxy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectogalaxy.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
