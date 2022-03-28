package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class welcome extends AppCompatActivity {
    VideoView vidello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        vidello=findViewById(R.id.bienvenida);

        vidello.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
        vidello.start();



    }
}