package pl.pusb.kaniewski.demothreads1;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.concurrent.Executor;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {


    private final Executor executor;
    //public final RequestCallback callback = new RequestCallback();

    public RequestHandler(Executor executor) {
        this.executor = executor;
    }
    interface RequestCallback
    {
        void onComplete();
    }

    public void asyncGet()
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("runnable","runnable");
                try {
                    MyApplication.response=sendGet(MyApplication.exampleURL);
                    //callback.onComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static String sendGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        Log.d("http","connection opened");
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        Log.d("http","Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "nothing";
        }
    }
}