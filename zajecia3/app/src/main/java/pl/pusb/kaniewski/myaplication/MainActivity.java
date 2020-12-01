package pl.pusb.kaniewski.myaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

	public static final String EXTRA_MESSAGE1 = "pl.pusb.kaniewski.MESSAGE1";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendMessage(View view)
	{
        final AssetManager mgr = getAssets();
        displayFiles(mgr, "/raw");


        String message;
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.editText);
		message = editText.getText().toString();


		intent.putExtra(EXTRA_MESSAGE1, message);



		startActivity(intent);


	}

	void displayFiles(AssetManager mgr, String path)
	{
		try
		{
			String list[] = mgr.list(path);
			if (list != null)
				for (int i = 0; i < list.length; ++i)
				{
					Log.v("Assets:", path + "/" + list[i]);
					displayFiles(mgr, path + "/" + list[i]);
				}
		} catch (IOException e)
		{
			Log.v("List error:", "can't list" + path);
		}

	}
}