package com.example.p2;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.AbstractMap;
import java.util.Map;

public class Gestures extends AppCompatActivity implements View.OnClickListener {

    VideoView videoViewObject;
    Button practiceCTA;
    Button playVideoCTA;
    static String userSelectedGesture;

    Map<String, Integer> gestureToMP4Mapping = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, Integer>("LightsOn", R.raw.lighton),
            new AbstractMap.SimpleEntry<String, Integer>("LightsOff", R.raw.lightoff),
            new AbstractMap.SimpleEntry<String, Integer>("FanOn",R.raw.fanon),
            new AbstractMap.SimpleEntry<String, Integer>("FanOff", R.raw.fanoff),
            new AbstractMap.SimpleEntry<String, Integer>("FanDown",R.raw.decreasefanspeed),
            new AbstractMap.SimpleEntry<String, Integer>("FanUp", R.raw.increasefanspeed),
            new AbstractMap.SimpleEntry<String, Integer>("SetThermo", R.raw.setthermo),
            new AbstractMap.SimpleEntry<String, Integer>("Num0", R.raw.h0),
            new AbstractMap.SimpleEntry<String, Integer>("Num1", R.raw.h1),
            new AbstractMap.SimpleEntry<String, Integer>("Num2", R.raw.h2),
            new AbstractMap.SimpleEntry<String, Integer>("Num3", R.raw.h3),
            new AbstractMap.SimpleEntry<String, Integer>("Num4", R.raw.h4),
            new AbstractMap.SimpleEntry<String, Integer>("Num5", R.raw.h5),
            new AbstractMap.SimpleEntry<String, Integer>("Num6", R.raw.h6),
            new AbstractMap.SimpleEntry<String, Integer>("Num7", R.raw.h7),
            new AbstractMap.SimpleEntry<String, Integer>("Num8", R.raw.h8),
            new AbstractMap.SimpleEntry<String, Integer>("Num9", R.raw.h9)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_guide);

        userSelectedGesture=getIntent().getStringExtra("user_chosen_gesture");

        practiceCTA = findViewById(R.id.upload_to_server);
        playVideoCTA = findViewById(R.id.record_user_gesture);

        practiceCTA.setOnClickListener(this);
        playVideoCTA.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.upload_to_server:
                navigateToUploadActivity();
                break;

            case R.id.record_user_gesture:
                playGestureVideos();
                break;

        }

    }

    public void navigateToUploadActivity(){
        Intent intent = new Intent(this, Gestures_uploading.class);
        intent.putExtra("user_chosen_gesture",userSelectedGesture);
        startActivity(intent);
    }


    public void playGestureVideos(){

        videoViewObject=findViewById(R.id.videoView);

        int gestureInt= gestureToMP4Mapping.get(userSelectedGesture);

        String videoPath="android.resource://" + getPackageName() + "/" + gestureInt;

        Log.i("GuideAct", videoPath);
        Uri uri=Uri.parse(videoPath);

        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoViewObject);

        videoViewObject.setMediaController(mediaController);
        videoViewObject.setVideoURI(uri);
        videoViewObject.requestFocus();
        videoViewObject.start();

    }

}
