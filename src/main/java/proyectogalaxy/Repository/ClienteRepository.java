package proyectogalaxy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectogalaxy.entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
