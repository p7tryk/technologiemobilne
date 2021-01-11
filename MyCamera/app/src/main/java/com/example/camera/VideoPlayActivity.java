package com.example.camera;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        mVideoView = findViewById(R.id.videoView);

        Uri videoUrl= Uri.parse(getIntent().getExtras().getString("videoUrl"));
        mVideoView.setVideoPath(String.valueOf((videoUrl)));
        mVideoView.start();
    }
}