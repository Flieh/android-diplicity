package se.oort.diplicity;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

public class App extends MultiDexApplication {

    public App() {
        if (BuildConfig.DEBUG) {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                    Log.wtf("Diplicity", paramThrowable.getMessage(), paramThrowable);
                    System.exit(2); //Prevents the service/app from freezing
                }
            });
        }
    }

    public static String nanosToDuration(long nanos) {
        return minutesToDuration((int) ((nanos / (long) 1000000000) / (long) 60));
    }

    public static void firebaseCrashReport(String msg) {
        if (!BuildConfig.DEBUG) {
            FirebaseCrash.report(new RuntimeException(msg));
        }
        Log.e("Diplicity", msg);
    }

    public static void firebaseCrashReport(String msg, Throwable e) {
        if (!BuildConfig.DEBUG) {
            FirebaseCrash.report(new RuntimeException(msg, e));
        }
        Log.e("Diplicity", msg, e);
    }

    public static String minutesToDuration(int mins) {
        long days = mins / (60 * 24);
        long hours = (mins - (60 * 24 * days)) / 60;
        long minutes = mins - (60 * 24 * days) - (60 * hours);
        List<String> timeLabelList = new ArrayList<String>();
        if (days > 0) {
            timeLabelList.add("" + days + "d");
        }
        if (hours > 0) {
            timeLabelList.add("" + hours + "h");
        }
        if (minutes > 0) {
            timeLabelList.add("" + minutes + "m");
        }
        StringBuilder timeLabel = new StringBuilder();
        for (int i = 0; i < timeLabelList.size(); i++) {
            timeLabel.append(timeLabelList.get(i));
            if (i < timeLabelList.size() - 1) {
                timeLabel.append(", ");
            }
        }
        return timeLabel.toString();
    }

}
