package med.voll.api.domain.address;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "address")

public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String logadouro;
	private String number;
	private String complement;
	private String neighborhood;
	private String city;
	private String state;
	private String cep;
	
	
	public Address() {
		super();
	}

    public Address( String logadouro, String number, String complement, String neighborhood, String city, String state, String cep) {
        this.logadouro = logadouro;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

        
		


	public Address(AddressDTO dto) {
    this.logadouro = dto.logradouro();
    this.number = dto.numero();
    this.complement = dto.complemento();
    this.neighborhood = dto.bairro();
    this.city = dto.cidade();
    this.state = dto.uf();
    this.cep = dto.cep();
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getLogadouro() {
		return logadouro;
	}





	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}





	public String getNumber() {
		return number;
	}





	public void setNumber(String number) {
		this.number = number;
	}





	public String getComplement() {
		return complement;
	}





	public void setComplement(String complement) {
		this.complement = complement;
	}





	public String getNeighborhood() {
		return neighborhood;
	}





	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public String getCep() {
		return cep;
	}





	public void setCep(String cep) {
		this.cep = cep;
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
