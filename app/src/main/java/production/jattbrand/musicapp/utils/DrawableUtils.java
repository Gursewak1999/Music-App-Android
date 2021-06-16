package production.jattbrand.musicapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableUtils {

    private static final float CORNER_RADIUS_PX = toPixel(16);

    private static int toPixel(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Drawable changeDrawableColor(Context context, int image, int color) {
        Drawable drawable = context.getResources().getDrawable(image);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, context.getResources().getColor(color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static GradientDrawable getChunkDrawable() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(CustomColorUtils.chunk_bg);
        shape.setCornerRadii(new float[]{
                toPixel(5), toPixel(5),
                toPixel(20), toPixel(20),
                toPixel(5), toPixel(5),
                toPixel(15), toPixel(15)});
        return shape;
    }

    public static GradientDrawable getMessageDrawable() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(CustomColorUtils.chunk_bg);
        shape.setCornerRadii(new float[]{
                toPixel(20), toPixel(20),
                toPixel(20), toPixel(20),
                toPixel(5), toPixel(5),
                toPixel(15), toPixel(15)});
        return shape;
    }

}
