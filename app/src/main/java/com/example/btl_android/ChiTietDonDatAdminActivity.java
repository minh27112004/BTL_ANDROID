package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;

public class ChiTietDonDatAdminActivity extends AppCompatActivity {

    ImageView imgBackChiTietAdmin;
    TextView txtTenKh, txtSoDienThoai, txtDiaChi, txtTenSanPham, txtThoiGian, txtTrangThai, txtTongTien, tvMaDonDat;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    List<DonDatUserDTO> list;
    DonDatUserDAO donDatUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_dat_admin);
        imgBackChiTietAdmin = findViewById(R.id.imgBackChiTietDonDatAdmin);
        txtTenKh = findViewById(R.id.txtTenKhachHangChiTiet);
        txtSoDienThoai = findViewById(R.id.txtSoDienThoaiChiTiet);
        txtDiaChi = findViewById(R.id.txtDiaChiChiTiet);
        txtTenSanPham = findViewById(R.id.txtDanhSachSanPhamChiTiet);
        txtThoiGian = findViewById(R.id.txtThoiGianChiTiet);
        txtTrangThai = findViewById(R.id.txtTrangThaiChiTiet);
        txtTongTien = findViewById(R.id.txtTongTienChiTiet);
        tvMaDonDat = findViewById(R.id.tvMaDonHangChiTietDonHang);

        //nhận dữ lệu
        int id = getIntent().getIntExtra("idHoaDon", 0);
        String tenKhachHang = getIntent().getStringExtra("tenKhach");
        String soDienThoai = getIntent().getStringExtra("soDienThoai");
        String diaChi = getIntent().getStringExtra("diaChi");
        String ngayDat = getIntent().getStringExtra("ngayDat");
        String tenSanPham = getIntent().getStringExtra("tenSanPham");
        String trangThai = getIntent().getStringExtra("trangThai");
        int tongTien = getIntent().getIntExtra("tongTien", 0);

        imgBackChiTietAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Tạo chuỗi ngẫu nhiên
        Random random = new Random();
        String kiTuRamDom = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        StringBuilder maRamDom = new StringBuilder();
        int soLuongKiTuRanDom = 8;

        for (int i = 0; i < soLuongKiTuRanDom; i++) {

            maRamDom.append(kiTuRamDom.charAt(random.nextInt(kiTuRamDom.length())));


        }


        tvMaDonDat.setText(id + "" + maRamDom);
        txtTenKh.setText("Họ tên: " + tenKhachHang);
        txtSoDienThoai.setText("Số điện thoại: " + soDienThoai);
        txtDiaChi.setText("Địa chỉ: " + diaChi);
        txtTenSanPham.setText(tenSanPham);
        txtThoiGian.setText(ngayDat);
        txtTrangThai.setText(trangThai);
        txtTongTien.setText("Tổng tiền: " + decimalFormat.format(tongTien) + " VND");
    }
}