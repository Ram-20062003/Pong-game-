package com.example.canvas1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.Customview;

public class MainActivity extends AppCompatActivity {
Customview customview;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        customview=new Customview(this);
        /*Intent intent=new Intent(MainActivity.this,firstpage.class);
        startActivity(intent);*/
        System.exit(0);
        customview.timer.cancel();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customview=new Customview(this);

    }
}
