package med.voll.api.domain.consulta;

import jakarta.xml.bind.ValidationException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DatosAgendarConsulta datosAgendarConsulta)  {
        if (pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }

        if (datosAgendarConsulta.IdMedico()!=null && medicoRepository.existsById(datosAgendarConsulta.IdMedico())){
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");

        }
        var paciente = pacienteRepository.findById(datosAgendarConsulta.idPaciente()).get();
        var medico = escogerMedico(datosAgendarConsulta);

        var consulta = new Consulta(null,medico,paciente,datosAgendarConsulta.fecha());


        consultaRepository.save(consulta);
    }

    private Medico escogerMedico(DatosAgendarConsulta datosAgendarConsulta) {
        if (datosAgendarConsulta.IdMedico()!=null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.IdMedico());
        }
        if (datosAgendarConsulta.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
        }
        return medicoRepository.selecionarMedicoConEspecialidadEnFecha(datosAgendarConsulta.especialidad(), datosAgendarConsulta.fecha());
    }
}
