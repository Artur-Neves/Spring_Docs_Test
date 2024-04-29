/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.infra.exceptions.AppointmentValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author devjava
 */

public class ValidateScheduleWithAdvanceNoticeTest extends BaseAppointmentValidationsTest{
    
    @InjectMocks
    private ValidateScheduleWithAdvanceNotice validation;

    @Test
    @DisplayName("Teste em que o horario é inferior a 30 minutos")
    void test1(){
        dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now().plusMinutes(29), Specialty.ORTOPEDIA);
        Assertions.assertThrows(AppointmentValidationException.class, ()-> validation.validation(dto));
    }
    
    @Test
    @DisplayName("Teste em que o horario é superior a 30 minutos")
    void test2(){
        dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now().plusMinutes(31), Specialty.ORTOPEDIA);
        Assertions.assertDoesNotThrow(()-> validation.validation(dto));
    }

    
}
