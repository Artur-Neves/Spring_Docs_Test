/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.Doctor;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import med.voll.api.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author devjava
 */
public class DoctorRepositoryTest extends BaseRepositoryTest<DoctorRepository> {
    private Doctor doctor;
    @BeforeEach
    void beforeEach(){
      doctor = registerDoctor();
    }
  
    @Test
    @DisplayName("Doctor inativo")
    void test1(){
        doctor.setActive(false);
        boolean b=repository.existsByIdAndActiveTrue(doctor.getId());
        assertThat(b).isFalse();
    }
      @Test
    @DisplayName("Doctor ativo")
    void test2(){
        doctor.setActive(true);
        boolean b=repository.existsByIdAndActiveTrue(doctor.getId());
        assertThat(b).isTrue();
    }
     private Doctor registerDoctor(){
        return em.persist(new Doctor( ));
    }
    
}
