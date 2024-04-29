package med.voll.api.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;
import med.voll.api.domain.appointment.Validacoes.AppointmentValidations;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository repository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
    @Autowired
    private List<AppointmentValidations> validations;
	public Appointment save(AppointmentDTO dto) {
		validations.forEach(validations -> validations.validation(dto));
		Appointment a = new Appointment(dto);
		if(dto.idDoctor()==null)
		a.setDoctor(findRandomDoctorBySpecialty(dto));
		return repository.save(a);
	}
	private Doctor findRandomDoctorBySpecialty(AppointmentDTO dto) {
            System.out.println("med.voll.api.domain.appointment.AppointmentService.findRandomDoctorBySpecialty()");
		return repository.findRandomDoctorActiveFree(dto.date().plusHours(-1), dto.date().plusHours(1), dto.specialty())
				.orElseThrow(()-> new AppointmentValidationException("No momento não existem médicos para a determinada especialidade"));
	}

    public void remove(Long id) {
     repository.deleteById(id);
    }
}
