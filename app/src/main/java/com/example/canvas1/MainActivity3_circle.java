package com.example.canvas1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.Hard_mode_circle;

public class MainActivity3_circle extends AppCompatActivity {
    Hard_mode_circle hard_mode_circle;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hard_mode_circle = new Hard_mode_circle(this);
       /* Intent intent=new Intent(MainActivity3.this,firstpage.class);
        startActivity(intent);*/
        System.exit(0);
        hard_mode_circle.mediaPlayer1.stop();
        hard_mode_circle.timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hard_mode_circle);
        hard_mode_circle = new Hard_mode_circle(this);

    }
}

