
package cl.bci.ms.user.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ErrorDto.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Data
@Builder
public class ErrorDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime timeStamp;
    private Integer codigo;
    private String detail;

}