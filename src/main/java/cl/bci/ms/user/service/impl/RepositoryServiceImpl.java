
/*
 * @(#)RepositoryServiceImpl.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.service.impl;

import cl.bci.ms.user.dtos.PhoneDto;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.entities.User;
import cl.bci.ms.user.enums.UserEnum;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.repository.UserRepository;
import cl.bci.ms.user.service.RepositoryService;
import cl.bci.ms.user.utils.UserUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RepositoryServiceImpl.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto saveOrUpdateUser(User user) throws UserException {
        UserResponseDto userResponse;
        ObjectMapper mapper = new ObjectMapper();
        try {
            User resultado = userRepository.save(user);
            userResponse = getUserResponseDto(mapper, resultado);

        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new UserException(UserEnum.COULD_NOT_SAVE_USER.getMessage(),
                    UserEnum.COULD_NOT_SAVE_USER.getCode());
        }
        return userResponse;
    }

    @Override
    public User findUserByUserToken(String token) {
        return userRepository.findUserByUserToken(token);
    }

    @Override
    public Boolean userExist(String name, String email){
        return userRepository.existsUserByNameByEmail(name,email);
    }

    private UserResponseDto getUserResponseDto(ObjectMapper mapper, User resultado) throws UserException {
        UserResponseDto personInfo;
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        personInfo = mapper.convertValue(resultado, UserResponseDto.class);
        personInfo.setPassword(UserUtils.decrypt(personInfo.getPassword()));
        List<PhoneDto> phones = mapper.convertValue( resultado.getPhones(),new TypeReference<>() {});
        personInfo.setPhones(phones);
        return personInfo;
    }
}