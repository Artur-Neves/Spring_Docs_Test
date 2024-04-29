package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDTO;

public record DoctorDTO(
		@NotBlank
	    String nome,
	    @NotBlank
	    @Email
	    String email,
	    @NotBlank
	    String telefone,
	    @NotBlank
	    @Pattern(regexp = "\\d{4,7}")
	    String crm,
	    @NotNull
	    String especialidade,
	    @NotNull @Valid
	    AddressDTO endereco
		) {

	public DoctorDTO(Doctor doctor) {
		this(doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), ""+doctor.getSpecialty(), new AddressDTO(doctor.getAddress()));
	}}
