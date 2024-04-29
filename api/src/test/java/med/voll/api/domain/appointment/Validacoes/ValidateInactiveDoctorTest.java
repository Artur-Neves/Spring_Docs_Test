package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.infra.exceptions.AppointmentValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ValidateInactiveDoctorTest extends BaseAppointmentValidationsTest{
    @InjectMocks
    private ValidateInactiveDoctor validation;
    @Mock
    private DoctorRepository repository;
   
    @Test
	@DisplayName("Testando quando o doctor não esta ativo")
	void test1() {
            BDDMockito.given(repository.existsByIdAndActiveTrue(dto.idDoctor())).willReturn(false);
            Assertions.assertThrows(AppointmentValidationException.class, () -> validation.validation(dto));
	}
        @Test
        @DisplayName("Testando quando o doctor não esta ativo por não possuir id")
        void test2(){
           dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.MAX, Specialty.ORTOPEDIA);
           Assertions.assertDoesNotThrow(()->validation.validation(dto));
        }
         @Test
        @DisplayName("Testando quando o doctor esta ativo")
        void test3(){
            BDDMockito.given(repository.existsByIdAndActiveTrue(dto.idDoctor())).willReturn(true);
           Assertions.assertDoesNotThrow(()->validation.validation(dto));
        }

 
}
