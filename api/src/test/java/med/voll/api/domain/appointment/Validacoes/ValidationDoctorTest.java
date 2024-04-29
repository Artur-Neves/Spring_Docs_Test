/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment.Validacoes;

import java.time.LocalDateTime;
import java.util.Optional;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
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
public class ValidationDoctorTest extends BaseAppointmentValidationsTest{
     @InjectMocks
    private ValidationDoctor validation;
    @Mock
    private DoctorRepository repository;
    
    @Test
    @DisplayName("Doutor e especialidade não mencionados ")
    void teste1(){
         dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.now(), null);
      Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
     @Test
    @DisplayName("Doutor com apenas o id nulo")
    void teste2(){
       dto = new AppointmentDTO(null, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
      Assertions.assertDoesNotThrow(()->validation.validation(dto));
    }
    @Test
    @DisplayName("Doutor com o id inválido")
    void teste3(){
       dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
       BDDMockito.given(repository.findById(dto.idDoctor())).willReturn(Optional.empty());
     Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
   @Test
    @DisplayName("Doutor não ativo")
    void teste4(){
       dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
       Doctor doctor = new Doctor();
       doctor.setActive(false);
       BDDMockito.given(repository.findById(dto.idDoctor())).willReturn(Optional.of(doctor));
     Assertions.assertThrows(AppointmentValidationException.class,()->validation.validation(dto));
    }
      @Test
    @DisplayName("Doutor ativo")
    void teste5(){
       dto = new AppointmentDTO(Long.MIN_VALUE, Long.MIN_VALUE, LocalDateTime.now(), Specialty.ORTOPEDIA);
       Doctor doctor = new Doctor();
       doctor.setActive(true);
       BDDMockito.given(repository.findById(dto.idDoctor())).willReturn(Optional.of(doctor));
     Assertions.assertDoesNotThrow(()->validation.validation(dto));
    }
    
}
