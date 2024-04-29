package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;
 

public record AppointmentDTO(
		
		Long idDoctor,
		@NotNull
		Long idPatient,
		@NotNull
		@Future
		LocalDateTime date,
		Specialty specialty) {

	public AppointmentDTO(Appointment appointment) {
	this(appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate(), appointment.getDoctor().getSpecialty());	
	}

}
