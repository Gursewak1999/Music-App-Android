package production.jattbrand.musicapp.utils;

import android.util.Log;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import production.jattbrand.musicapp.misc.MyApp;

public class DateUtils {

    public static final String SIMPLE_DATE_FORMAT = "dd/MM/YYYY";
    public static final String SIMPLE_TIME_FORMAT = "HH:mm a";
    public static final String SIMPLE_DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss a";
    private static final String TAG = "DateUtils";
    public static Date currentDate;

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = new Date(milliSeconds);
        return formatter.format(date.getTime());
    }

    public static String parseStringDate(String dateToConvert, String inputDateFormat, String outputDateFormat) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat, Locale.getDefault());
        try {
            Date date = inputFormat.parse(dateToConvert);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateToConvert;
        }
    }

    public static String getDayOfMonthSuffix(final int n) {
        if(n >= 1 && n <= 31)
        {
            Log.e(TAG, "getDayOfMonthSuffix: \"illegal day of month: \" + n" );
            return "";
        }
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static String getAge(String dob, String inputDateFormat) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault());

        try {
            Date dobDate = inputFormat.parse(dob);
            Date currentDate = new Date(System.currentTimeMillis());
            String dobYear = outputFormat.format(dobDate);
            String currentYear = outputFormat.format(currentDate);
            String[] splitDobDate = dobYear.split("/");
            String[] splitCurrentDate = currentYear.split("/");

            int year = Integer.parseInt(splitCurrentDate[2]) - Integer.parseInt(splitDobDate[2]);

            if (year == 0) {
                int month = Integer.parseInt(splitCurrentDate[1]) - Integer.parseInt(splitDobDate[1]);
                if (month == 0) {
                    int day = Integer.parseInt(splitCurrentDate[0]) - Integer.parseInt(splitDobDate[0]);
                    if (day == 0) {
                        return "0 Yrs";
                    } else {
                        return day + " Days";
                    }
                } else {
                    return month + " Months";

                }

            } else {
                return year + " Yrs";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "0 Yrs";
        }
    }

    public static String getAMPM(int hourOfDay, int minute) {
        String time = null;
        if (hourOfDay > 12) {
            time = (hourOfDay - 12) + ":" + (minute + "PM");
        }
        if (hourOfDay == 12) {
            time = "12" + ":" + (minute + "PM");
        }
        if (hourOfDay < 12) {
            time = hourOfDay + ":" + (minute + "AM");
        }
        return time;
    }

    public static String get24HourFormat(String time) {
        String inputPattern = "hh:mm a";
        String outputPattern = "HH:mm";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        try {
            return outputFormat.format(inputFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String dateDiffrence(String today, String lastDate) {

        //Specify the data format
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        long diff = 0;

        try {

            //Convert to Date
            Date startDate = df.parse(today);
            Calendar c1 = Calendar.getInstance();
            //Change to Calendar Date
            c1.setTime(startDate);

            //Convert to Date
            Date endDate = df.parse(lastDate);
            Calendar c2 = Calendar.getInstance();
            //Change to Calendar Date
            c2.setTime(endDate);

            //get Time in milli seconds
            long ms1 = c1.getTimeInMillis();
            long ms2 = c2.getTimeInMillis();
            //get difference in milli seconds
            diff = ms2 - ms1;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Find number of days by dividing the mili seconds
        int diffInDays = (int) (diff / (24 * 60 * 60 * 1000));
        System.out.println("Number of days difference is: " + diffInDays);

        return "" + diffInDays;
        //To get number of seconds diff/1000
        //To get number of minutes diff/(1000 * 60)
        //To get number of hours diff/(1000 * 60 * 60)

    }

    public static long nowTimestamp() {

        if (currentDate == null) {
            new Thread(() -> {
                try {
                    MyApp.getInternetTimeAndSync();
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(600);
                        MyApp.getInternetTimeAndSync();
                    } catch (InterruptedException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return currentDate.getTime();
    }

    public static long daysPlusTimestamp(int i) {
        return nowTimestamp() + i * 86400000;
    }

    public static long toTimestamp(Date date) {
        return date.getTime();
    }

    public static Date toDate(long time) {
        return new Date(time);
    }

    public static long toTimestamp(int year, int month, int dayOfMonth, int hourOfDay, int minute) {

        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String date_str = year + "-" + month + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00";
        Date date = new Date();
        try {
            date = new SimpleDateFormat(dateFormat, Locale.getDefault()).parse(date_str);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        return new Timestamp(date.getTime()).getTime();
    }

    public static Date minus15MinutesToDate(Date beforeTime) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        long curTimeInMs = beforeTime.getTime();
        return new Date(curTimeInMs - (15 * ONE_MINUTE_IN_MILLIS));
    }

    public static Date addMintuesToDate(Date beforeTime, int minutes) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        long curTimeInMs = beforeTime.getTime();
        return new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
    }

    public static Date addSecondsToDate(Date beforeTime, int seconds) {
        final long ONE_MINUTE_IN_MILLIS = 1000;
        long curTimeInMs = beforeTime.getTime();
        return new Date(curTimeInMs + (seconds * ONE_MINUTE_IN_MILLIS));
    }

    public static Date minusHoursToDate(int hour, Date beforeTime) {
        final long ONE_HOUR_IN_MILLIS = 3600000;
        long curTimeInMs = beforeTime.getTime();
        return new Date(curTimeInMs - (hour * ONE_HOUR_IN_MILLIS));
    }

    public static String toTime(long sec) {
        String am_pm = " AM";

        int hour = (int) (sec / 3600);
        int min = (int) ((sec % 3600) / 60);
        if (hour > 12) {
            hour = hour - 1;
            am_pm = " PM";
        }

        if (hour == 0)
            hour = 12;
        return (hour < 10 ? "0" : "") + hour + ":" + (min < 10 ? "0" : "") + min + am_pm;
    }

    public static String getDayString(int i) {
        switch (i) {
            case 0:
                return "MON";
            case 1:
                return "TUE";
            case 2:
                return "WED";
            case 3:
                return "THR";
            case 4:
                return "FRI";
            case 5:
                return "SAT";
        }
        return null;
    }
}
