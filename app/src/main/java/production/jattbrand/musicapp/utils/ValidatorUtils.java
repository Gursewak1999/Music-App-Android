package production.jattbrand.musicapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {

    public static boolean isPhoneNumber(String mob) {
        return mob.length() == 10;
    }

    public static boolean isEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean isPassword(String str) {
        char ch;
        boolean alphabetFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch)) {
                alphabetFlag = true;
            }
            if (numberFlag && alphabetFlag)
                return true;
        }
        return false;
    }
}
