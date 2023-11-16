package com.greethy.auth.utility;

import java.util.Random;

public class OtpUtil {

    public static int generateOtp(){
        return 100000 + new Random().nextInt(900000);
    }

}
