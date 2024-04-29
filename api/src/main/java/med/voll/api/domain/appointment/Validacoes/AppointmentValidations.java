package med.voll.api.domain.appointment.Validacoes;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentDTO;


public interface AppointmentValidations {
	void validation (AppointmentDTO dto);

}
