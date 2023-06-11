package com.example.auted;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gamelist extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelist);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button arrow = findViewById(R.id.button);
         arrow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(gamelist.this, welcomScreen.class);
                 startActivity(intent);

             }
         });
//
//        Button mem = findViewById(R.id.button3);
//
//        mem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(gamelist.this, memory.class);
//                startActivity(intent);
//
//            }
//        });
//
//        Button txt = findViewById(R.id.button4);
//
//        txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(gamelist.this, rowyourboat.class);
//                startActivity(intent);
//
//            }
//        });
//        Button hh = findViewById(R.id.button2);
//
//        hh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(gamelist.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        Button bb = findViewById(R.id.button1);
//
//        bb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(gamelist.this, motor.class);
//                startActivity(intent);
//
//            }
//        });
    }
}
