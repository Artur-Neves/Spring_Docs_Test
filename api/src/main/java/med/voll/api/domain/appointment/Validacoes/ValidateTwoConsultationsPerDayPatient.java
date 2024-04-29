package med.voll.api.domain.appointment.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidateTwoConsultationsPerDayPatient implements AppointmentValidations {
	@Autowired
	private AppointmentRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
		boolean notValidity = repository.existsByPatientIdAndDateBetween(
				dto.idPatient(),
				dto.date().withHour(7).withMinute(0),
				dto.date().withHour(18).withMinute(0));
		if (notValidity) {
			throw new AppointmentValidationException("O Paciente não pode Agendar duas consultadas no mesmo dia!");
		}
		
		
	}

}
