/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exceptions.AppointmentValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 *
 * @author devjava
 */
public class ValidationPatientTest extends BaseAppointmentValidationsTest{
    @InjectMocks
    private ValidationPatient validation;
    @Mock
    private PatientRepository repository;
    
    @Test
    @DisplayName("O id do paciente não é válido")
    void teste1(){
         BDDMockito.given(repository.existsById(dto.idPatient())).willReturn(false);
      Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
     @Test
    @DisplayName("O id do paciente é válido")
    void teste2(){
         BDDMockito.given(repository.existsById(dto.idPatient())).willReturn(true);
      Assertions.assertDoesNotThrow(()->validation.validation(dto));
    }
}
