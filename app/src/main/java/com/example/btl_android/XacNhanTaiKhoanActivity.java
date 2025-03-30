package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.btl_android.DAO.TaiKhoanDAO;

public class XacNhanTaiKhoanActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText edTenDangNhap;
    private AppCompatButton btnXacNhanTaiKhoan;
    private TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_tai_khoan);

        edTenDangNhap = findViewById(R.id.edXacNhanTenDangNhap);
        ivBack = findViewById(R.id.tvBackXacNhanTaiKhoan);
        btnXacNhanTaiKhoan = findViewById(R.id.btnXacNhanTaiKhoan);
        taiKhoanDAO = new TaiKhoanDAO(this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(XacNhanTaiKhoanActivity.this, LoginSigUpActivity.class));
                finish();


            }
        });
        btnXacNhanTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()) {

                    String tenDangNhap = edTenDangNhap.getText().toString();

                    boolean kq = taiKhoanDAO.checkUserName(tenDangNhap);

                    if (kq) {

                        Intent intent = new Intent(XacNhanTaiKhoanActivity.this,LayLaiMatKhauActivity.class);
                        intent.putExtra("tenDangNhap",tenDangNhap);
                        startActivity(intent);
                        finish();

                    }else {

                        Toast.makeText(XacNhanTaiKhoanActivity.this, "Tài khoản không tồn tại"
                                ,Toast.LENGTH_SHORT).show();

                    }


                }



            }

            private boolean kiemTra() {

                if (edTenDangNhap.getText().toString().equals("")) {

                    Toast.makeText(XacNhanTaiKhoanActivity.this, "Mời nhập tài khoản"
                            , Toast.LENGTH_SHORT).show();
                    return false;

                }


                return true;

            }
        });



    }
}