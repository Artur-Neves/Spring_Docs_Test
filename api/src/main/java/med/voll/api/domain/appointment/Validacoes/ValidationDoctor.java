package med.voll.api.domain.appointment.Validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidationDoctor implements AppointmentValidations {
	@Autowired
	private DoctorRepository repository;
	@Override
	public void validation(AppointmentDTO dto) {
	 
	  if(dto.idDoctor()==null && dto.specialty()==null) {
		throw new AppointmentValidationException("O Médico e a especialidade não podem ser nulos!");
	}
	  if(dto.idDoctor()==null) {
		  return;
	  }
	  Optional<Doctor> doctor = repository.findById(dto.idDoctor());
	if(doctor.isEmpty()) {
		throw new AppointmentValidationException("O id do Médico não existe no banco de dados!");
	}
	 if(!doctor.get().getActive()) {
		 throw new AppointmentValidationException("Consulta não pode ser agendada com médico excluido");
	}
	
	}
}
