package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LienHeGopYActivity extends AppCompatActivity {

    private ImageView imgBackLienHeGopY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he_gop_yactivity);
        imgBackLienHeGopY = findViewById(R.id.imgBackLienHeGopY);
        imgBackLienHeGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}