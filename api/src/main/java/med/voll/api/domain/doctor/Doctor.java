package med.voll.api.domain.doctor;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import med.voll.api.domain.address.Address;
@Entity
@Table(name = "doctors")

public class Doctor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String crm;
	private Boolean active = true;
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Address address;
	
	
	
	public Doctor() {
		super();
	}
	
	public Doctor(DoctorDTO dto) {
	this.name = dto.nome();
	this.email = dto.email();
	this.phone = dto.telefone();
	this.crm = dto.crm();
	this.specialty = Specialty.getSpecialty(dto.especialidade());
	this.address = new Address(dto.endereco());
	}

    public Doctor( String name, String email, String phone, String crm, Specialty specialty, Address address) {
    
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
    }
	public Doctor(Long id, String name, String email, String phone, String crm, Specialty specialty, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.crm = crm;
		this.specialty = specialty;
		this.address = address;
	}
        
        

	public Doctor(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id);
	}

	public void update(DoctorUpdateDTO doctorDTO) {

		if (doctorDTO.nome()!=null) {
			this.name = doctorDTO.nome();
		}
		if (doctorDTO.telefone()!=null) {
			this.phone = doctorDTO.telefone();
		}
		if (doctorDTO.endereco() !=null) {

			this.address = new Address(doctorDTO.endereco());

		}
		
	}
	
	
}
