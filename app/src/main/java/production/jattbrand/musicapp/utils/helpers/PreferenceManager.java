package production.jattbrand.musicapp.utils.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    public static final String HOME_DATA = "home_data";
    public static final String CURRENT_SONG = "current_song";
    private static final String LOGIN_DETAILS = "user_login_details";
    private static final String GENERAL_DATA = "general_data";

    private SharedPreferences mSharedPreferences;
    private Context context;

    public PreferenceManager(Context context) {
        this.context = context;
        this.mSharedPreferences = context.getSharedPreferences("notezPreference", Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return mSharedPreferences.edit();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        editor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean getBooleanValue(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public int getIntegerValue(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
