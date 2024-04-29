package med.voll.api.domain.doctor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
@Query("Select d from Doctor d Order by d.name LIMIT 10")
public List<Doctor> findTop10Doctors();

public Page<Doctor> findByActiveIsTrue(Pageable pageable);

public boolean existsByIdAndActiveTrue(Long idDoctor);
}
