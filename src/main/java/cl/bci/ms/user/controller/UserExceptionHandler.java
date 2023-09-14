
package cl.bci.ms.user.controller;

import cl.bci.ms.user.dtos.ErrorDto;
import cl.bci.ms.user.dtos.ErrorResponseDto;
import cl.bci.ms.user.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserExceptionHandler.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Slf4j
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorResponseDto> handleOnError(UserException ex){
        LocalDateTime ahora = LocalDateTime.now();
        ErrorDto errorDto=ErrorDto.builder()
                .timeStamp(ahora)
                .codigo(ex.getInternalCode())
                .detail(ex.getMessage())
                .build();
        ErrorResponseDto errorResponseDto=ErrorResponseDto.builder()
                .error(Arrays.asList(errorDto))
                .build();

        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}