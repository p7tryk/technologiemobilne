package pl.pusb.kaniewski.myaplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity
{
	private static final String PLAYBACK_TIME = "play_time";
	private int mCurrentPosition = 0;

	private static final String VIDEO_SAMPLE = "league";
	private VideoView mVideoView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_display_message);

		mVideoView = findViewById(R.id.videoView);

		if (savedInstanceState != null)
		{
			mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
		}

		MediaController controller = new MediaController(this);
		controller.setMediaPlayer(mVideoView);
		mVideoView.setMediaController(controller);

	}


	@Override
	protected void onStart()
	{
		super.onStart();

		initializePlayer();
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		releasePlayer();
	}


	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
	}


	private Uri getMedia(String mediaName)
	{
		return Uri.parse("android.resource://" + getPackageName() +
				"/raw/" + mediaName);
	}

	private void initializePlayer()
	{
		Uri videoUri = getMedia(VIDEO_SAMPLE);
		mVideoView.setVideoURI(videoUri);

		if (mCurrentPosition > 0)
		{
			mVideoView.seekTo(mCurrentPosition);
		} else
		{
			mVideoView.seekTo(1);
		}

		mVideoView.start();
	}


	private void releasePlayer()
	{

		mVideoView.stopPlayback();
	}
}

