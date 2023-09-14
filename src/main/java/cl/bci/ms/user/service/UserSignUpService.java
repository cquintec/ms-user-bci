
package cl.bci.ms.user.service;

import cl.bci.ms.user.dtos.UserRequestDto;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.exceptions.UserException;

/**
 * UserLoginService.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
public interface UserSignUpService {

    UserResponseDto userSignUpProcess(final UserRequestDto user) throws UserException;

}
