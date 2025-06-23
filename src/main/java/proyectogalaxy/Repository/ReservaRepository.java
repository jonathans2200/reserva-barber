package proyectogalaxy.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectogalaxy.entity.Reserva;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByBarberoIdAndFecha(Long barberoId, LocalDate fecha);

    List<Reserva> findByBarberoId(Long barberoId);

    @Query("""
             SELECT r FROM Reserva r
                WHERE ( LOWER(r.cliente.nombre) LIKE LOWER(CONCAT('%', :cliente, '%')))
                  OR ( LOWER(r.barbero.nombre) LIKE LOWER(CONCAT('%', :barbero, '%')))
            """)
    Page<Reserva> buscarConFiltros(
            @Param("cliente") String cliente,
            @Param("barbero") String barbero,

            Pageable pageable
    );

}
