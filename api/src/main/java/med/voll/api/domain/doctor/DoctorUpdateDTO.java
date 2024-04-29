package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record DoctorUpdateDTO(
		@NotNull
		Long id,
		String nome,
		String telefone,
		@Valid
		AddressDTO endereco) {
	public DoctorUpdateDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getPhone(), new AddressDTO(doctor.getAddress()));
	}
}
