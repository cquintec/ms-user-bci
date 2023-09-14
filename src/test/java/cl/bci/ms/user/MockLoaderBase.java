
/*
 * @(#)MockLoaderBase.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user;

import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;

/**
 * MockLoaderBase.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
public class MockLoaderBase {

    public static String loadJsonStringResponse(final String responseName) {
        return new String(MockLoaderBase.loadJsonResponse(responseName), StandardCharsets.UTF_8);
    }

    public static byte[] loadJsonResponse(final String responseName) {
        return MockLoaderBase.loadResponse("json/" + responseName);
    }

    public static byte[] loadResponse(final String fileName) {
        try {
            final var in = MockLoaderBase.class.getClassLoader().getResourceAsStream(fileName);
            return IOUtils.toByteArray(in);
        } catch (final Exception e) {
            throw new IllegalArgumentException("Response doesn't exist", e);
        }
    }
}