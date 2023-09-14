
package cl.bci.ms.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * PhoneDto.
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
public class PhoneDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String number;
    private String cityCode;
    private String countryCode;
}