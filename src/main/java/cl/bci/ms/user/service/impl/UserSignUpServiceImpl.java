
package cl.bci.ms.user.service.impl;

import cl.bci.ms.user.config.JwtService;
import cl.bci.ms.user.dtos.UserRequestDto;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.entities.Phone;
import cl.bci.ms.user.entities.User;
import cl.bci.ms.user.enums.UserEnum;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.service.RepositoryService;
import cl.bci.ms.user.service.UserSignUpService;
import cl.bci.ms.user.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * UserLoginService.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSignUpServiceImpl implements UserSignUpService {

    private final RepositoryService repositoryService;
    private final JwtService jwtService;

    @Override
    public UserResponseDto userSignUpProcess(final UserRequestDto userRq) throws UserException {

        if (repositoryService.userExist(userRq.getName(),userRq.getEmail())) {
            throw new UserException(UserEnum.USER_ALREADY_EXIST.getMessage(),
                    UserEnum.USER_ALREADY_EXIST.getCode());
        }

        User user = userMapper(userRq);

        return repositoryService.saveOrUpdateUser(user);
    }

    private User userMapper(UserRequestDto userDto) throws UserException {

        User user=new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(UserUtils.encript(userDto.getPassword()));
        user.setToken(jwtService.generateToken(userDto.getName()));
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setActive(true);

        List<Phone> phones=new ArrayList<>();

        userDto.getPhones().stream().forEach(phoneDto->{
            Phone phone =new Phone();
            phone.setCityCode(phoneDto.getCityCode());
            phone.setContryCode(phoneDto.getCountryCode());
            phone.setNumber(phoneDto.getNumber());
            phone.setUser(user);
            phones.add(phone);
        });
        user.setPhones(phones);
        return user;
    }
}