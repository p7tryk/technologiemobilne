package pl.pusb.kaniewski.sensor;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends Activity implements SensorEventListener
{
	private static final String TAG = "MainAcitivity";

	private SensorManager sensorManager;
	Sensor accelerometr;
	Sensor gyroscope;
	Sensor magnetometr;
	private TextView accX;
	private TextView accY;
	private TextView accZ;
	private TextView gyroX;
	private TextView gyroY;
	private TextView gyroZ;
	private TextView magX;
	private TextView magY;
	private TextView magZ;
	private long lastUpdateAcc = 0;
	private long lastUpdateGyro = 0;
	private long lastUpdateMagnet = 0;
	private GraphView graph;
	int lastx = 0;
	LineGraphSeries<DataPoint> series;

	private void plotCreate(Bundle savedInstanceState)
	{
		// data
		series = new LineGraphSeries<DataPoint>();
		graph.addSeries(series);
		// customize a little bit viewport
		Viewport viewport = graph.getViewport();
		viewport.setYAxisBoundsManual(true);
		viewport.setMinY(-10);
		viewport.setMaxY(10);
		viewport.setScrollable(true);
		addEntry(-5);
		addEntry(5);

	}
	private void addEntry(float input)
	{
		Log.d(TAG, "addEntry(" + String.format("%.3f",input) +")");
		series.appendData(new DataPoint(lastx++,input), true, 10);
	}
	private void setupTextView(Bundle SavedInstanceState)
	{
		accX = findViewById(R.id.accX);
		accY = findViewById(R.id.accY);
		accZ = findViewById(R.id.accZ);
		gyroX = findViewById(R.id.gyroX);
		gyroY = findViewById(R.id.gyroY);
		gyroZ = findViewById(R.id.gyroZ);
		magX = findViewById(R.id.magX);
		magY = findViewById(R.id.magY);
		magZ = findViewById(R.id.magZ);
	}
	private void setupAcc(Bundle SavedInstanceState)
	{
		accelerometr = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(MainActivity.this, accelerometr, SensorManager.SENSOR_DELAY_NORMAL);
		Log.d(TAG, "oncreate: register accelerometr");
	}
	private void setupGyro(Bundle SavedInstanceState)
	{
		gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
		Log.d(TAG, "oncreate: register gyroscope");
	}
	private void setupMag(Bundle SavedInstanceState)
	{
		magnetometr = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		sensorManager.registerListener(MainActivity.this, magnetometr, SensorManager.SENSOR_DELAY_NORMAL);
		Log.d(TAG, "oncreate: register magnotomer");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		graph = (GraphView) findViewById(R.id.graph);
		plotCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Log.d(TAG, "oncreate: register sensors");
		setupTextView(savedInstanceState);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		setupAcc(savedInstanceState);
		setupGyro(savedInstanceState);
		setupMag(savedInstanceState);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i)
	{

	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent)
	{

		Sensor mySensor = sensorEvent.sensor;
		long curTime = System.currentTimeMillis();
		if (mySensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		{
			if ((curTime - lastUpdateMagnet) > 1000)
			{
				double x = sensorEvent.values[0];
				double y = sensorEvent.values[1];
				double z = sensorEvent.values[2];
				long diffTime = (curTime - lastUpdateMagnet);
				lastUpdateMagnet = curTime;
				//Log.d(TAG, "magnetometr: X:" + x + " Y" + y + " Z" + z);

				magX.setText("X:" + String.format("%.3f", x));
				magY.setText("Y:" + String.format("%.3f", y));
				magZ.setText("Z:" + String.format("%.3f", z));
			}

		}
		if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			if ((curTime - lastUpdateAcc) > 1000)
			{
				double x = sensorEvent.values[0];
				double y = sensorEvent.values[1];
				double z = sensorEvent.values[2];
				long diffTime = (curTime - lastUpdateAcc);
				lastUpdateAcc = curTime;
				//Log.d(TAG, "accelerometr: X:" + x + " Y" + y + " Z" + z);
				accX.setText("X:" + String.format("%.3f", x));
				accY.setText("Y:" + String.format("%.3f", y));
				accZ.setText("Z:" + String.format("%.3f", z));

				addEntry((float) x);
			}

		}
		if (mySensor.getType() == Sensor.TYPE_GYROSCOPE)
		{
			if ((curTime - lastUpdateGyro) > 1000)
			{
				double x = sensorEvent.values[0];
				double y = sensorEvent.values[1];
				double z = sensorEvent.values[2];
				long diffTime = (curTime - lastUpdateGyro);
				lastUpdateGyro = curTime;
				//Log.d(TAG, "zyroskop: X:" + x + " Y" + y + " Z" + z);
				gyroX.setText("X:" + String.format("%.3f", x));
				gyroY.setText("Y:" + String.format("%.3f", y));
				gyroZ.setText("Z:" + String.format("%.3f", z));
			}

		}

	}
}