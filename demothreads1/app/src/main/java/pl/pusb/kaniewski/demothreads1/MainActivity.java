package pl.pusb.kaniewski.demothreads1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import pl.pusb.kaniewski.demothreads1.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    RequestHandler request =  new RequestHandler(MyApplication.executorService);



    public void updateMain()
    {
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(MyApplication.response);
    }

    public void syncNetwork()
    {
         MyApplication.enableStrictMode();
                try {

                    MyApplication.response = RequestHandler.sendGet(MyApplication.exampleURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateMain();
    }

    public void asyncNetwork()
    {
        request.asyncGet();
        updateMain();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


        Button button = (Button) findViewById(R.id.buttonrefresh);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateMain();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                syncNetwork();
                Snackbar.make(view, MyApplication.response, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}