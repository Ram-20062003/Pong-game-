package com.example.canvas1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.Hard_mode;

public class MainActivity3 extends AppCompatActivity {
    Hard_mode hard_mode;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hard_mode = new Hard_mode(this);
        Intent intent=new Intent(MainActivity3.this,firstpage.class);
        startActivity(intent);
        hard_mode.mediaPlayer1.stop();
        hard_mode.timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hard_mode);
        hard_mode = new Hard_mode(this);

    }
}

