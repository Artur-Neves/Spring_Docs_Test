/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.patient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import med.voll.api.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author devjava
 */

public class PatientRepositoryTest extends BaseRepositoryTest<PatientRepository> {
    private Patient patient;
    @BeforeEach
    void beforeEach(){
      patient = registerPatient();
    }
  
    @Test
    @DisplayName("Patient inativo")
    void test1(){
        patient.setActive(false);
        boolean b=repository.existsByIdAndActiveTrue(patient.getId());
        assertThat(b).isFalse();
    }
      @Test
    @DisplayName("Patient ativo")
    void test2(){
        patient.setActive(true);
        boolean b=repository.existsByIdAndActiveTrue(patient.getId());
        assertThat(b).isTrue();
    }
 
    
      private Patient registerPatient(){
        return em.persist(new Patient());
    }
   
}
