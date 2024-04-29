package med.voll.api.domain.appointment.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;

public class ValidateInactivePatient implements AppointmentValidations {
	@Autowired
	private PatientRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
		 
		if(!repository.existsByIdAndActiveTrue(dto.idPatient())) {
			throw new AppointmentValidationException("Este paciente não esta ativo, logo não pode realizar o agendamento");
		}
	}

}
