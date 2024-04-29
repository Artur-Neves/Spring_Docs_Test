package med.voll.api.domain.patient;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;
@Entity
public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String cpf;
	private Boolean active = true;
	@OneToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Address address;
	
	public Patient() {
		super();
	}
	public Patient(PatientDTO patient) {
		this.name = patient.nome();
		this.email = patient.email();
		this.phone = patient.telefone();
		this.cpf=patient.cpf();
	    this.address= new Address(patient.endereco());
	}

    public Patient(Long id, String name, String email, String phone, String cpf, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
    }
     public Patient(String name, String email, String phone, String cpf, Address address) {
      
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
    }
        
	public Patient(Long id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", cpf=" + cpf
				+ ", address=" + address + "]";
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}
	public Patient update(PatientUpdateDTO patientDTO) {
		if (!patientDTO.nome().trim().isBlank()){
		 this.name = patientDTO.nome();
		}
		if (!patientDTO.telefone().trim().isBlank()){
		 this.phone = patientDTO.telefone();
		}
		if (patientDTO.endereco() != null){
		 this.address =	patientDTO.endereco();
		}
		return this;
	}
	
	
	
	
}
