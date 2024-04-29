/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.infra.exceptions.AppointmentValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;



/**
 *
 * @author devjava
 */
public class ValidateTwoConsultationsPerDayPatientTest extends BaseAppointmentValidationsTest{
   @InjectMocks
    private ValidateTwoConsultationsPerDayPatient validation;
    @Mock
    private AppointmentRepository repository;
    
    @Test
    @DisplayName("Paciente tentando cadastrar mais de uma consulta por dia ")
    void teste1(){
         dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.MAX, Specialty.ORTOPEDIA);
        BDDMockito.given(repository.existsByPatientIdAndDateBetween(dto.idPatient(), dto.date().withHour(7).withMinute(0), dto.date().withHour(18).withMinute(0))).willReturn(true);
      Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
     @Test
    @DisplayName("Paciente tentando cadastrar uma consulta no dia ")
    void teste2(){
       dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.MAX, Specialty.ORTOPEDIA);
        BDDMockito.given(repository.existsByPatientIdAndDateBetween(dto.idPatient(), dto.date().withHour(7).withMinute(0), dto.date().withHour(18).withMinute(0))).willReturn(false);
      Assertions.assertDoesNotThrow(()->validation.validation(dto));
        
    }
    
}
