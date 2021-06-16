package production.jattbrand.musicapp.utils;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import production.jattbrand.musicapp.R;

public class FragmentUtils {

    public static final String PROGRESS_DIALOG_FRAGMENT = "progress_dialog_fragment";
    public static final String FRAGMENT_MAIN = "fragment_main";
    public static final String FRAGMENT_NOTIFICATIONS = "fragment_notifications";
    public static final String FRAGMENT_SUBMIT_UPLOAD_REQUEST_FRAGMENT = "fragment_submit_upload_request_fragment";
    private static final String TAG = "FragmentUtils";
    private static String currentFragmentName = FRAGMENT_MAIN;

    FragmentUtils() {
        currentFragmentName = FRAGMENT_MAIN;
    }

    public static void setCurrentFragmentName(String fragmentName) {
        currentFragmentName = fragmentName;
    }

    public static String getCurrentFragmentName() {
        return currentFragmentName;
    }

    //Fragment Manager
//    public static void gotoMainFragment(Fragment fragment) {
//
//        setCurrentFragmentName(FragmentUtils.FRAGMENT_MAIN);
//        NavHostFragment.findNavController(fragment)
//                .navigate(R.id.action_to_mainFragment);
//    }
//
//    public static void gotoNotificationFragment(Fragment fragment) {
//
//        setCurrentFragmentName(FragmentUtils.FRAGMENT_NOTIFICATIONS);
//        NavHostFragment.findNavController(fragment)
//                .navigate(R.id.action_to_notificationFragment);
//    }
//
//    public static boolean isMainFragment(FragmentManager fragmentManager) {
//
//        Fragment frag = fragmentManager.getFragments().get(0);
//
//
//        Log.e(TAG, "isMainFragment: "+frag.getId() );
//        return frag.getId()==R.id.MainFragment;
//    }

    public static void gotoFragment(Fragment currentFragment,Fragment instance, String tag) {
        final FragmentTransaction ft = currentFragment.getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, instance, tag);
        ft.commit();
    }
}
