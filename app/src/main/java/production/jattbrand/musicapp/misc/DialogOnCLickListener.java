package production.jattbrand.musicapp.misc;

import android.content.DialogInterface;

public interface DialogOnCLickListener {

    void onPositiveButtonClick(DialogInterface dialog, int id);
    void onNegativeButtonClick(DialogInterface dialog, int id);

}
