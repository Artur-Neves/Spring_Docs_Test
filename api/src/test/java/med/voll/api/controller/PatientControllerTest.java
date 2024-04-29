/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package med.voll.api.controller;

import java.util.Arrays;
import java.util.List;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDTO;
import med.voll.api.domain.patient.PatientDetailsDTO;
import med.voll.api.domain.patient.PatientListDTO;
import med.voll.api.domain.patient.PatientService;
import med.voll.api.domain.patient.PatientUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.then;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
/**
 *
 * @author devjava
 */

 class PatientControllerTest extends BaseControllerTest<PatientService> {

    @Autowired
    private JacksonTester<PatientDTO> PatientDTOJackson;
      @Autowired
    private JacksonTester<PatientDetailsDTO> PatientDetailsDTOJackson;
      @Autowired
      private JacksonTester<PatientUpdateDTO> PatientUpdateDTOJackson;
            @Autowired
      private JacksonTester<PatientListDTO> PatientListDTOJackson;
                    @Autowired
      private JacksonTester<Page<PatientListDTO>> PatientListDTOPageJackson;
      @Mock
      private Patient patientMock;
@WithMockUser
@Test
@DisplayName("Testando listagem dos pacientes")
void test1() throws Exception {
     List<PatientListDTO> pacientes = Arrays.asList(new PatientListDTO(randomPatient()));
        Page<PatientListDTO> page = new PageImpl<>(pacientes);
    when(service.listPatientActiveIsTrue(any())).thenReturn(page );
    this.mvc.perform(get("/pacientes"))
            .andExpect(status().isOk()).andExpect(
            content().json(PatientListDTOPageJackson.write(page).getJson()));
}
@WithMockUser
@Test
@DisplayName("Testando o salvamento dos pacientes")
void test2() throws Exception {
    Patient patient = randomPatient();
    PatientDTO dto = new PatientDTO(patient);
    BDDMockito.given(service.save(dto)).willReturn(patient);
    this.mvc.perform(
            post("/pacientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(PatientDTOJackson.write(
                    dto)
                    .getJson()))
            .andExpect(status().isCreated()).andExpect(
            content().json(
                    PatientDetailsDTOJackson.write(new PatientDetailsDTO(patient)).getJson()));
}
@WithMockUser
@Test
@DisplayName("Testando a atualização dos pacientes")
void test3() throws Exception {
    Patient patient = randomPatient();
    PatientUpdateDTO dto = new PatientUpdateDTO(patient);
    BDDMockito.given(service.findById(dto.id())).willReturn(patient);
    BDDMockito.given(service.findById(dto.id()).update(dto)).willReturn(patient);
    this.mvc.perform(
            put("/pacientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(PatientUpdateDTOJackson.write(
                    dto)
                    .getJson()))
            .andExpect(status().isOk()).andExpect(
            content().json(
                    PatientListDTOJackson.write(new PatientListDTO(patient)).getJson()));
}
@WithMockUser
@Test
@DisplayName("Testando a exclusão dos pacientes")
void test4() throws Exception {
    Patient patient = randomPatient();
    BDDMockito.given(service.findById(patient.getId())).willReturn(patientMock);
    this.mvc.perform(
            delete("/pacientes/{id}", patient.getId()))
            .andExpect(status().isNoContent());
    then(patientMock).should().setActive(false);
    
}
@WithMockUser
@Test
@DisplayName("Testando Selecionando um paciente")
void test5() throws Exception {
    Patient patient = randomPatient();
    when(service.findById(patient.getId())).thenReturn(patient );
    this.mvc.perform(get("/pacientes/{id}", patient.getId()))
            .andExpect(status().isOk()).andExpect(
            content().json(PatientDetailsDTOJackson.write(new PatientDetailsDTO(randomPatient())).getJson())
    );
   
}
private Address randomAddress(){
    return new Address("Casa", "12", "Residenvia", "Beira rio", "Mutuipe", "Bahia", "45345201");
}
private Patient randomPatient(){
    return new Patient(1L, "Artur", "artur.diamante@gmail.com", "88113206", "CPF", randomAddress());
}
}
