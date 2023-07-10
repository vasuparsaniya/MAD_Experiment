package com.example.audio_video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int REQUEST_AUDIO_CAPTURE = 2;

    private Uri videoUri;
    private Uri audioUri;

    private Button recordVideoButton, recordAudioButton;
    private Button playVideoButton, playAudioButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordVideoButton = findViewById(R.id.recordButton);
        playVideoButton = findViewById(R.id.playButton);
        recordAudioButton = findViewById(R.id.recordButton1);
        playAudioButton = findViewById(R.id.playButton1);

        recordVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeVideoIntent();
            }
        });

        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        recordAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeAudioIntent();
            }
        });

        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private void dispatchTakeAudioIntent() {
        Intent takeAudioIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        if (takeAudioIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeAudioIntent, REQUEST_AUDIO_CAPTURE);
        }
    }

    private void playVideo() {
        if (videoUri != null) {
            Intent playVideoIntent = new Intent(Intent.ACTION_VIEW);
            playVideoIntent.setDataAndType(videoUri, "video/*");
            playVideoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(playVideoIntent);
        }
    }

    private void playAudio() {
        if (audioUri != null) {
            Intent playAudioIntent = new Intent(Intent.ACTION_VIEW);
            playAudioIntent.setDataAndType(audioUri, "audio/*");
            playAudioIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(playAudioIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = data.getData();
        }
        if (requestCode == REQUEST_AUDIO_CAPTURE && resultCode == RESULT_OK) {
            audioUri = data.getData();
        }
    }
}
