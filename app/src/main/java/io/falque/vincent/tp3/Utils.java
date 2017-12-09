package io.falque.vincent.tp3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Utils {
    static final String GRAVATAR_PREFIX = "https://www.gravatar.com/avatar/";

    static String md5(final String s) {
        final String MD5 = "MD5";
        if(s != null) {
            try {
                // Create MD5 Hash
                MessageDigest digest = java.security.MessageDigest
                        .getInstance(MD5);
                digest.update(s.getBytes());
                byte messageDigest[] = digest.digest();

                // Create Hex String
                StringBuilder hexString = new StringBuilder();
                for (byte aMessageDigest : messageDigest) {
                    String h = Integer.toHexString(0xFF & aMessageDigest);
                    while (h.length() < 2)
                        h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
        return "";
    }
}
