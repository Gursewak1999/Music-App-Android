package production.jattbrand.musicapp.utils;

import android.graphics.Color;

import java.util.Random;

public class CustomColorUtils {

    public static final int skin_bg = Color.parseColor("#F7ECDB");
    public static final int sky_bg = Color.parseColor("#DBECF7");
    public static final int red_bg = Color.parseColor("#EB5757");
    public static final int mustard_bg = Color.parseColor("#FEC674");
    public static final int black_bg = Color.parseColor("#000000");
    public static final int white_bg = Color.parseColor("#ffffff");
    public static final int light_gray_bg = Color.parseColor("#d3d3d3");

    public static final int background = Color.parseColor("#F5EFE5");
    public static int background_alt = Color.parseColor("#FAF7F3");

    public static final int text_color_light = sky_bg;
    public static final int text_color_dark = Color.parseColor("#686868");
    public static final int text_color = text_color_light;
    public static final int text_color_hint = light_gray_bg;

    public static final int button_bg = mustard_bg;
    public static final int button_text = text_color_dark;

    public static final int button_bg_dark = sky_bg;
    public static final int button_text_dark = text_color_dark;

    public static final int chunk_bg = mustard_bg;
    public static final int chunk_text = text_color_dark;
    public static final int chunk_bg_alt = white_bg;
    public static final int chunk_text_alt = text_color_dark;
    public static int list_bg = mustard_bg;
    public static int divider_bg = background;
    public static int chip_selected = mustard_bg;
    public static int chip_unselected = skin_bg;

    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return !(darkness < 0.6); // It's a dark color
    }

    public static int generateRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public CustomColorUtils() {
    }

}
