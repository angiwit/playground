package hash;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Test {

    public static void main(String[] args) {
        md5Default();
        md5WithoutPadding0();
    }

    public static void md5Default() {
        System.out.println(DigestUtils.md5DigestAsHex("zaniu".getBytes(StandardCharsets.UTF_8)));
    }

    public static void md5WithoutPadding0() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String toBeEncrypt = "zaniu";
            md.update(toBeEncrypt.getBytes(StandardCharsets.UTF_8));
            /**
             * when MD5 result not enough to 32 characters, do NOT add 0 at the beginning.
             */
            String result = new BigInteger(1, md.digest()).toString(16).toUpperCase();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
