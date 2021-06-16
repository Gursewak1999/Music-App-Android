package production.jattbrand.musicapp.utils;

import android.app.Activity;
import android.graphics.Color;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean passwordValidator(String str) {
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

    public static int invertColor(Activity activity, int titleColor) {
        return Color.rgb(255 - Color.red(titleColor), 255 - Color.red(titleColor), 255 - Color.red(titleColor));
    }


    public int getRandomValue(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

}
