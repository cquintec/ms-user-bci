
package cl.bci.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * UserRequestDto.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 11-09-2023
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede ser estar vacio")
    private String name;

    @Email(regexp =".+@.+\\..+")
    private String email;

    @NotNull(message = "El password no puede ser null")
    @Pattern(regexp ="(?=.*[0-9].{2})(?=.*[a-z])(?=.*[A-Z]).{1}(?=\\S+$).{8,12}$"
            ,message = "El password no cumple con los requisitos")
    private String password;

    private List<PhoneDto> phones;
}