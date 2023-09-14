
/*
 * @(#)UserUtils.java
 *
 * Copyright (c) CQC (Chile). All rights reserved.
 *
 * All rights to this product are owned by CQC and may only
 * be used under the terms of its associated license document.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms.user.utils;

import cl.bci.ms.user.enums.UserEnum;
import cl.bci.ms.user.exceptions.UserException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * UserUtils.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 13-09-2023
 */
public class UserUtils {
    private static String  ENCRYPT_KEY="acegikmoqsbdfhjl";

    public static final String encript(String text) throws UserException {
        try{
            Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        }catch (Exception ex){
            throw new UserException(UserEnum.ERROR_ENCODE.getMessage(),
                    UserEnum.ERROR_ENCODE.getCode() );
        }
    }

    public static final String decrypt(String encrypted) throws UserException {
        try {
            byte[] encryptedBytes=Base64.getDecoder().decode( encrypted.replace("\n", "") );
            Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return new String(cipher.doFinal(encryptedBytes),"UTF-8");

        }catch (Exception ex){

            throw new UserException(UserEnum.ERROR_DECODE.getMessage(),
                    UserEnum.ERROR_DECODE.getCode());
        }

    }
}