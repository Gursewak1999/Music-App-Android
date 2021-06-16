package production.jattbrand.musicapp.misc;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.Date;
import java.util.List;

import production.jattbrand.musicapp.utils.DateUtils;

public class TimeEssentials {

    private static String RESUMED = "resumed";
    private static String RUNNING = "running";
    private static String PAUSED = "paused";

    private static String currentState = RUNNING;

    private static String TAG = "TimeEssentials";

    public static class BackgroundThread extends Thread {

        private Context context;

        public BackgroundThread(Context context, Date date) {
            this.context = context;
            DateUtils.currentDate = date;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DateUtils.currentDate = DateUtils.addSecondsToDate(DateUtils.currentDate, 1);
            while (true) {

                if (isAppIsInBackground(context)) {
                    //is Running
                    if (currentState.equals(PAUSED))
                        currentState = RESUMED;
                    if (currentState.equals(RESUMED))
                        currentState = RUNNING;
                } else {
                    //has stopped
                    currentState = PAUSED;
                }
                if (currentState.equals(RUNNING)) {
                    tick();
                }
            }

        }

        private void tick() {
            DateUtils.currentDate = DateUtils.addSecondsToDate(DateUtils.currentDate, 1);
            Log.e(TAG, "tick : time : " + DateUtils.getDate(DateUtils.currentDate.getTime(), DateUtils.SIMPLE_DATE_TIME_FORMAT));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public boolean isAppIsInBackground(Context context) {
            boolean isInBackground = true;
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = null;      // this line shows error as "Field requires API level 29 (current min is 16): android.app.TaskInfo#topActivity"*
                componentInfo = taskInfo.get(0).topActivity;
                if (componentInfo.getPackageName().equals(context.getPackageName())) {
                    isInBackground = false;
                }
            } else {
                List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            }

            return isInBackground;
        }
    }

    public String getAppState() {

        return currentState;
    }

}