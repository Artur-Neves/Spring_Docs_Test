/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import java.time.Month;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.infra.exceptions.AppointmentValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;

/**
 *
 * @author devjava
 */
public class ValidationOperatingHoursTest extends BaseAppointmentValidationsTest {

    @InjectMocks
    private ValidationOperatingHours validation;

    @Test
    @DisplayName("Horario válido ")
    void teste1() {
        dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.of(2024, Month.APRIL, 24, 7, 0), null);
        Assertions.assertDoesNotThrow(() -> validation.validation(dto));
    }

    @Test
    @DisplayName("Horario Inválido, muito cedo")
    void teste2() {
        dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.of(2024, Month.APRIL, 24, 6, 0), null);
        
        Assertions.assertThrows(AppointmentValidationException.class, () -> validation.validation(dto));
    }

    @Test
    @DisplayName("Horario Inválido, muito tarde")
    void teste3() {
        dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.of(2024, Month.APRIL, 24, 19, 0), null);
       Assertions.assertThrows(AppointmentValidationException.class, () -> validation.validation(dto));
    }

    @Test
    @DisplayName("Dia inválido")
    void teste4() {
        dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.of(2024, Month.APRIL, 28, 12, 0), null);
        Assertions.assertThrows(AppointmentValidationException.class, () -> validation.validation(dto));
    }
    @Test
    @DisplayName("Horario válido ")
    void teste5() {
        dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.of(2024, Month.APRIL, 24, 18, 0), null);
        Assertions.assertDoesNotThrow(() -> validation.validation(dto));
    }
}
