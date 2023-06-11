package com.example.auted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class demoscreefour extends AppCompatActivity {
    Button msignup ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoscreefour);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        msignup = findViewById(R.id.btnSignup);
        ImageView img;
        img = (ImageView) findViewById(R.id.imagePolygonTwo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity();
            }
        });
        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(demoscreefour.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    public void Activity(){
        Intent intent = new Intent(demoscreefour.this, demoscreenthree.class);
        startActivity(intent);

    }
}