package production.jattbrand.musicapp.utils.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;

import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.ArrayList;
import java.util.List;

import production.jattbrand.musicapp.misc.DialogOnCLickListener;
import production.jattbrand.musicapp.R;

public class DialogHelper {


    public static PowerMenu buildPopup(Context context, String[] dataset) {

        boolean isSelected = true;
        List<PowerMenuItem> list = new ArrayList<>();

        for (String s : dataset) {
            list.add(new PowerMenuItem(s, isSelected));

            if (isSelected) {
                isSelected = false;
            }
        }

        return new PowerMenu.Builder(context)
                .addItemList(list) // list has "Novel", "Poerty", "Art"
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT) // Animation start point (TOP | LEFT)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setTextColor(context.getResources().getColor(R.color.menu_text_no_selected))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(context.getResources().getColor(R.color.colorPrimary))
                .build();
    }


    public static Dialog logoutDialog(Context context) {
        return createDialog(context,
                "Log Out",
                "Do You Really want to Log-Out.",
                "Yes", "Cancel", new DialogOnCLickListener() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog, int id) {
                       // FirebaseAuth.getInstance().signOut();
                    }

                    @Override
                    public void onNegativeButtonClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
    }

    public static Dialog quitAppDialog(Activity activity) {
        return createDialog(activity.getApplicationContext(),
                "Quit App",
                "Do You Really want to Exit.",
                "Yes", "Cancel", new DialogOnCLickListener() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog, int id) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            activity.finishAndRemoveTask();
                        } else {
                            activity.finish();
                            System.exit(0);
                        }
                    }

                    @Override
                    public void onNegativeButtonClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
    }

    public static Dialog createDialog(Context context,
                                      String title,
                                      String message,
                                      String positiveText,
                                      String negativiText,
                                      DialogOnCLickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, listener::onPositiveButtonClick);
        builder.setNegativeButton(negativiText, listener::onNegativeButtonClick);
        AlertDialog dialog = builder.create();
        return dialog;

    }


    public static void showListDialog(Context context,
                                      String title,
                                      String[] itemList,
                                      DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setItems(itemList, okListener)
                .setCancelable(true)
                .create()
                .show();
    }

    public static void showImageDialogWithOkButton(Context context,
                                                   String title,
                                                   String message,
                                                   int icon,
                                                   String positiveButton,
                                                   DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setIcon(icon)
                .setMessage(message)
                .setPositiveButton(positiveButton, okListener)
                .setCancelable(true)
                .create()
                .show();
    }

    public static void showSingleChoiceListDialog(Context context,
                                                  String title,
                                                  String[] itemList,
                                                  int checkedItem,
                                                  String positiveButton,
                                                  String negativeButton,
                                                  DialogInterface.OnClickListener selectListener,
                                                  DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setSingleChoiceItems(itemList, checkedItem, selectListener)
                .setPositiveButton(positiveButton, okListener)
                .setNegativeButton(negativeButton, null)
                .setCancelable(true)
                .create()
                .show();
    }

}
