package com.example.btl_android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_android.Adapter.AdapterViewPagerLoginSigUp;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class LoginSigUpActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private AdapterViewPagerLoginSigUp adapterViewPagerLoginSigUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sig_up);

        //Ánh xạ
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager2 = findViewById(R.id.myViewPager2);

        //Khởi tạo Adapter
        adapterViewPagerLoginSigUp = new AdapterViewPagerLoginSigUp(this);

        //set adapter cho viewPager2
        viewPager2.setAdapter(adapterViewPagerLoginSigUp);

        //Tạo tabLayout
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){

                    case 0:
                        tab.setText("Đăng nhập");
                        break;
                    case 1:
                        tab.setText("Đăng ký");
                        break;

                }


            }
        });
        mediator.attach();


    }
}