package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDTO;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Address endereco) {

    public PatientUpdateDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getCpf(), patient.getAddress());
    }
}
