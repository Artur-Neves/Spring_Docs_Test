/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.domain.appointment;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.Patient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import med.voll.api.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author devjava
 */

public class AppointmentRepositoryTest  extends BaseRepositoryTest<AppointmentRepository> {
    private  LocalDateTime date;
    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;
    
    @BeforeEach
     void beforchAll(){
        date = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10);
        doctor =registerDoctor("", "", "", "", Specialty.ORTOPEDIA, null);
        patient= registerPatient("", "", "", "",  null);
        appointment = registerAppointment(doctor, patient, date);
     }
     
    @Test
    @DisplayName("Medico ja possui uma consulta neste horario")
    void test1(){
        Doctor doctor2 = repository.findRandomDoctorActiveFree(date.plusHours(-1), date.plusHours(1), Specialty.ORTOPEDIA).orElse(null);
        assertThat(doctor2).isNull();
    }
        @Test
    @DisplayName("Medico Válido neste horario")
    void test2(){
        Doctor doctor2 = repository.findRandomDoctorActiveFree(date.plusHours(1), date.plusHours(2), Specialty.ORTOPEDIA).orElse(null);
        assertThat(doctor2).isEqualTo(doctor);
    }
           @Test
    @DisplayName("Medico Válido neste horario, mas não com a determinada especialidade")
    void test3(){
        Doctor doctor2 = repository.findRandomDoctorActiveFree(date.plusHours(1), date.plusHours(2), Specialty.GINECOLOGIA).orElse(null);
        assertThat(doctor2).isNull();
    }
    
    
    private Doctor registerDoctor( String name, String email, String phone, String crm, Specialty specialty, Address address){
        return em.persist(new Doctor( name, email, phone, crm, specialty, address));
    }
      private Patient registerPatient(String name, String email, String phone, String cpf, Address address){
        return em.persist(new Patient(name, email, phone, cpf, address));
    }
    private Appointment registerAppointment(Doctor doctor, Patient patient, LocalDateTime data){
        return em.persist(new Appointment( doctor, patient, data));
    }
}
