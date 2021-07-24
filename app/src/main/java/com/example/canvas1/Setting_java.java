package com.example.canvas1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Setting_java extends AppCompatActivity {
    Button b_circle;
    Button b_square;
    Button b_save;
    Button b_home;
    public int choose=1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        b_circle=(Button)findViewById(R.id.setting_circle);
        b_square=(Button)findViewById(R.id.setting_square);
        b_save=(Button)findViewById(R.id.save);
        b_home=(Button)findViewById(R.id.home1);
        b_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose=2;

            }
        });

        b_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose=1;

            }
        });
       /* if(choose==2)
        {
            b_circle.setBackgroundResource(R.drawable.styling_setcircle);
            b_square.setBackgroundResource(R.drawable.styling_square);
        }
        if(choose==1)
        {
            b_square.setBackgroundResource(R.drawable.styling_setsquare);
            b_circle.setBackgroundResource(R.drawable.styling_circle);
        }*/
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Setting_java.this,firstpage.class);
                intent.putExtra("choose",choose);
                startActivity(intent);


            }
        });
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               System.exit(0);

            }
        });
    }

}

