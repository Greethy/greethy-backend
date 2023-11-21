package com.greethy.auth.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.Collectors;
/**
 * Utility class for generating One-Time Passwords (OTPs) using a secure random number generator.
 *
 * @author ThanhKien
 */
public class OtpUtil {

    private static final int RANDOM_NUMBER_ORIGIN = 0;

    private static final int RANDOM_NUMBER_BOUND = 10;

    private static final int OTP_LENGTH = 6;

    /**
     * Generates a random One-Time Password (OTP) using a secure random number generator.
     *
     * @return A string representation of the generated OTP.
     * @throws NoSuchAlgorithmException If a cryptographic algorithm is requested but is not available in the environment.
     */
    public static String generateOtp() throws NoSuchAlgorithmException {
        return SecureRandom.getInstanceStrong()
                .ints(RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND)
                .limit(OTP_LENGTH)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
