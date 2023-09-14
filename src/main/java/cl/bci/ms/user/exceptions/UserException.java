
package cl.bci.ms.user.exceptions;

import lombok.Getter;
import lombok.NonNull;

/**
 * UserException.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Getter
public class UserException extends Exception{

    @NonNull
    private final int internalCode;
    public UserException(String message, int internalCode) {
        super(message);
        this.internalCode = internalCode;
    }

    public UserException(String message, Throwable cause, @NonNull int internalCode) {
        super(message, cause);
        this.internalCode = internalCode;
    }
}