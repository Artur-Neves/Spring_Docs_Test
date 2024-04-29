package med.voll.api.domain.appointment.Validacoes;

import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidateInactiveDoctor implements AppointmentValidations {
	@Autowired
	private DoctorRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
		if(dto.idDoctor()!=null && !repository.existsByIdAndActiveTrue(dto.idDoctor())) {
			throw new AppointmentValidationException("Este Doutor não esta ativo, logo não pode realizar o agendamento");
		}
	}

}
