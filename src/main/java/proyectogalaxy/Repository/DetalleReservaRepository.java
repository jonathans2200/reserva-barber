package proyectogalaxy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectogalaxy.entity.DetalleReserva;
@Repository
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Long> {
}
