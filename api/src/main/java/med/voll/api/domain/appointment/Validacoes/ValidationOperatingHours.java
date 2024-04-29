package med.voll.api.domain.appointment.Validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.infra.exceptions.AppointmentValidationException;
@Component
public class ValidationOperatingHours implements AppointmentValidations {

	@Override
	public void validation(AppointmentDTO dto) {
	   LocalDateTime date = dto.date();
	   if (date.getHour()>18 || date.getHour()<7 || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
		throw new AppointmentValidationException("Este horário não é válido, escolha um de segunda à sábado das 7 ás 18");
	}
	}

}
