
/*
 * @(#)UserResponseDtoFixture.java
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
import cl.bci.ms.user.dtos.UserResponseDto;
import com.google.gson.Gson;

/**
 * UserResponseDtoFixture.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
public class UserResponseDtoFixture {

    public static UserResponseDto getResponseDto(){
        final var json = MockLoaderBase.loadJsonStringResponse("user_response_dto.json");
        return new Gson().fromJson(json, UserResponseDto.class);
    }
}