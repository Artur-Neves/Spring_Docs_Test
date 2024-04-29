package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DetailedAppointmentDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key") 
public class AppointmentController {
	@Autowired
	private AppointmentService service;
	@PostMapping()
	public ResponseEntity<DetailedAppointmentDTO> makeAppointment(@RequestBody @Valid AppointmentDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		Appointment appointment = service.save(dto);
		URI uri = uriComponentsBuilder.path("/Appointment/{id}").buildAndExpand(appointment.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetailedAppointmentDTO(appointment));
	}
        
        @DeleteMapping("/cancel/{id}")
        public ResponseEntity cancelAppointment(@PathVariable(name = "id") Long id){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
}
