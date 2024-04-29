/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import med.voll.api.domain.patient.PatientRepository;
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
public class ValidateInactivePatientTest extends BaseAppointmentValidationsTest {

    @InjectMocks
    private ValidateInactivePatient validation;

    @Mock
    private PatientRepository repository;

    @Test
    @DisplayName("Testando paciente não ativo")
    void test1() {
        BDDMockito.given(repository.existsByIdAndActiveTrue(dto.idPatient())).willReturn(false);
        Assertions.assertThrows(AppointmentValidationException.class, () -> validation.validation(dto));
    }

    @Test
    @DisplayName("Testando paciente ativo")
    void test2() {
        BDDMockito.given(repository.existsByIdAndActiveTrue(dto.idPatient())).willReturn(true);
        Assertions.assertDoesNotThrow(() -> validation.validation(dto));
    }

}
