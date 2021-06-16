package production.jattbrand.musicapp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import production.jattbrand.musicapp.ui.splash.SplashActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtils {

    private final Context context;
    private NotificationManager mNotificationManager;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    private static String CHANNEL_ID = "channel123";
    private String TAG = "NotificationUtils ALarm";

    public NotificationUtils(Context context) {
        this.context = context;
    }

    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create channel only if it is not already created
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }

    public void createNotification(String msg) {

        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        //2.Build Notification with NotificationCompat.Builder
        NotificationCompat.Builder notificationBuilder;

        createNotificationChannel(mNotificationManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);

        } else {
            notificationBuilder = new NotificationCompat.Builder(context);
        }

        Intent intent = new Intent(context, SplashActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = notificationBuilder.setContentTitle("Results")   //Set the title of Notification
                .setContentText("Click to see the Result of " + msg)    //Set the text for notification
                .setSmallIcon(android.R.drawable.ic_menu_view)
                .setAutoCancel(true)    //Close notification after click
                .setContentIntent(pendingIntent)//Set the icon
                .build();

        //Send the notification.
        if (notification != null)
            mNotificationManager.notify(1, notification);

    }

   }
