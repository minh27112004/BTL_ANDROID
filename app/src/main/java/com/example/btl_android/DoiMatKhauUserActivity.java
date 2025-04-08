package com.example.btl_android;

import android.content.SharedPreferences;
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

public class DoiMatKhauUserActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtNewPassAgain;
    AppCompatButton btnDoiPass;
    private TaiKhoanDAO taiKhoanDAO;
    private List<TaiKhoanDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau_user);


        edtOldPass = findViewById(R.id.edt_pass_old);
        edtNewPass = findViewById(R.id.edt_pass_new);
        edtNewPassAgain = findViewById(R.id.edt_pass_new_agin);
        btnDoiPass = findViewById(R.id.btn_doi_pass);
        ImageView imgBackDoiMatKhau = findViewById(R.id.imgBackDoiMatKhau);

        imgBackDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        taiKhoanDAO = new TaiKhoanDAO(this);
        list = taiKhoanDAO.getAll();
        btnDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {

                    SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
                    String tenDangNhap = sharedPreferences.getString("tenDangNhap", "");
                    String matKhauMoi = edtNewPass.getText().toString();

                    int kq = taiKhoanDAO.updateMatKhau(tenDangNhap, matKhauMoi);

                    if (kq > 0) {

                        Toast.makeText(DoiMatKhauUserActivity.this, "Đổi mật khẩu thành công"
                                , Toast.LENGTH_SHORT).show();
                        edtNewPass.setText("");
                        edtOldPass.setText("");
                        edtNewPassAgain.setText("");
                    } else {

                        Toast.makeText(DoiMatKhauUserActivity.this, "Đổi mật khẩu thành công"
                                , Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });


    }

    private boolean validate() {

        if (edtOldPass.getText().length() == 0 || edtNewPass.getText().length() == 0 || edtNewPassAgain.getText().length() == 0) {
            Toast.makeText(this, "Mời nhập thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!taiKhoanDAO.isValidPassword(edtNewPass.getText().toString())){
            Toast.makeText(this, "Mật khẩu mới phải có chữ hoa đầu và kí tự đặc biệt!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edtNewPass.getText().toString().equals(edtNewPassAgain.getText().toString())) {
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String matKhauCu = sharedPreferences.getString("matKhau", "");

        if (!matKhauCu.equals(edtOldPass.getText().toString())) {

            Toast.makeText(this, "Mật khẩu cũ không khớp", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(matKhauCu.equals(edtNewPass.getText().toString())){
            Toast.makeText(this, "Mật khẩu mới phải khác mật khẩu cũ !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}