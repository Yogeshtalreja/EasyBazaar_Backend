package com.example.easybazaar.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtility {
    public static boolean isValidNIC(String nic){
        String regex = "^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$";
        System.out.println("NIC"+ nic);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(nic);

        return m.matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber){

        String regex = "^[0][0-9]{3}-[0-9]{7}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobileNumber);

        return m.matches();
    }
}
