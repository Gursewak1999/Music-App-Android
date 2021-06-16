package production.jattbrand.musicapp.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.exoplayer2.Player;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import production.jattbrand.musicapp.R;
import production.jattbrand.musicapp.misc.CustomMediaPlayer;
import production.jattbrand.musicapp.modals.SongDetail;
import production.jattbrand.musicapp.utils.AnimationUtils;
import production.jattbrand.musicapp.utils.CustomColorUtils;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View header;
    private BottomNavigationView bottomNavigationMenu;
    private Toolbar toolbar;

    @BindView(R.id.bottom_player_title)
    public TextView bottom_player_title;
    @BindView(R.id.bottom_player_subtitle)
    public TextView bottom_player_subtitle;
    @BindView(R.id.bottom_player_play_button)
    public ImageButton bottom_player_play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bottom_player_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomMediaPlayer.getInstance(getApplicationContext())
                        .getMediaPlayer().setPlayWhenReady(!CustomMediaPlayer.getInstance(getApplicationContext())
                        .getMediaPlayer().getPlayWhenReady());
            }
        });

        ((TextView) findViewById(R.id.toolbar_title)).setTextColor(CustomColorUtils.text_color_dark);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.GONE);
        bottomNavigationMenu = findViewById(R.id.bottomNavigationView);
        drawer = findViewById(R.id.drawer_layout);

        header = navigationView.getHeaderView(0);
        initColors();
//        toolbar.setBackgroundColor(getResources().getColor(R.color.background_white));
//        toolbar.setTitleTextColor(Utils.invertColor(this, getResources().getColor(R.color.background_white)));

        setTitle("notez");
        setSupportActionBar(toolbar);

        setDrawer();
    }

    private void initColors() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(CustomColorUtils.background);
        }

        //    getWindow().setBackgroundDrawableResource(R.color.background_white);

//        Typeface face = Typeface.createFromAsset(getAssets(),
//                "font/poppins_bold.ttf");
//        ((TextView) findViewById(R.id.toolbar_title)).setTypeface(face);
        findViewById(R.id.drawer_layout).setBackgroundColor(CustomColorUtils.background);
        // findViewById(R.id.bottomNavigationView).setBackgroundColor(CustomColorUtils.background_alt);
        findViewById(R.id.bottomNavigationView).setElevation(0);

        findViewById(R.id.nav_view).setBackgroundColor(CustomColorUtils.background);
        header.setBackgroundColor(CustomColorUtils.background);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }

    public void setSubTitle(CharSequence subtitle) {
        if (subtitle.equals("")) {
            ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.toolbar_subtitle)).setText(subtitle);
        }
    }

    @Override
    public void setTitle(int title) {
        super.setTitle(title);
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);

        if (((TextView) findViewById(R.id.toolbar_title)).getText().toString().equals("")) {
            ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.VISIBLE);
        }
    }

    public void setSubTitle(int subtitle) {
        ((TextView) findViewById(R.id.toolbar_subtitle)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.toolbar_subtitle)).setText(subtitle);
    }


    private void setDrawer() {

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.MainFragment
//                , R.id.AddMEsFragment,
//                R.id.AddCourseFragment, R.id.AddBranchFragment,
//                R.id.AddSectionFragment, R.id.AddSubjectFragment, R.id.AddNotificationGroupFragment,
//                R.id.AddSyllabus, R.id.AddNotificationFragment, R.id.AddUploadRequestFragment,
//                R.id.MainFragment, R.id.ShowSyllabusFragment,
//                R.id.DashboardLogin, R.id.MySubmissionsFragment, R.id.ShowUploadRequestReceivedFragment,
//                R.id.SubmitUploadRequestFragment, R.id.SearchFragment,
//                R.id.ShowNotificationsFragment, R.id.ShowNotificationsGroupFragment
        )
                .setDrawerLayout(drawer)
                .build();

//        //moderator
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.AddAssignmentsFragment, R.id.AddMEsFragment, R.id.AddSyllabus, R.id.AddNotificationFragment, R.id.AddUploadRequestFragment
//        )
//                .setDrawerLayout(drawer)
//                .build();
//
//        //main Admin
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.AddCourseFragment, R.id.AddBranchFragment,
//                R.id.AddSectionFragment, R.id.AddSubjectFragment, R.id.AddNotificationGroupFragment)
//                .setDrawerLayout(drawer)
//                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationMenu, navController);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);


//        if (header != null && details != null && details.getProfile_url() != null && details.getFull_name() != null && details.getEmail() != null) {
//
//            new NotificationsHelper().subscribeAll(details.getNotification_topics());
//            ImageView profileImage = header.findViewById(R.id.drawer_image);
//            Glide.with(getApplicationContext())
//                    .load(details.getProfile_url())
//                    .circleCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(profileImage);
//            ((TextView) header.findViewById(R.id.drawer_title)).setText(details.getFull_name());
//            ((TextView) header.findViewById(R.id.drawer_subtitle)).setText(details.getEmail());
//        }
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == R.id.action_settings) {
//            return true;
//        } else {
//            return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
//        }
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity2, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        if (FragmentUtils.isMainFragment(getSupportFragmentManager())) {
//            //show Dialog to really Exit
//
//            //   DialogHelper.quitAppDialog(this).show();
//
//            Log.e("isMainFragment", "true");
//        } else {
//            //represent Stack here
//            Log.e("isMainFragment", "false");
//            super.onBackPressed();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (drawer.isOpen())
            drawer.close();

        Log.e(TAG, "onNavigationItemSelected: " + item.getTitle());
//        if (item.getItemId() == R.id.DashboardLogin) {
//
//            ProgressDialogFragment.showProgress(getSupportFragmentManager());
//            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
//                    .addDefaultShareMenuItem()
//                    .setToolbarColor(this.getResources().getColor(R.color.colorPrimary))
//                    .setShowTitle(true)
//                    //.setCloseButtonIcon()
//                    .build();
//
//            // This is optional but recommended
//            CustomTabsHelper.addKeepAliveExtra(this, customTabsIntent.intent);
//
//            // This is where the magic happens...
//            CustomTabsHelper.openCustomTab(this, customTabsIntent,
//                    Uri.parse("http://notez.site/web"),
//                    new WebViewFallback());
//
//            return false;
//        }
//
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    public void setFullBounds() {
        View frag_view = findViewById(R.id.nav_host_fragment);
        if (frag_view != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frag_view.getLayoutParams();
            //Log.e(TAG, "setFullBounds: pre " + params.getMarginStart() + " " + params.getMarginEnd());
            params.setMarginEnd(2);
            params.setMarginStart(2);
            frag_view.setLayoutParams(params);
            //Log.e(TAG, "setFullBounds: post " + params.getMarginStart() + " " + params.getMarginEnd());
        }
    }

    public void setBounds() {
        View frag_view = findViewById(R.id.nav_host_fragment);

        if (frag_view != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frag_view.getLayoutParams();
            //Log.e(TAG, "setFullBounds: pre " + params.getMarginStart() + " " + params.getMarginEnd());
            params.setMarginEnd(72);
            params.setMarginStart(72);
            frag_view.setLayoutParams(params);
            //Log.e(TAG, "setFullBounds: post " + params.getMarginStart() + " " + params.getMarginEnd());
        }
    }

    public void hideBottomBar() {
        //final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) bottomNavigationMenu.getLayoutParams();
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
        Log.e(TAG, "hideBottomBar: ");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Animation anim = AnimationUtils.animHideBottom();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottomNavigationMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottomNavigationMenu.startAnimation(anim);

    }

    public void showBottomBar() {
        //final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) bottomNavigationMenu.getLayoutParams();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.e(TAG, "showBottomBar: ");

        toolbar.setNavigationOnClickListener(v -> {
            if (this.drawer.isDrawerOpen(GravityCompat.START))
                this.drawer.closeDrawer(GravityCompat.START);
            else this.drawer.openDrawer(GravityCompat.START);
        });

        Animation anim = AnimationUtils.animShowBottom();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bottomNavigationMenu.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottomNavigationMenu.startAnimation(anim);

    }

    public void setPlayerSong(SongDetail songDetail) {

        new PreferenceManager(getApplicationContext()).putString(PreferenceManager.CURRENT_SONG, new Gson().toJson(songDetail));
        bottom_player_title.setText(songDetail.getName());
        bottom_player_subtitle.setText(songDetail.getDetails().get(0).getSINGERS());

        CustomMediaPlayer.getInstance(getApplicationContext())
                .getMediaPlayer().addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playWhenReady)
                    bottom_player_play_button.setImageResource(R.drawable.exo_icon_pause);
                else
                    bottom_player_play_button.setImageResource(R.drawable.ic_play_big);
            }
        });
    }
}