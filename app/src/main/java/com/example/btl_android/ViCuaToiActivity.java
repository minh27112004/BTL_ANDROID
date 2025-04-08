package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViCuaToiActivity extends AppCompatActivity {

    private ImageView imgBackViCuaToi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_cua_toi);
        imgBackViCuaToi = findViewById(R.id.imgBackViCuaToi);
        imgBackViCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}