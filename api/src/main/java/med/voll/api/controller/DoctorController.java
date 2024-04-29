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
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorDTO;
import med.voll.api.domain.doctor.DoctorListDTO;
import med.voll.api.domain.doctor.DoctorService;
import med.voll.api.domain.doctor.DoctorUpdateDTO;



@RestController()
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key") 
public class DoctorController {
	@Autowired
	private DoctorService service;
	
	@PostMapping()	
	@Transactional
	public ResponseEntity addDoctor(@RequestBody @Valid DoctorDTO doctorDTO, UriComponentsBuilder uribuilder) {
		Doctor doctor = service.saveDoctor(doctorDTO);
		URI uri = uribuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
		return ResponseEntity.created(uri).body(new DoctorListDTO(doctor));
	}
	@GetMapping()
	public ResponseEntity<Page<DoctorListDTO>> getTop10Doctors(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
		return ResponseEntity.ok(service.findAllActiveTrue(pageable));
	}
	@PutMapping()
	@Transactional
	public ResponseEntity putMethodName( @RequestBody @Valid DoctorUpdateDTO doctorDTO) {
		Doctor doctor = service.findById(doctorDTO.id());
		doctor.update(doctorDTO);
		return ResponseEntity.ok(new DoctorUpdateDTO(doctor));
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteDoctor(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorListDTO> getDoctor(@PathVariable Long id){
		return ResponseEntity.ok(new DoctorListDTO(service.findById(id)));
	}
}
