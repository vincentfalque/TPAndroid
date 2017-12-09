package io.falque.vincent.tp3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    static String timestampToString(Long timestamp){
        try {
            Date datePasse = new Date(timestamp);
            Date maintenant = new Date();
            long secondes = TimeUnit.MILLISECONDS.toSeconds(maintenant.getTime() - datePasse.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(maintenant.getTime() - datePasse.getTime());
            long heures = TimeUnit.MILLISECONDS.toHours(maintenant.getTime() - datePasse.getTime());
            long jours = TimeUnit.MILLISECONDS.toDays(maintenant.getTime() - datePasse.getTime());

            if( secondes < 60 )
            {
                return "il y a " + secondes + " secondes";
            }
            else if( minutes < 60 )
            {
                return "il y a " + minutes + " minutes";
            }
            else if( heures < 24 )
            {
                return "il y a " + heures + " heures";
            }
            else
            {
                return "il y a " + jours + " jours";
            }
        }
        catch (Exception j) {
            j.printStackTrace();
            return null;
        }
    }
}
