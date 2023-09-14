
/*
 * @(#)UserRequestDtoFixture.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.fixture;

import cl.bci.ms.user.MockLoaderBase;
import cl.bci.ms.user.dtos.UserRequestDto;
import com.google.gson.Gson;

/**
 * UserRequestDtoFixture.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
public class UserRequestDtoFixture {
    public static UserRequestDto getRequestDto(){
        final var json = MockLoaderBase.loadJsonStringResponse("user_request_dto.json");
        return new Gson().fromJson(json, UserRequestDto.class);
    }
}