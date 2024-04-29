package med.voll.api.domain.doctor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository repository;
	public Doctor saveDoctor(DoctorDTO doctor) {
		return repository.save( new Doctor(doctor)); 
	}
	public List<DoctorListDTO> listTop10Doctor(){
		return repository.findTop10Doctors().stream().map(DoctorListDTO::new).collect(Collectors.toList());
	}
	public Page<DoctorListDTO> findAllActiveTrue(Pageable pageable) {
		return repository.findByActiveIsTrue(pageable).map(DoctorListDTO::new);
	}
	public Doctor findById(Long id) {
		return repository.getReferenceById(id);
	}
	public void deleteById(Long id) {
		Doctor doctor = findById(id);
		doctor.setActive(false);

	}
	
	
	
}
