package production.jattbrand.musicapp.misc;
import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;
import saschpe.android.customtabs.CustomTabsActivityLifecycleCallbacks;

public class MyApp extends Application {

    private static final String TAG = "MyApplication";
    private static MyApp instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        new Thread(() -> {
            try {
                getInternetTimeAndSync();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(600);
                    getInternetTimeAndSync();
                } catch (InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        init();
    }

    public static void getInternetTimeAndSync() throws IOException {

        String TIME_SERVER = "time-a.nist.gov";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);

        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
        Date time = new Date(returnTime);

        new TimeEssentials.BackgroundThread(getInstance().getApplicationContext(), time).start();
    }

    private void init() {
        //enable automatic time update

        // This line needs to be executed before any usage of EmojiTextView, EmojiEditText or EmojiButton.
        // EmojiManager.install(new GoogleEmojiProvider());
        //TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Alegreya_Bold.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
       // registerActivityLifecycleCallbacks(new CustomTabsActivityLifecycleCallbacks());
    }

    public static Context getInstance() {
        return instance;
    }
}
