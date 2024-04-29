package med.voll.api.domain.appointment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.Patient;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	@Query(value ="select d from Doctor d "
			+ "where d.active=true and d.specialty =:specialty and"
			+ " d.id not in "
			+ "( select a.doctor.id from Appointment a where a.date Between :date1 and :date2) "
			+ "Order By Rand() Limit 1")
	Optional<Doctor> findRandomDoctorActiveFree(LocalDateTime date1, LocalDateTime date2, Specialty specialty);


	 boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime withMinute,
			LocalDateTime withMinute2);
	boolean existsByDoctorIdAndDateBetween(Long idDoctor, LocalDateTime plusHours, LocalDateTime plusHours2);

}
