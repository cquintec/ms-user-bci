
/*
 * @(#)UserLoginService.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.service;

import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.exceptions.UserException;

/**
 * UserLoginService.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
public interface UserLoginService {

    UserResponseDto userLoginProcess(final String auth) throws UserException;
}
