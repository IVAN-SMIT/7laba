package auxillary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Этот класс принимает на вход String, а возвращает зашифрованное значение
 * Использует алгоритм SHA-256
 */

public class PasswordChecker {

    public String toSHA256(String password) throws NoSuchAlgorithmException {

       final MessageDigest digest = MessageDigest.getInstance("SHA-256");
       final byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_16));
       final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            final String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static char[] readPassword() throws IOException {
        if (System.console()!=null){return System.console().readPassword();}
        else return new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
    }
}
