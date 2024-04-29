package med.voll.api.domain.appointment.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidationPatient implements AppointmentValidations {
	@Autowired
	private PatientRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
		if(!repository.existsById(dto.idPatient())) {
			throw new  AppointmentValidationException("O Id do paciente não é válido!");
		}
		
	}

}
