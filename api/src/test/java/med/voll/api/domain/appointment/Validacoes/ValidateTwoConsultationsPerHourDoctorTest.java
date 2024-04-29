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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 *
 * @author devjava
 */
public class ValidateTwoConsultationsPerHourDoctorTest extends BaseAppointmentValidationsTest{
    
 @InjectMocks
    private ValidateTwoConsultationsPerHourDoctor validation;
    @Mock
    private AppointmentRepository repository;
    
    @Test
    @DisplayName("Doutor não pode cadastrar mais de uma consulta no mesmo horario ")
    void teste1(){
         dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
        BDDMockito.given(repository.existsByDoctorIdAndDateBetween(dto.idDoctor(), dto.date().plusHours(-1),dto.date().plusHours(1))).willReturn(true);
      Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
     @Test
    @DisplayName("Doutor pode cadastrar uma consulta em determinado horario livre ")
    void teste2(){
       dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
        BDDMockito.given(repository.existsByDoctorIdAndDateBetween(dto.idDoctor(), dto.date().plusHours(-1),dto.date().plusHours(1))).willReturn(false);
      Assertions.assertDoesNotThrow(()->validation.validation(dto));
        
    }
    
}
