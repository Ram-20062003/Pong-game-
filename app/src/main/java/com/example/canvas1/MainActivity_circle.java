package com.example.canvas1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.Customview_circle;

public class MainActivity_circle extends AppCompatActivity {
    Customview_circle customview_circle;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        customview_circle=new Customview_circle(this);
        /*Intent intent=new Intent(MainActivity.this,firstpage.class);
        startActivity(intent);*/
        System.exit(0);
        customview_circle.timer.cancel();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_circle);
        customview_circle=new Customview_circle(this);

    }
}
