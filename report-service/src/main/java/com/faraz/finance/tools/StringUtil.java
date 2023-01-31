package com.faraz.finance.tools;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class StringUtil {


    public static String getUniquePropertyFromException(DataIntegrityViolationException e) {

        String specificCause = e.getMostSpecificCause().getMessage();
        int beginIndex = specificCause.indexOf('_');
        int endIndex = specificCause.indexOf(')');
        return specificCause.substring(++beginIndex, endIndex);
    }




    public static String removeBracketFromArray(List<?> array) {
        String raw = array.toString();
        int lastBracketIndex = raw.lastIndexOf(']');
        return raw.substring(1, lastBracketIndex);
    }

    public static String replaceArabicChars(String s) {
        if (s.contains("ي") || s.contains("ك"))
            s = s.replace("ي", "ی").replace("ك", "ک");
        return s;
    }

    public static boolean validateNationalCode(String nationalCode) {

        if (nationalCode.trim().isEmpty()) {
            return false; // Melli Code is empty
        } else if (nationalCode.length() != 10) {
            return false; // Melli Code is less or more than 10 digits
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(nationalCode.charAt(i)) * (10 - i);
            }
            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }
            // Invalid MelliCode
            return Character.getNumericValue(nationalCode.charAt(9)) == lastDigit;
        }
    }
}
