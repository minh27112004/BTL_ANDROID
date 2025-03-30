package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;

import com.example.btl_android.DAO.TaiKhoanDAO;
import com.example.btl_android.DTO.TaiKhoanDTO;

public class LayLaiMatKhauActivity extends AppCompatActivity {

    private EditText edMatKhauMoi,edLaiMatKhauMoi;
    private AppCompatButton btnDoiMatKhau;
    private TaiKhoanDAO taiKhoanDAO;
    private List<TaiKhoanDTO> list;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_lai_mat_khau);

        //Ánh xạ view
        ivBack = findViewById(R.id.ivBackDoiMatKhau);
        edLaiMatKhauMoi = findViewById(R.id.edLaiMatKhauMoiLayLaiMatKhau);
        edMatKhauMoi = findViewById(R.id.edMatKhauMoiLayLaiMatKhau);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhacLayLaiMatKhau);
        taiKhoanDAO = new TaiKhoanDAO(this);
        list = taiKhoanDAO.getAll();

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()) {

                    String tenDangNhap = getIntent().getStringExtra("tenDangNhap");
                    String matKhauMoi = edMatKhauMoi.getText().toString();


                    int kq = taiKhoanDAO.updateMatKhau(tenDangNhap,matKhauMoi);

                    if (kq > 0) {

                        Toast.makeText(LayLaiMatKhauActivity.this, "Đổi mât khẩu thành công"
                                , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LayLaiMatKhauActivity.this, LoginSigUpActivity.class));
                        finish();


                    }else {

                        Toast.makeText(LayLaiMatKhauActivity.this, "Đổi mật khẩu thất bại"
                                , Toast.LENGTH_SHORT).show();

                    }



                }


            }

            private boolean kiemTra() {

                if (edMatKhauMoi.getText().toString().equals("")
                        ||edLaiMatKhauMoi.getText().toString().equals("")) {

                    Toast.makeText(LayLaiMatKhauActivity.this, "Mời nhập thông tin"
                            , Toast.LENGTH_SHORT).show();
                    return false;

                }

                if (edMatKhauMoi.getText().toString().length() < 5 ) {

                    Toast.makeText(LayLaiMatKhauActivity.this
                            , "Mật khẩu mới phải có trên 5 ký tự", Toast.LENGTH_SHORT).show();
                    return false;

                }

                if (!edMatKhauMoi.getText().toString().equals(edLaiMatKhauMoi.getText().toString())) {

                    Toast.makeText(LayLaiMatKhauActivity.this, "Mật khẩu không khớp"
                            , Toast.LENGTH_SHORT).show();
                    return false;

                }



                return true;

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LayLaiMatKhauActivity.this,XacNhanTaiKhoanActivity.class));
                finish();

            }
        });



    }

}
