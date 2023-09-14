
package cl.bci.ms.user.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * UserResponseDto.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Data
public class UserResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private Date created;
    private Date lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;

}