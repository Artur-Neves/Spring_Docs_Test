package med.voll.api.domain.appointment.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidateTwoConsultationsPerHourDoctor implements AppointmentValidations {
	@Autowired
	private AppointmentRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
		boolean notValidity= repository.existsByDoctorIdAndDateBetween(dto.idDoctor(), dto.date().plusHours(-1),dto.date().plusHours(1));
		if (notValidity) {
			throw new AppointmentValidationException("O Médico não pode agendar duas consultadas na mesma hora!");
		}
		
	}

}
