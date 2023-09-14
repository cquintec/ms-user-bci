
/*
 * @(#)UserLoginServiceImpl.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.service.impl;

import cl.bci.ms.user.config.JwtService;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.entities.User;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.service.RepositoryService;
import cl.bci.ms.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * UserLoginServiceImpl.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final RepositoryService repositoryService;
    private final JwtService jwtService;

    @Override
    public UserResponseDto userLoginProcess(final String auth) throws UserException {
        UserResponseDto newTokenUser = new UserResponseDto();
        final String token = cleanToken(auth);
        Optional<User> user = Optional.ofNullable(repositoryService.findUserByUserToken(token));
        if (user.isPresent()){
            User updatedUser=user.get();
            updatedUser.setToken(jwtService.generateToken(updatedUser.getName()));
            updatedUser.setLastLogin(new Date());
            newTokenUser = repositoryService.saveOrUpdateUser(updatedUser);
        }
        return newTokenUser;
    }
    private String cleanToken(String auth){
        return (auth.startsWith("Bearer "))?auth.substring(7):auth;
    }
}