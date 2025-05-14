package com.sdms.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;

@Component
public class UtilityClass {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_@#$&";
    private static final int ID_LENGTH = 5;

//    public static String getUID() {
//        String uid = "";
//        return uid;
//    }

    public static String generateRandomID() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public String getUniqueString(String message) {
        return message + LocalDate.now();
    }

    public static String getStudentPassFailStatus(float percentage) {
        if (percentage <= 34) {
            return "Fail";
        } else
            return "Pass";
    }
}
