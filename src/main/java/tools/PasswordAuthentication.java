/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Hash passwords for storage, and test passwords against password tokens.
 *
 * Instances of this class can be used concurrently by multiple threads.
 *
 * Completed by Herbert Caffarel 2019/03/20
 *
 * @author erickson
 * @author Herbert Caffarel
 * @see <a href="http://stackoverflow.com/a/2861125/3474">StackOverflow</a>
 */
public class PasswordAuthentication {
    
    private static final String INIT_ID = "51";

    /**
     * Each token produced by this class uses this identifier as a prefix.
     */
    public static final String ID = "$" + INIT_ID + "$";

    /**
     * The minimum recommended cost, used by default
     */
    public static final int DEFAULT_COST = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int SIZE = 128;
    private static final Pattern LAYOUT = Pattern.compile("\\$" + INIT_ID + "\\$(\\d\\d?)\\$(.{43})");
    private final SecureRandom random;
    private final int cost;

    /**
     * Create a password manager with a default cost
     */
    public PasswordAuthentication() {
        this(DEFAULT_COST);
    }

    /**
     * Create a password manager with a specified cost
     *
     * @param cost the exponential computational cost of hashing a password, 0
     * to 30
     */
    public PasswordAuthentication(int cost) {
        iterations(cost);
        /* Validate cost */
        this.cost = cost;
        this.random = new SecureRandom();
    }

    private static int iterations(int cost) {
        if ((cost < 0) || (cost > 30)) {
            throw new IllegalArgumentException("cost: " + cost);
        }
        return 1 << cost; // Décalage du bit à gauche : 0001 << 3 revient à 1000
    }

    /**
     * Hash a password for storage.
     *
     * @param password The password to hash
     * @return a secure authentication token to be stored for later
     * authentication
     */
    public String hash(char[] password) {
        byte[] salt = new byte[SIZE / 8];
        random.nextBytes(salt);
        byte[] dk = pbkdf2(password, salt, 1 << cost);
        byte[] hash = new byte[salt.length + dk.length];
        System.arraycopy(salt, 0, hash, 0, salt.length);
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        return ID + cost + '$' + enc.encodeToString(hash);
    }

    /**
     * Authenticate with a password and a stored password token.
     *
     * @param password The password to compare
     * @param token The token to compare the password to
     * @return true if the password and token match
     */
    public boolean authenticate(char[] password, String token) {
        Matcher m = LAYOUT.matcher(token);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid token format");
        }
        int iterations = iterations(Integer.parseInt(m.group(1)));
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
        byte[] check = pbkdf2(password, salt, iterations);
        int zero = 0;
        for (int idx = 0; idx < check.length; ++idx) {
            zero |= hash[salt.length + idx] ^ check[idx];
        }
        return zero == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
        } catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Invalid SecretKeyFactory", ex);
        }
    }

    /**
     * Hash a password in an immutable {@code String}.
     *
     * <p>
     * Passwords should be stored in a {@code char[]} so that it can be filled
     * with zeros after use instead of lingering on the heap and elsewhere.
     *
     * @param password The password to hash
     * @return a secure authentication token to be stored for later
     * authentication
     * @deprecated Use {@link #hash(char[])} instead
     */
    @Deprecated
    public String hash(String password) {
        return hash(password.toCharArray());
    }

}
