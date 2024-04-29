package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailedAppointmentDTO(
		Long id,
		Long idDoctor,
		Long idPatient,
		LocalDateTime date){
	public DetailedAppointmentDTO(Appointment appointment) {
		this(appointment.getId(), appointment.getDoctor().getId(),appointment.getPatient().getId(), appointment.getDate());	
		}

}
