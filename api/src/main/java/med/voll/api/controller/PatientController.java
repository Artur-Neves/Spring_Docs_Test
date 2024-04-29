package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDTO;
import med.voll.api.domain.patient.PatientDetailsDTO;
import med.voll.api.domain.patient.PatientListDTO;
import med.voll.api.domain.patient.PatientService;
import med.voll.api.domain.patient.PatientUpdateDTO;

@RestController()
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key") 
public class PatientController {
	@Autowired
	private PatientService service;
	@PostMapping
	public ResponseEntity<PatientDetailsDTO> savePatient(@RequestBody @Valid PatientDTO patientDTO, UriComponentsBuilder uriBuilder) {
		Patient patient = service.save(patientDTO);
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(uri).body(new PatientDetailsDTO(patient));
	}
	@GetMapping
	public ResponseEntity<Page<PatientListDTO>> listPatient(@PageableDefault(size = 10, sort = "name") Pageable pageable){
		return ResponseEntity.ok(service.listPatientActiveIsTrue(pageable));
	}
	@PutMapping
	@Transactional
	public ResponseEntity<PatientListDTO> updatePatient(@RequestBody @Valid PatientUpdateDTO patientDTO) {
	  return ResponseEntity.ok(new PatientListDTO(service.findById(patientDTO.id()).update(patientDTO)));
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletePatient(@PathVariable Long id) {
		Patient patient = service.findById(id);
		patient.setActive(false);
	   return ResponseEntity.noContent().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity<PatientDetailsDTO> getPatient(@PathVariable Long id){
		Patient patient = service.findById(id);
	   return ResponseEntity.ok(new PatientDetailsDTO(patient));
	}
}