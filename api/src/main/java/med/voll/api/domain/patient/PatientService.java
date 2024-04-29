package med.voll.api.domain.patient;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
	@Autowired
	private PatientRepository repository;
	public Patient save(PatientDTO patient) {
		return repository.save( new Patient(patient));
	}
	public Page<PatientListDTO> listPatient(Pageable pageable) {
		return repository.findAll(pageable).map(PatientListDTO::new);
	}
	public Patient findById(Long id) {
		return repository.getReferenceById(id);
	}
	public Page<PatientListDTO> listPatientActiveIsTrue(Pageable pageable) {
		return repository.findByActiveIsTrue(pageable).map(PatientListDTO::new);
	}

}
