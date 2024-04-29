package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentDTO;
import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DetailedAppointmentDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorDTO;
import med.voll.api.domain.doctor.DoctorListDTO;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppointmentControllerTest extends BaseControllerTest<AppointmentService> {
    @Autowired
    private JacksonTester<AppointmentDTO> appointmentDTOJackson;
    @Autowired
    private JacksonTester<DetailedAppointmentDTO> detailedAppointmentDTOJackson;

    @WithMockUser
    @Test
    @DisplayName("Testando o salvamento das consultas")
    void test1() throws Exception {
        Appointment appointment = randomAppointment();
        AppointmentDTO dto = new AppointmentDTO(appointment);
        BDDMockito.given(service.save(dto)).willReturn(appointment);
        this.mvc.perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentDTOJackson.write(
                                                dto)
                                        .getJson()))
                .andExpect(status().isCreated()).andExpect(
                        content().json(
                                detailedAppointmentDTOJackson.write(new DetailedAppointmentDTO(appointment)).getJson()));
    }
    @WithMockUser
    @Test
    @DisplayName("Testando a exclusão das consultas")
    void test2() throws Exception {
        Appointment appointment = randomAppointment();
        this.mvc.perform(
                        delete("/appointment/cancel/{id}", appointment.getId()))
                .andExpect(status().isNoContent());
        verify(service).remove(appointment.getId());
    }

    private Appointment randomAppointment() {
        return new Appointment(1L,randomDoctor(), randomPatient(),
                LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10));
    }
    private Doctor randomDoctor() {
        return new Doctor(1L, "Artur", "ArturBonitao@gmail.com", "73 988180562", "8888", Specialty.ORTOPEDIA, randomAddress() );
    }
    private Address randomAddress(){
        return new Address("Casa", "12", "Residenvia", "Beira rio", "Mutuipe", "Bahia", "45345201");
    }
    private Patient randomPatient(){
        return new Patient(1L, "Artur", "artur.diamante@gmail.com", "88113206", "CPF", randomAddress());
    }

}