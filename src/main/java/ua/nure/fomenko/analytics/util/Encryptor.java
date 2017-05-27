package ua.nure.fomenko.analytics.util;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.exception.AppException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fomenko on 11.03.2017.
 */
public class Encryptor {

    private static final Logger LOG = Logger.getLogger(Encryptor.class);

    public String encryptPassword(String password){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] hashBite = messageDigest.digest();
            for (byte b: hashBite) {
                stringBuilder.append(Integer.toHexString((b & 240) >> 4));
                stringBuilder.append(Integer.toHexString(b & 15));
            }
            return stringBuilder.toString().toLowerCase();
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            LOG.error(Messages.ERR_CANNOT_ENCRYPT_PASSWORD, e);
            throw new AppException();

        }
    }

}
