
package cl.bci.ms.user.controller;

import cl.bci.ms.user.constants.UserContans;
import cl.bci.ms.user.dtos.UserRequestDto;
import cl.bci.ms.user.dtos.UserResponseDto;
import cl.bci.ms.user.exceptions.UserException;
import cl.bci.ms.user.service.UserLoginService;
import cl.bci.ms.user.service.UserSignUpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 11-09-2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserSignUpService userSignUpProcess;
    private final UserLoginService userLoginService;
    private final HttpServletRequest request;

    @ApiOperation("Endpoint to register a new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = UserContans.API_RESPONSE_OK),
            @ApiResponse(code = 403, message = UserContans.API_RESPONSE_FORBIDDEN),
            @ApiResponse(code = 503, message = UserContans.API_RESPONSE_ERROR)
    })
    @GetMapping(value = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto signUpProcess(@Valid @RequestBody UserRequestDto userRequest) throws UserException {

        return userSignUpProcess.userSignUpProcess(userRequest);
    }

    @ApiOperation("Endpoint user login")
    @ApiResponses({
            @ApiResponse(code = 201, message = UserContans.API_RESPONSE_OK),
            @ApiResponse(code = 403, message = UserContans.API_RESPONSE_FORBIDDEN),
            @ApiResponse(code = 503, message = UserContans.API_RESPONSE_ERROR)
    })
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE )
    public UserResponseDto loginProcess() throws UserException {
       final String authorizationHeader=request.getHeader("Authorization");
       return userLoginService.userLoginProcess(authorizationHeader);
    }
}