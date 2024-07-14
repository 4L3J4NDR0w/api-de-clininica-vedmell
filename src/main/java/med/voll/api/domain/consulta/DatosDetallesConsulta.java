package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DatosDetallesConsulta(Long id, Long IdPaciente, Long IdMedico, LocalDateTime fecha) {
}
