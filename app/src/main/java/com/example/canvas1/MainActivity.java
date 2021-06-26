package com.example.canvas1;

import androidx.appcompat.app.AppCompatActivity;
import Views.Customview;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static Views.Customview.*;

public class MainActivity extends AppCompatActivity {
Customview customview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customview=new Customview(this);
    }

}