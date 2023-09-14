
/*
 * @(#)LoginAuthController.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.controller;

import cl.bci.ms.user.constants.UserContans;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.service.UserLoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginAuthController.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class LoginAuthController {

    private final UserLoginService userLoginService;
    private final HttpServletRequest request;

    @ApiOperation("Endpoint user login")
    @ApiResponses({
            @ApiResponse(code = 201, message = UserContans.API_RESPONSE_OK),
            @ApiResponse(code = 403, message = UserContans.API_RESPONSE_FORBIDDEN),
            @ApiResponse(code = 503, message = UserContans.API_RESPONSE_ERROR)
    })
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto loginProcess() throws UserException {
        final String authorizationHeader=request.getHeader("Authorization");

        return userLoginService.userLoginProcess(authorizationHeader);
    }

}