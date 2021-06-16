package production.jattbrand.musicapp.misc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.gson.Gson;

import production.jattbrand.musicapp.R;
import production.jattbrand.musicapp.modals.SongDetail;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;

public class MusicService extends Service {

    private static final int NOTIFICATION_ID = 785;
    private static final String TAG = "MusicService";
    private CustomMediaPlayer mediaPlayer;
    private static final String CHANNEL_ID = "music_app_channel";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "onStartCommand: ");
        if (mediaPlayer == null || mediaPlayer.getMediaPlayer() == null) {
            startPlayer();
            Log.e(TAG, "onStartCommand: Starting Notification");
            initNotification();
        }

        if (intent == null || intent.getAction() == null)
            return START_REDELIVER_INTENT;
        String action = intent.getAction();

        switch (action) {
            case Actions.ACTION_PLAY_NEW:
                mediaPlayer.playNew(intent.getStringExtra("url"));
                initNotification();
                break;
            case Actions.ACTION_PLAY:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                else
                    mediaPlayer.play();
                break;
            case Actions.ACTION_STOP:
                mediaPlayer.stop();
                stopForeground(true);
                break;
        }

        return START_STICKY;
    }

    private PlayerNotificationManager.MediaDescriptionAdapter createMediaDescriptionAdapter() {

        String data = new PreferenceManager(getApplicationContext()).getStringValue(PreferenceManager.CURRENT_SONG);

        SongDetail songDetail = new Gson().fromJson(data, SongDetail.class);

        return new PlayerNotificationManager.MediaDescriptionAdapter() {
            @Override
            public String getCurrentSubText(Player player) {
                return songDetail.getName();
            }

            @Override
            public String getCurrentContentTitle(Player player) {
                return songDetail.getName();
            }

            @Override
            public PendingIntent createCurrentContentIntent(Player player) {
                return null;
            }

            @Override
            public String getCurrentContentText(Player player) {
                return songDetail.getDetails().get(0).getSINGERS();
            }

            @Override
            public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {

                return null;
            }

        };
    }

    private void initNotification() {

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        }

        PlayerNotificationManager playerNotificationManager = new PlayerNotificationManager(
                this, CHANNEL_ID, NOTIFICATION_ID,
                createMediaDescriptionAdapter(),
                null);

        playerNotificationManager.setNotificationListener(new PlayerNotificationManager.NotificationListener() {
            @Override
            public void onNotificationStarted(int notificationId, Notification notification) {
                Log.e(TAG, "onNotificationStarted: " + "showing notification");

                startForeground(notificationId, notification);
            }

            @Override
            public void onNotificationCancelled(int notificationId) {
                Log.e(TAG, "onNotificationStarted: " + "notification cancelled");
                stopForeground(true);
            }
        });
        playerNotificationManager.setColor(Color.BLACK);
        playerNotificationManager.setColorized(true);
        playerNotificationManager.setUseChronometer(true);
        playerNotificationManager.setSmallIcon(R.drawable.exo_notification_small_icon);
        playerNotificationManager.setBadgeIconType(NotificationCompat.BADGE_ICON_NONE);
        playerNotificationManager.setOngoing(true);
        // playerNotificationManager.setBadgeIconType(NotificationCompat.BadgeIconType);
        playerNotificationManager.setPlayer(mediaPlayer.getMediaPlayer());

    }

    private void startPlayer() {
        mediaPlayer = CustomMediaPlayer.getInstance(getApplicationContext());
        mediaPlayer.init();
    }
}
