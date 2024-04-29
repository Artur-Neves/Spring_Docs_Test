package med.voll.api.domain.doctor;

public record DoctorListDTO(
		Long id,
		String nome,
		String email,
		String crm,
		Specialty especialidade) {
	public DoctorListDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}
