package com.example.canvas1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Views.MultiCustomview;

public class MainActivity2 extends AppCompatActivity {
    MultiCustomview multiCustomview;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        multiCustomview=new MultiCustomview(this);
        Intent intent=new Intent(MainActivity2.this,firstpage.class);
        startActivity(intent);
        multiCustomview.timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mode);
        multiCustomview=new MultiCustomview(this);
    }
}
