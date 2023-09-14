
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
import cl.bci.ms.user.dtos.UserRequestDto;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.entities.Phone;
import cl.bci.ms.user.entities.User;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        System.out.println("response :"+response);
        Assertions.assertNotEquals(response.getToken(),AUTH);


    }
    private User dummyUserEntity() {
        User user=new User();
        user.setUserId(UUID.randomUUID());
        user.setLastLogin(new Date());
        user.setCreated(new Date());
        user.setToken(AUTH);
        user.setEmail("some@email.com");
        user.setActive(true);
        Phone phone=new Phone();
        phone.setPhoneId(1L);
        phone.setNumber("987654321");
        phone.setCityCode("02");
        phone.setCountryCode("52");
        List<Phone> phones=new ArrayList<>();
        phones.add(phone);
        user.setPhones(phones);
        return user;
    }
}