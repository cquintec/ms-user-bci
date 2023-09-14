
/*
 * @(#)TestUserController.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.controller;

import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.fixture.UserRequestDtoFixture;
import cl.bci.ms.user.service.UserLoginService;
import cl.bci.ms.user.service.UserSignUpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * TestUserController.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
@ExtendWith(SpringExtension.class)
public class TestUserController {

    @MockBean
    private UserSignUpService userSignUpProcess;
    @MockBean
    private UserLoginService userLoginService;
    @MockBean
    private HttpServletRequest request;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(
                        new UserController(userSignUpProcess,userLoginService,request)
                ).build();
    }

    @Test
    void testSignUpProcess()throws Exception {
        Mockito.when(userSignUpProcess.userSignUpProcess(Mockito.any())).thenReturn(new UserResponseDto());
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/users/sign-up")
                        .content(this.objectMapper.writeValueAsString(UserRequestDtoFixture.getRequestDto()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void testLoginProcess()throws Exception {
        Mockito.when(userLoginService.userLoginProcess(Mockito.anyString()))
                .thenReturn(new UserResponseDto());

        Mockito.when(request.getHeader(Mockito.anyString()))
                .thenReturn("Some String");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}