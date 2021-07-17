package com.example.canvas1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.MultiCustomview_circle;

public class MainActivity2_circle extends AppCompatActivity {
    MultiCustomview_circle multiCustomview_circle;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        multiCustomview_circle=new MultiCustomview_circle(this);
        /*Intent intent=new Intent(MainActivity2.this,firstpage.class);
        startActivity(intent);*/
        System.exit(0);
        multiCustomview_circle.timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mode_circle);
        multiCustomview_circle=new MultiCustomview_circle(this);
    }
}
