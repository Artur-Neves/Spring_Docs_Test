package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
		@NotBlank
		String logradouro,
		@NotBlank
		String bairro,
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,
		@NotBlank
		String cidade,
		@NotBlank
		String uf,
	
		String numero,
		String complemento
		) {

	public AddressDTO(Address address) {
		this(address.getLogadouro(), address.getNeighborhood(), address.getCep(), address.getCity(), address.getState(), address.getNumber(), address.getComplement());
	}

}
