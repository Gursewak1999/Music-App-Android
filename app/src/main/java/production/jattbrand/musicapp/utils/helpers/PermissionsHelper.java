package production.jattbrand.musicapp.utils.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionsHelper{

    private String TAG = "PermissionHelper";
    private final Activity activity;
    private PermissionsInterface listener = null;

    public void setListener(PermissionsInterface permissionsInterface) {
        this.listener = permissionsInterface;

    }

    public interface PermissionsInterface {

        void onGranted(String[] grantedPermissions);

        void onRejected(String[] rejectedPermission);

        void onUnknownError();

        void onComplete();
    }

    public PermissionsHelper(Activity activity) {
        this.activity = activity;

        ActivityCompat.OnRequestPermissionsResultCallback lit = new ActivityCompat.OnRequestPermissionsResultCallback() {
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (requestCode == MY_PERMISSIONS_REQUEST_CODE) {
                    // If request is cancelled, the result arrays are empty.

                    Log.e(TAG, "onRequestPermissionsResult: " + "reuest matches");
                    ArrayList<String> grantedPermissions = new ArrayList<>();
                    ArrayList<String> rejectedPermissions = new ArrayList<>();

                    if (grantResults.length > 0) {
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                                grantedPermissions.add(permissions[i]);
                            else
                                rejectedPermissions.add(permissions[i]);
                        }
                        if (listener != null) {
                            listener.onGranted(grantedPermissions.toArray(new String[]{}));
                            listener.onRejected(rejectedPermissions.toArray(new String[]{}));
                            listener.onComplete();
                        } else Log.e(TAG, "onRequestPermissionsResult: " + "listener not set");
                    } else {
                        if (listener != null) {
                            listener.onUnknownError();
                            listener.onComplete();
                        }
                    }
                }
            }
        };

    }

    public PermissionsHelper(Activity activity, PermissionsInterface permissionsInterface) {
        this(activity);
        this.listener = permissionsInterface;
    }

    private static final int MY_PERMISSIONS_REQUEST_CODE = 345;

    public boolean isPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(activity,
                permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void askPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(activity,
                permissions,
                MY_PERMISSIONS_REQUEST_CODE);
         }

}
