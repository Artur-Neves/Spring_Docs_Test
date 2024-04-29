package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidateScheduleWithAdvanceNotice implements AppointmentValidations {

	@Override
	public void validation(AppointmentDTO dto) {
		
		if(LocalDateTime.now().plusMinutes(30).isAfter(dto.date())) {
			throw new AppointmentValidationException("Não é permitido agendar uma consulta com menos de 30 minutos de atecedência");
		}
	}

}
