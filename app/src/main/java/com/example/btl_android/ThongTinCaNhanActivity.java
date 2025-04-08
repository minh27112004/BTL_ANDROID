package com.example.btl_android;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.btl_android.DAO.TaiKhoanDAO;
import com.example.btl_android.DTO.TaiKhoanDTO;

import java.util.Calendar;
import java.util.regex.Pattern;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    private ImageView imgBackThongTinCaNhan;
    private EditText edtTenKhachHang, edtEmail, edtSoDienThoai, edtGioiTinh, edtNgaySinh, edtTenDangNhapUser;
    private AppCompatButton btnLuThongTin;
    private TaiKhoanDAO taiKhoanDAO;

    // Biểu thức chính quy cho email và số điện thoại
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^(03|05|07|08|09)[0-9]{8}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        // Ánh xạ các View
        imgBackThongTinCaNhan = findViewById(R.id.imgBackThongTinCaNhan);
        imgBackThongTinCaNhan.setOnClickListener(v -> finish());

        edtTenDangNhapUser = findViewById(R.id.edtTenDangNhapUser);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String loggEdtTenDangNhap = sharedPreferences.getString("userName", "Default Name");
        edtTenDangNhapUser.setText(loggEdtTenDangNhap);

        edtTenKhachHang = findViewById(R.id.edt_ho_ten);
        edtEmail = findViewById(R.id.edt_email);
        edtSoDienThoai = findViewById(R.id.edt_sdt);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        btnLuThongTin = findViewById(R.id.btn_luu_thongtin);

        // DatePickerDialog
        edtNgaySinh.setOnClickListener(v -> showDatePickerDialog());
        edtNgaySinh.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        // Khởi tạo DAO và lấy thông tin tài khoản
        taiKhoanDAO = new TaiKhoanDAO(this);
        TaiKhoanDTO taiKhoanDTO = taiKhoanDAO.getThongTinTheoTenDangNhap(loggEdtTenDangNhap);

        if (taiKhoanDTO != null) {
            edtTenKhachHang.setText(taiKhoanDTO.getTenUser());
            edtEmail.setText(taiKhoanDTO.getEmail());
            edtSoDienThoai.setText(taiKhoanDTO.getSoDienThoai());
            edtGioiTinh.setText(taiKhoanDTO.getGioiTinh());
            edtNgaySinh.setText(taiKhoanDTO.getNgaySinh());
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin tài khoản", Toast.LENGTH_SHORT).show();
        }

        // Lưu thông tin
        btnLuThongTin.setOnClickListener(v -> {
            if (kiemTra()) {
                if (taiKhoanDTO == null) {
                    Toast.makeText(this, "Không thể lưu: Thông tin tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }

                taiKhoanDTO.setTenUser(edtTenKhachHang.getText().toString());
                taiKhoanDTO.setEmail(edtEmail.getText().toString());
                taiKhoanDTO.setSoDienThoai(edtSoDienThoai.getText().toString());
                taiKhoanDTO.setGioiTinh(edtGioiTinh.getText().toString());
                taiKhoanDTO.setNgaySinh(edtNgaySinh.getText().toString());

                int kq = taiKhoanDAO.updateThongTin(taiKhoanDTO);
                if (kq > 0) {
                    Toast.makeText(this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Lưu thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean kiemTra() {
        String tenKhachHang = edtTenKhachHang.getText().toString();
        String email = edtEmail.getText().toString();
        String soDienThoai = edtSoDienThoai.getText().toString();
        String gioiTinh = edtGioiTinh.getText().toString();
        String ngaySinh = edtNgaySinh.getText().toString();

        // Kiểm tra rỗng
        if (tenKhachHang.isEmpty() || email.isEmpty() || soDienThoai.isEmpty() ||
                gioiTinh.isEmpty() || ngaySinh.isEmpty()) {
            Toast.makeText(this, "Mời nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Kiểm tra định dạng email
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            edtEmail.requestFocus();
            return false;
        }

        // Kiểm tra định dạng số điện thoại
        if (!PHONE_PATTERN.matcher(soDienThoai).matches()) {
            Toast.makeText(this, "Số điện thoại không hợp lệ (10 số, bắt đầu bằng 03/05/07/08/09)",
                    Toast.LENGTH_SHORT).show();
            edtSoDienThoai.requestFocus();
            return false;
        }

        return true;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            edtNgaySinh.setText(selectedDate);
        }, year, month, day).show();
    }
}