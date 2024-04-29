package med.voll.api.domain.appointment;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;
	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;
	
	private LocalDateTime date;
	
	public Appointment(Doctor doctor, Patient patient, LocalDateTime data) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.date = data;
	}
	public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime data) {
		super();
		this.id=id;
		this.doctor = doctor;
		this.patient = patient;
		this.date = data;
	}
	public Appointment() {
		super();
	}
	public Appointment(AppointmentDTO dto) {
		this.doctor = new Doctor(dto.idDoctor());
		this.patient = new Patient(dto.idPatient());;
		this.date = dto.date();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
