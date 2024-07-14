package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query(value = "SELECT * FROM Medico m WHERE m.activo = 1 AND m.especialidad = :especialidad AND m.id NOT IN (SELECT c.medico_id FROM Consulta c WHERE c.fecha = :fecha) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Medico selecionarMedicoConEspecialidadEnFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);
}
