package med.voll.api.domain.doctor;

import java.util.Iterator;

import javax.management.RuntimeErrorException;

public enum Specialty {
	ORTOPEDIA,
	CARDIOLOGIA,
	GINECOLOGIA,
	DERMATOLOGIA;
	
	public static Specialty getSpecialty(String especialidade) {
		 return Specialty.valueOf(especialidade.trim().toUpperCase());
		 }
	
}
