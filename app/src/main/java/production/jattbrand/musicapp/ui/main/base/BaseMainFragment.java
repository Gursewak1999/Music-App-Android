package production.jattbrand.musicapp.ui.main.base;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import production.jattbrand.musicapp.ui.dialog.ProgressDialogFragment;
import production.jattbrand.musicapp.ui.main.MainActivity;
import production.jattbrand.musicapp.utils.ToastUtils;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;


public class BaseMainFragment extends Fragment {

    protected static MainActivity parentActivity;
    protected static PreferenceManager preferenceManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        parentActivity = (MainActivity) requireActivity();
        preferenceManager = new PreferenceManager(requireContext());
    }

    public void initBase() {
//        parentActivity.setBounds();
//        showBottomBar();
//        parentActivity.setSubTitle("");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected Activity getParentActivity() {
        return parentActivity;
    }

    //Toast
    protected void showLongToast(int text) {
        ToastUtils.longToast(text);
    }

    protected void showLongToast(String text) {
        ToastUtils.longToast(text);
    }

    protected void showShortToast(int text) {
        ToastUtils.shortToast(text);
    }

    protected void showShortToast(String text) {
        ToastUtils.shortToast(text);
    }

    protected void hideBottomBar() {
        parentActivity.hideBottomBar();
    }

    protected void showBottomBar() {
        parentActivity.showBottomBar();
    }

    //preferenceManager
    protected PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    protected void showProgressBar() {
        ProgressDialogFragment.showProgress(getFragmentManager());
    }

    public void hideProgressBar() {
        ProgressDialogFragment.dismissProgress(getFragmentManager());
    }

    public void showSnackbar(String text) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show();
    }

    protected TextWatcher baseTextWatcher(TextInputEditText input) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
