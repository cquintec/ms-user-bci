
/*
 * @(#)UserEnums.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.enums;

import lombok.RequiredArgsConstructor;

/**
 * UserEnums.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
@RequiredArgsConstructor
public enum UserEnum {

    ERROR_ENCODE(5, "Error al encriptar"),
    ERROR_DECODE(6, "Error al desencriptar"),
    COULD_NOT_SAVE_USER(6, "No se pudo guardar al usuario"),
    USER_ALREADY_EXIST(1,"El usuario ya esta registrado");

//    public static final String USER_ALREADY_EXIST = ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}