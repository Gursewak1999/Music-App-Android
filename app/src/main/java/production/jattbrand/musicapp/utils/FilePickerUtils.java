package production.jattbrand.musicapp.utils;

import androidx.fragment.app.Fragment;

import abhishekti7.unicorn.filepicker.UnicornFilePicker;
import production.jattbrand.musicapp.R;

public class FilePickerUtils {

    public static void pickDocuments(Fragment context, int REQUEST_CODE) {
        UnicornFilePicker.from(context)
                .addConfigBuilder()
                .selectMultipleFiles(false)
                .showHiddenFiles(false)
                .setFilters(new String[]
                        {
                                "pdf", "png", "jpg", "jpeg"
                        }).
                addItemDivider(true)
                .theme(R.style.UnicornFilePicker_Dracula)
                .build()
                .forResult(REQUEST_CODE);

    }

    public static void pickImages(Fragment context, int REQUEST_CODE) {
        UnicornFilePicker.from(context)
                .addConfigBuilder()
                .selectMultipleFiles(false)
                .showHiddenFiles(false)
                .setFilters(new String[]
                        {
                                "png", "jpg", "jpeg"
                        }).
                addItemDivider(true)
                .theme(R.style.UnicornFilePicker_Dracula)
                .build()
                .forResult(REQUEST_CODE);

    }
}
