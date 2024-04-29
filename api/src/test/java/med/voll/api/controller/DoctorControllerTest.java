package med.voll.api.controller;

import ch.qos.logback.core.net.AutoFlushingObjectWriter;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DoctorControllerTest extends BaseControllerTest<DoctorService>{
    @Autowired
    private JacksonTester<Page<DoctorListDTO>> doctorListDTOJacksonPage;
    @Autowired
    private JacksonTester<DoctorListDTO> doctorListDTOJackson;
    @Autowired
    private JacksonTester<DoctorDTO> doctorDTOJackson;
    @Spy
    private Doctor doctorMock;
    @Autowired
    private JacksonTester<DoctorUpdateDTO> doctorUpdateDTOJackson;

    @WithMockUser
    @Test
    @DisplayName("Testando listagem dos doutores")
    void test1() throws Exception {
        List<DoctorListDTO> doctors = Arrays.asList(new DoctorListDTO(randomDoctor()));
        Page<DoctorListDTO> page = new PageImpl<>(doctors);
        when(service.findAllActiveTrue(any())).thenReturn(page);
        this.mvc.perform(get("/medicos"))
                .andExpect(status().isOk()).andExpect(
                        content().json(doctorListDTOJacksonPage.write(page).getJson()));
    }
    @WithMockUser
    @Test
    @DisplayName("Testando o salvamento dos doutores")
    void test2() throws Exception {
        Doctor doctor = randomDoctor();
        DoctorDTO dto = new DoctorDTO(doctor);
        BDDMockito.given(service.saveDoctor(dto)).willReturn(doctor);
        this.mvc.perform(
                        post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(doctorDTOJackson.write(
                                                dto)
                                        .getJson()))
                .andExpect(status().isCreated()).andExpect(
                        content().json(
                                doctorListDTOJackson.write(new DoctorListDTO(doctor)).getJson()));
    }
    @WithMockUser
    @Test
    @DisplayName("Testando a atualização dos doutores")
    void test3() throws Exception {
        DoctorUpdateDTO dto = new DoctorUpdateDTO(randomDoctor());
        BDDMockito.given(service.findById(dto.id())).willReturn(doctorMock);
        this.mvc.perform(
                        put("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(doctorUpdateDTOJackson.write(
                                                dto)
                                        .getJson()))
                .andExpect(status().isOk()).andExpect(
                        content().json(
                                doctorUpdateDTOJackson.write(new DoctorUpdateDTO(doctorMock)).getJson()));
        verify(doctorMock).update(dto);
    }
    @WithMockUser
    @Test
    @DisplayName("Testando a exclusão dos doutores")
    void test4() throws Exception {
        Doctor doctor = randomDoctor();
        this.mvc.perform(
                        delete("/medicos/{id}", doctor.getId()))
                .andExpect(status().isNoContent());
        then(service).should().deleteById(doctor.getId());

    }

    @WithMockUser
    @Test
    @DisplayName("Testando Selecionando um doutor")
    void test5() throws Exception {
        Doctor doctor = randomDoctor();
        when(service.findById(doctor.getId())).thenReturn(doctor );
        this.mvc.perform(get("/medicos/{id}", doctor.getId()))
                .andExpect(status().isOk()).andExpect(
                        content().json(doctorListDTOJackson.write(new DoctorListDTO(randomDoctor())).getJson())
                );

    }

    private Doctor randomDoctor() {
        return new Doctor(1L, "Artur", "ArturBonitao@gmail.com", "73 988180562", "8888", Specialty.ORTOPEDIA, randomAddress() );
    }
    private Address randomAddress(){
        return new Address("Casa", "12", "Residenvia", "Beira rio", "Mutuipe", "Bahia", "45345201");
    }
}