package pl.pusb.kaniewski.demothreads1;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static String response = "test";
    public static String exampleURL = "";
    ExecutorService executorService = Executors.newFixedThreadPool(2);
}
