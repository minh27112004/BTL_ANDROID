package com.example.btl_android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.example.btl_android.DAO.TaiKhoanDAO;
import com.example.btl_android.DTO.TaiKhoanDTO;
import com.example.btl_android.Fragment.FragDonDatHomeUser;
import com.example.btl_android.Fragment.FragGioHangUser;
import com.example.btl_android.Fragment.FragTaiKhoanAdmin;
import com.example.btl_android.Fragment.FragTaiKhoanUser;
import com.example.btl_android.Fragment.FragTrangChuUser;
import com.example.btl_android.Fragment.FragmentDanhSachDonDatAdmin;
import com.example.btl_android.Fragment.FragmentThongKeAdmin;
import com.example.btl_android.Fragment.FragmentTrangChuAdmin;


public class MainActivity extends AppCompatActivity {


    private LinearLayout layoutTrangChuUser,layoutGioHangUser,layoutDonDatUser
                        ,layoutTaiKhoanUser;
    private TextView tvTrangChuUser,tvGioHangUser,tvDonDatUser,tvTaiKhoanUser;

    private LinearLayout layoutTrangChuAdmin,layoutThongKeAdmin,layoutDonDatAdmin,layoutTaiKhoanAdmin;
    private TextView tvTrangChuAdmin,tvThongKeAdmin,tvDonDatAdmin,tvTaiKhoanAdmin;
    private CardView layoutNavBottomAdmin,layoutNavBottomUser;
    private FragmentManager manager;
    public static TaiKhoanDTO currentAccount;
    public static String nameAccount;

    private int index = 1; //Khởi tạo biến index bằng 1 để gán cho từng icon có trong navbottom
    //Gui thong tin ten dang nhap qua user


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Ánh xạ layout navbuttom admin và user
        layoutNavBottomAdmin = findViewById(R.id.layoutNavBottomAdmin);
        layoutNavBottomUser = findViewById(R.id.layoutNavBottomUser);

        //Khoải tạo fragment menager
        manager = getSupportFragmentManager();

        //Phẩm quyền admin và user
        //Nhận tên đăng nhập
        String tenDangNhap = getIntent().getStringExtra("tenDangNhap");
        String matKhau = getIntent().getStringExtra("matKhau");
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        currentAccount = taiKhoanDAO.getThongTinTheoTenDangNhap(tenDangNhap);
        if (currentAccount != null) {
            nameAccount = tenDangNhap;
            MainActivity.currentAccount.setTenUser(tenDangNhap);
        }

        //xư ly lay ten dang nhap
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", tenDangNhap);
        editor.putString("PassOld", matKhau);
        editor.apply();


        assert tenDangNhap != null;
        if (tenDangNhap.equals("admin")) {

            layoutNavBottomAdmin.setVisibility(View.VISIBLE);
            layoutNavBottomUser.setVisibility(View.GONE);
            manager.beginTransaction().replace(R.id.fragContainerView,new FragmentTrangChuAdmin()).commit();
            layoutTrangChuAdmin = findViewById(R.id.layoutTrangChuAdmin);
            layoutThongKeAdmin = findViewById(R.id.layouThongKeAdmin);
            layoutDonDatAdmin = findViewById(R.id.layoutDonDatAdmin);
            layoutTaiKhoanAdmin = findViewById(R.id.layoutTaiKhoanAdmin);
            //textView
            tvTrangChuAdmin = findViewById(R.id.tvTrangChuAdmin);
            tvThongKeAdmin = findViewById(R.id.tvThongKeAdmin);
            tvDonDatAdmin = findViewById(R.id.tvDonDatAdmin);
            tvTaiKhoanAdmin = findViewById(R.id.tvTaiKhoanAdmin);

            //set onclick cho layout navbottom của admin

            //set onclick cho layout trang chủ admin
            layoutTrangChuAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 1) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragmentTrangChuAdmin()).commit();

                        tvThongKeAdmin.setVisibility(View.GONE);
                        tvDonDatAdmin.setVisibility(View.GONE);
                        tvTaiKhoanAdmin.setVisibility(View.GONE);


                        tvTrangChuAdmin.setVisibility(View.VISIBLE);
                        layoutTrangChuAdmin.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        layoutThongKeAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        layoutTrangChuAdmin.startAnimation(scaleAnimation);

                        index = 1;

                    }


                }
            });


            //set onclick cho layout thống kê admin
            layoutThongKeAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 2) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragmentThongKeAdmin()).commit();

                        tvTrangChuAdmin.setVisibility(View.GONE);
                        tvDonDatAdmin.setVisibility(View.GONE);
                        tvTaiKhoanAdmin.setVisibility(View.GONE);


                        tvThongKeAdmin.setVisibility(View.VISIBLE);
                        layoutThongKeAdmin.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        layoutTrangChuAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        layoutThongKeAdmin.startAnimation(scaleAnimation);

                        index = 2;

                    }


                }
            });


            //set onclick cho layout đơn đặt admin
            layoutDonDatAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 3) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragmentDanhSachDonDatAdmin()).commit();

                        tvTrangChuAdmin.setVisibility(View.GONE);
                        tvThongKeAdmin.setVisibility(View.GONE);
                        tvTaiKhoanAdmin.setVisibility(View.GONE);


                        tvDonDatAdmin.setVisibility(View.VISIBLE);
                        layoutDonDatAdmin.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        layoutTrangChuAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutThongKeAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        layoutDonDatAdmin.startAnimation(scaleAnimation);

                        index = 3;

                    }

                }
            });


            //set onclick cho layout tài khoản admin
            layoutTaiKhoanAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 4) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragTaiKhoanAdmin()).commit();

                        tvTrangChuAdmin.setVisibility(View.GONE);
                        tvThongKeAdmin.setVisibility(View.GONE);
                        tvDonDatAdmin.setVisibility(View.GONE);


                        tvTaiKhoanAdmin.setVisibility(View.VISIBLE);
                        layoutTaiKhoanAdmin.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        layoutTrangChuAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutThongKeAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatAdmin.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        layoutTaiKhoanAdmin.startAnimation(scaleAnimation);

                        index = 4;

                    }

                }
            });

        }else {

            layoutNavBottomAdmin.setVisibility(View.GONE);
            layoutNavBottomUser.setVisibility(View.VISIBLE);
            manager.beginTransaction().replace(R.id.fragContainerView, new FragTrangChuUser()).commit();


            //Ánh xạ layout và textview cho navbottom của user
            //Layout
            layoutTrangChuUser = findViewById(R.id.layoutTrangChuUser);
            layoutGioHangUser = findViewById(R.id.layouGioiHangUser);
            layoutDonDatUser = findViewById(R.id.layoutDonDatUser);
            layoutTaiKhoanUser = findViewById(R.id.layoutTaiKhoanUser);
            //textView
            tvTrangChuUser = findViewById(R.id.tvTrangChuUser);
            tvGioHangUser = findViewById(R.id.tvGioHangUser);
            tvDonDatUser = findViewById(R.id.tvDonDatUser);
            tvTaiKhoanUser = findViewById(R.id.tvTaiKhoanUser);


            //Sửa lý sự kiện onclick cho từng layout trong navbottom của user
            //set sự kiên onclick cho layout trang chủ user
            layoutTrangChuUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 1) {

                        //Thêm fragTrangChu
                        manager.beginTransaction().replace(R.id.fragContainerView, new FragTrangChuUser()).commit();


                        //Ẩn đi các textView của các icon không đc click
                        tvGioHangUser.setVisibility(View.GONE);
                        tvDonDatUser.setVisibility(View.GONE);
                        tvTaiKhoanUser.setVisibility(View.GONE);

                        //Hiện lên textView của icon trang chủ và bg của icon trang chủ
                        tvTrangChuUser.setVisibility(View.VISIBLE);
                        layoutTrangChuUser.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        //Ẩn đi các layout của các icon khác trong navbottom không được click
                        layoutGioHangUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        //Tạo animation
                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setDuration(200);
                        layoutTrangChuUser.startAnimation(scaleAnimation);


                        index = 1;

                    }


                }
            });

            //set sự kiên onclick cho layout giỏ hàng user
            layoutGioHangUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (index != 2) {

                        //Gắn fragment GioHang
                        manager.beginTransaction().replace(R.id.fragContainerView, new FragGioHangUser()).commit();


                        //Ẩn đi các textView của các icon không đc click
                        tvTrangChuUser.setVisibility(View.GONE);
                        tvDonDatUser.setVisibility(View.GONE);
                        tvTaiKhoanUser.setVisibility(View.GONE);

                        //Hiện lên textView của icon trang chủ và bg của icon trang chủ
                        tvGioHangUser.setVisibility(View.VISIBLE);
                        layoutGioHangUser.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        //Ẩn đi các layout của các icon khác trong navbottom không được click
                        layoutTrangChuUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        //Tạo animation
                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setDuration(200);
                        layoutGioHangUser.startAnimation(scaleAnimation);


                        index = 2;

                    }

                }
            });


            //set sự kiên onclick cho layout đơn đặt user
            layoutDonDatUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 3) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragDonDatHomeUser()).commit();

                        //Ẩn đi các textView của các icon không đc click
                        tvTrangChuUser.setVisibility(View.GONE);
                        tvGioHangUser.setVisibility(View.GONE);
                        tvTaiKhoanUser.setVisibility(View.GONE);

                        //Hiện lên textView của icon trang chủ và bg của icon trang chủ
                        tvDonDatUser.setVisibility(View.VISIBLE);
                        layoutDonDatUser.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        //Ẩn đi các layout của các icon khác trong navbottom không được click
                        layoutTrangChuUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutGioHangUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutTaiKhoanUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        //Tạo animation
                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setDuration(200);
                        layoutDonDatUser.startAnimation(scaleAnimation);


                        index = 3;

                    }

                }
            });

            //set sự kiên onclick cho layout tài khoản user
            layoutTaiKhoanUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (index != 4) {

                        manager.beginTransaction().replace(R.id.fragContainerView, new FragTaiKhoanUser()).commit();

                        //Ẩn đi các textView của các icon không đc click
                        tvTrangChuUser.setVisibility(View.GONE);
                        tvGioHangUser.setVisibility(View.GONE);
                        tvDonDatUser.setVisibility(View.GONE);

                        //Hiện lên textView của icon trang chủ và bg của icon trang chủ
                        tvTaiKhoanUser.setVisibility(View.VISIBLE);
                        layoutTaiKhoanUser.setBackgroundResource(R.drawable.custom_seclected_nav_bottom);

                        //Ẩn đi các layout bg của các icon khác trong navbottom không được click
                        layoutTrangChuUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutGioHangUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        layoutDonDatUser.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                        //Tạo animation
                        ScaleAnimation scaleAnimation =
                                new ScaleAnimation(0.8f, 1.0f, 1f, 1f
                                        , Animation.RELATIVE_TO_SELF, 0.0f
                                        , Animation.RELATIVE_TO_SELF, 0.0f);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setDuration(200);
                        layoutTaiKhoanUser.startAnimation(scaleAnimation);


                        index = 4;

                    }

                }
            });




        }

    }
}