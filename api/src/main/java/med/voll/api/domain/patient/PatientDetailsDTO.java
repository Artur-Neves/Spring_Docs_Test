package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailsDTO(String nome, String email, String telefone, String cpf, Address endereco) {
	public PatientDetailsDTO(Patient patient) {
		this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
	}
}