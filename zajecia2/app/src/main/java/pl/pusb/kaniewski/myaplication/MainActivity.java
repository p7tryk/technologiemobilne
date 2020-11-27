package pl.pusb.kaniewski.myaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE1 = "pl.pusb.kaniewski.MESSAGE1";
    public static final String EXTRA_MESSAGE2 = "pl.pusb.kaniewski.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "pl.pusb.kaniewski.MESSAGE3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view)
    {
        String message;
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        message = editText.getText().toString();
        double numer1 = Double.parseDouble(message);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        message = editText2.getText().toString();
        double numer2 = Double.parseDouble(message);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        message = editText3.getText().toString();
        double numer3 = Double.parseDouble(message);

        intent.putExtra(EXTRA_MESSAGE1, numer1);
        intent.putExtra(EXTRA_MESSAGE2, numer2);
        intent.putExtra(EXTRA_MESSAGE3, numer3);


        startActivity(intent);
    }
}