package pl.pusb.kaniewski.demothreads1;

import android.app.Application;
import android.os.StrictMode;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static String exampleURL = "http://archfail.lan/android.php?platform=android";
    public static String response;

    /*static {
        try {
            response = RequestHandler.sendGet(MyApplication.exampleURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

}
