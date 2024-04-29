package med.voll.api.controller;

import med.voll.api.domain.User.AuthenticationUserService;
import med.voll.api.domain.User.User;
import med.voll.api.domain.User.UserLoginDTO;
import med.voll.api.domain.User.UserRepository;
import med.voll.api.infra.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest<TokenService>{
    @Autowired
    private JacksonTester<UserLoginDTO> userLoginDTOJackson;
    @MockBean
    private AuthenticationUserService authenticationUserService;
    private UserLoginDTO userLoginDTO;

    @Test
    @DisplayName("Testando o usuário logado")
    void test1() throws Exception {
    userLoginDTO = new UserLoginDTO("fulano", "123");
        BDDMockito.given(authenticationUserService.loadUserByUsername(userLoginDTO.login())).willReturn(new User(1L,userLoginDTO.login(), "$2a$12$uMLQe0FDFWD/JzH71JZvbO4OUgdYZMXULcC2P0VmKTla2d8pTsmQG"));
        mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userLoginDTOJackson.write(userLoginDTO).getJson()))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}