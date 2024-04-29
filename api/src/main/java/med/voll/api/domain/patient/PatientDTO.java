package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDTO;

public record PatientDTO(
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefone,
		@NotBlank
		String cpf,
		@NotNull
		@Valid
		AddressDTO endereco) {

	public PatientDTO(Patient patient) {
		this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), new AddressDTO(patient.getAddress()));
	}

}
