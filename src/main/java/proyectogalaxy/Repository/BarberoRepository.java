package proyectogalaxy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectogalaxy.entity.Barbero;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

}
