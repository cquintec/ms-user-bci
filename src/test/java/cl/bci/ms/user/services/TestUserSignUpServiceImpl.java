
/*
 * @(#)TestUserSingUpServiceImpl.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.services;

import cl.bci.ms.user.config.JwtService;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.fixture.UserRequestDtoFixture;
import cl.bci.ms.user.fixture.UserResponseDtoFixture;
import cl.bci.ms.user.service.RepositoryService;
import cl.bci.ms.user.service.impl.UserSignUpServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



/**
 * TestUserSingUpServiceImpl.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
public class TestUserSignUpServiceImpl {

    private static final String AUTH="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGF1ZGlvIiwiZXhwIjoxNjk0Njc2ODIwLCJpYXQiOjE2OTQ2NDA4MjB9.AWatvwQCjWUWdN9xuZYUABiB5i8zlpWoyaJGNagk00s";
    @InjectMocks
    private UserSignUpServiceImpl userSignUpService;

    @Mock
    private RepositoryService repositoryService;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserSignUpProcess() throws UserException {
        Mockito.when(repositoryService.userExist(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(jwtService.generateToken(Mockito.anyString()))
                .thenReturn(AUTH);
        Mockito.when(repositoryService.saveOrUpdateUser(Mockito.any()))
                .thenReturn(UserResponseDtoFixture.getResponseDto());
        UserResponseDto response = userSignUpService.userSignUpProcess(UserRequestDtoFixture.getRequestDto());

        Assertions.assertNotEquals(response.getToken(),AUTH);
    }

    @Test
    void testUserSignUpProcess_usuario_existe() throws UserException {
        Mockito.when(repositoryService.userExist(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Boolean.TRUE);

        Assertions.assertThrows(UserException.class, () -> userSignUpService
                .userSignUpProcess(UserRequestDtoFixture.getRequestDto()));

    }

}