package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(Long id,
                                   @NotNull Long idPaciente,
                                   Long IdMedico,
                                   @NotNull@Future LocalDateTime fecha,
                                   Especialidad especialidad){
}
