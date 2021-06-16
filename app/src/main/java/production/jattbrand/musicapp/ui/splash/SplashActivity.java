package production.jattbrand.musicapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import production.jattbrand.musicapp.R;
import production.jattbrand.musicapp.modals.HomeDetails;
import production.jattbrand.musicapp.network.NetworkUtils;
import production.jattbrand.musicapp.ui.main.MainActivity;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // initPlayerService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }).start();

    }

    private void initData() {
        Observable<HomeDetails> disposable = NetworkUtils.getClient().getHomeDetails();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initPlayerService() {
        String url = "https://lq.djyoungster.in/LaTest Punjabi/Solace-The PropheC/Solace The PropheC.mp3";
//        Intent svc=new Intent(this, MusicService.class);
//        svc.setAction(Actions.ACTION_PLAY_NEW);
//        svc.putExtra("url",url);
//        startService(svc);
    }


}