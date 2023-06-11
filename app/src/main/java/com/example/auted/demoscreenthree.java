package com.example.auted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class demoscreenthree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoscreenthree);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ImageView img;
        img = (ImageView) findViewById(R.id.imagePolygonOne);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        ImageView image;
        image = (ImageView) findViewById(R.id.imagePolygonTwo);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity();
            }
        });
    }
    public void openActivity(){
        Intent intent = new Intent(demoscreenthree.this,demoscreefour.class);
        startActivity(intent);
    }
    public void Activity(){
        Intent intent1 = new Intent(demoscreenthree.this, demoscreentwo.class);
        startActivity(intent1);

    }
}