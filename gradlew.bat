package production.jattbrand.notes.ui.main.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.List;
import java.util.Objects;

import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;
import production.jattbrand.notes.R;
import production.jattbrand.notes.RuntimeData;
import production.jattbrand.notes.modals.general.NotificationTopics;
import production.jattbrand.notes.ui.main.base.BaseMainFragment;
import production.jattbrand.notes.utils.helpers.NotificationTopicsHelper;
import production.jattbrand.notes.utils.helpers.PermissionsHelper;
import production.jattbrand.notes.utils.helpers.PreferenceManager;
import pub.devrel.easypermissions.EasyPermissions;

public class AddNotificationGroupFragment extends BaseMainFragment {


    private static final int RC_CAMERA_AND_STORAGE = 738;
    private static final String TAG = "AddNotifGroupFragment";
    private EasyImage easyImage;
    ImageView cover_image;
    Button button_next;
    ImageButton change_cover_imageButton;
    TextInputEditText input_title, input_description;
    String cover_url = "";
    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_