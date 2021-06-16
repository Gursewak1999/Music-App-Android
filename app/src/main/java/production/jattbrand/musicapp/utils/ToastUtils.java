package production.jattbrand.musicapp.utils;

import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.IntDef;
import androidx.annotation.StringRes;
import production.jattbrand.musicapp.misc.MyApp;

public class ToastUtils {

    public static void shortToast(@StringRes int text) {
        shortToast(MyApp.getInstance().getString(text));
    }

    public static void shortToast(String text) {
        if (TextUtils.isEmpty(text))
            return;
        show(text, Toast.LENGTH_SHORT);
    }

    public static void longToast(@StringRes int text) {
        longToast(MyApp.getInstance().getString(text));
    }

    public static void longToast(String text) {
        if (TextUtils.isEmpty(text))
            return;
        show(text, Toast.LENGTH_LONG);
    }

    private static Toast makeToast(String text, @ToastLength int length) {
        return Toast.makeText(MyApp.getInstance(), text, length);
    }

    private static void show(String text, @ToastLength int length) {
        makeToast(text, length).show();
    }

    @IntDef({Toast.LENGTH_LONG, Toast.LENGTH_SHORT})
    private @interface ToastLength {

    }

}
