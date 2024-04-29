package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Page<Patient> findByActiveIsTrue(Pageable pageable);

	boolean existsByIdAndActiveTrue(Long idPatient);

}
