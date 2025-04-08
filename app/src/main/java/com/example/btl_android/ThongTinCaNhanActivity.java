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
import com.example.btl_android.DAO.ThongTinKhachHangDAO;
import com.example.btl_android.DTO.TaiKhoanDTO;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    private ImageView imgBackThongTinCaNhan;
    private EditText edtTenKhachHang, edtEmail, edtSoDienThoai, edtGioiTinh, edtNgaySinh, edtTenDangNhapUser;
    private AppCompatButton btnLuThongTin;

    private ThongTinKhachHangDAO thongTinKhachHangDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private List<TaiKhoanDTO> listTk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);



//        Ánh xạ các View Layout
        imgBackThongTinCaNhan = findViewById(R.id.imgBackThongTinCaNhan);
        imgBackThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
// hien thi thong tin ten dăng nhập lên EditText
        edtTenDangNhapUser = findViewById(R.id.edtTenDangNhapUser);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String loggEdtTenDangNhap = sharedPreferences.getString("userName", "Default Name");
        edtTenDangNhapUser.setText(loggEdtTenDangNhap);

        edtTenKhachHang = findViewById(R.id.edt_ho_ten);
        edtEmail = findViewById(R.id.edt_email);
        edtSoDienThoai = findViewById(R.id.edt_sdt);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        btnLuThongTin = findViewById(R.id.btn_doi_pass);


        taiKhoanDAO = new TaiKhoanDAO(this);
        listTk = taiKhoanDAO.getAllThongTin();

        //Lấy thông tin hiển thị lên Edittext
        TaiKhoanDTO taiKhoanDTO = taiKhoanDAO.getThongTinTheoTenDangNhap(loggEdtTenDangNhap);
        if (taiKhoanDTO != null){
            edtTenKhachHang.setText(taiKhoanDTO.getTenUser());
            edtEmail.setText(taiKhoanDTO.getEmail());
            edtSoDienThoai.setText(taiKhoanDTO.getSoDienThoai());
            edtGioiTinh.setText(taiKhoanDTO.getGioiTinh());
            edtNgaySinh.setText(taiKhoanDTO.getNgaySinh());
        }

        //lưu thông tin khách hàng vuwaf nhập

        btnLuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    TaiKhoanDTO idTaikhoan = new TaiKhoanDTO();
                    for (int i = 0; i < listTk.size(); i++) {
                        idTaikhoan = listTk.get(i);
                    }

                    String tenUser = edtTenKhachHang.getText().toString();
                    String email = edtEmail.getText().toString();
                    String soDienThoai = edtSoDienThoai.getText().toString();
                    String gioiTinh = edtGioiTinh.getText().toString();
                    String ngaySinh = edtNgaySinh.getText().toString();

                    idTaikhoan.setTenUser(tenUser);
                    idTaikhoan.setEmail(email);
                    idTaikhoan.setSoDienThoai(soDienThoai);
                    idTaikhoan.setGioiTinh(gioiTinh);
                    idTaikhoan.setNgaySinh(ngaySinh);

                    int kq = taiKhoanDAO.updateThongTin(idTaikhoan);
                    if (kq > 0) {
                        Toast.makeText(ThongTinCaNhanActivity.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                        listTk.clear();
                        listTk.addAll(taiKhoanDAO.getAllThongTin());
                        finish();
                    }else {
                        Toast.makeText(ThongTinCaNhanActivity.this, "Lưu thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }



    private boolean kiemTra(){
        if (edtTenKhachHang.getText().toString().equals("") || edtEmail.getText().toString().equals("")
        || edtSoDienThoai.getText().toString().equals("") || edtGioiTinh.getText().toString().equals("")
        || edtNgaySinh.getText().toString().equals("")){
            Toast.makeText(this, "Mời nhập thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}