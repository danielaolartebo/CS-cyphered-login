package cypher_login.back_jwt.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordUtil {
    private static final int ITERATIONS = 65536; // Recommended by NIST
    private static final int KEY_LENGTH = 256;  // Hash output length in bits
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256"; // PBKDF2 with SHA-256

    // Generate a random salt
    public static String generateSalt() {
        byte[] salt = new byte[16]; // 128-bit salt
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash the password with the salt
    public static String hashPassword(String password, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            System.out.println(exception.getMessage());
        }

        return "Error";
    }

    // Validate a password against the hash and salt
    public static boolean validatePassword(String password, String salt, String hash)  {
        String hashedPassword = hashPassword(password, salt);
        return hashedPassword.equals(hash);
    }
}
