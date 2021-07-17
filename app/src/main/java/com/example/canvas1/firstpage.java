package com.example.canvas1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    TextView textView;
    int choose=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button);
        b5=(Button)findViewById(R.id.button4);
        Intent intent=getIntent();
        choose=intent.getIntExtra("choose",1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==1){
                Intent intent=new Intent(firstpage.this,MainActivity.class);
                startActivity(intent);}
                if(choose==2){
                    Intent intent=new Intent(firstpage.this,MainActivity_circle.class);
                    startActivity(intent);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==1){
                    Intent intent=new Intent(firstpage.this,MainActivity2.class);
                    startActivity(intent);}
                if(choose==2){
                    Intent intent=new Intent(firstpage.this,MainActivity2_circle.class);
                    startActivity(intent);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==1){
                    Intent intent=new Intent(firstpage.this,MainActivity3.class);
                    startActivity(intent);}
                if(choose==2){
                    Intent intent=new Intent(firstpage.this,MainActivity3_circle.class);
                    startActivity(intent);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
