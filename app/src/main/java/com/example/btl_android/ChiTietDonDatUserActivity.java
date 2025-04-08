package com.example.btl_android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;

public class ChiTietDonDatUserActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTrangThai, tvTenKhach, tvSoDienThoai, tvDiaChi, tvDanhSachSp, tvTongTien, tvThoiGian, tvMaDonHang;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private AppCompatButton btnHuy;
    private List<DonDatUserDTO> list;
    private DonDatUserDAO donDatUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_dat_user);

        // Khởi tạo các view
        ivBack = findViewById(R.id.ivBackChiTietDonDat);
        tvTenKhach = findViewById(R.id.tvTenNguoiNhan);
        tvSoDienThoai = findViewById(R.id.tvSDTChiTietDonDat);
        tvDiaChi = findViewById(R.id.tvDiaChiChiTietDonDat);
        tvDanhSachSp = findViewById(R.id.tvDanhSachSpChiTietDonDat);
        tvTongTien = findViewById(R.id.tvTongTienChiTietDonDat);
        tvThoiGian = findViewById(R.id.tvThoiGianDatHangChiTietDonHang);
        tvMaDonHang = findViewById(R.id.tvMaDonHangChiTietDonHang);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        btnHuy = findViewById(R.id.btnHuyDonChiTietDonDat);

        // Khởi tạo DAO
        donDatUserDAO = new DonDatUserDAO(this);
        list = donDatUserDAO.donDat().stream().filter(it-> Objects.equals(it.getTenKhachHang(), MainActivity.currentAccount.getTenUser())).collect(Collectors.toList());

        // Nhận dữ liệu từ Intent
        int id = getIntent().getIntExtra("idHoaDon", 0);
        String tenKhachHang = getIntent().getStringExtra("tenKhach");
        String soDienThoai = getIntent().getStringExtra("soDienThoai");
        String diaChi = getIntent().getStringExtra("diaChi");
        String tenSanPham = getIntent().getStringExtra("tenSanPham");
        int tongTien = getIntent().getIntExtra("tongTien", 0);
        String ngayDat = getIntent().getStringExtra("ngayDat");
        String trangThai = getIntent().getStringExtra("trangThai");

        // Tạo mã đơn hàng ngẫu nhiên
        String maDonHang = generateRandomCode(8);

        // Thiết lập dữ liệu cho các view
        setViewData(tenKhachHang, soDienThoai, diaChi, tenSanPham, tongTien, ngayDat, id, maDonHang, trangThai);

        // Kiểm tra trạng thái đơn hàng và ẩn nút hủy nếu cần
        checkOrderStatus(trangThai);

        // Thiết lập sự kiện click cho nút hủy đơn
        btnHuy.setOnClickListener(v -> showCancelDialog());

        // Thiết lập sự kiện click cho nút quay lại
        ivBack.setOnClickListener(v -> onBackPressed());
    }

    // Phương thức để tạo mã đơn hàng ngẫu nhiên
    private String generateRandomCode(int length) {
        Random random = new Random();
        String characters = "QWERTYUIOPJASDFGHJKZXCVBNMqwertyuiopkjhgfdsazxcvbnm";
        StringBuilder randomCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomCode.append(characters.charAt(random.nextInt(characters.length())));
        }

        return randomCode.toString();
    }

    // Phương thức để thiết lập dữ liệu cho các view
    private void setViewData(String tenKhachHang, String soDienThoai, String diaChi, String tenSanPham,
                             int tongTien, String ngayDat, int id, String maDonHang, String trangThai) {
        tvTenKhach.setText("Họ tên: " + tenKhachHang);
        tvSoDienThoai.setText("SĐT: " + soDienThoai);
        tvDiaChi.setText("Địa chỉ: " + diaChi);
        tvDanhSachSp.setText(tenSanPham);
        tvTongTien.setText("Tổng tiền: " + decimalFormat.format(tongTien) + " VND");
        tvThoiGian.setText(ngayDat);
        tvMaDonHang.setText(id + maDonHang);
        tvTrangThai.setText(trangThai);
    }

    // Phương thức để kiểm tra trạng thái đơn hàng
    private void checkOrderStatus(String trangThai) {
        if (trangThai.equals("Hủy") || trangThai.equals("Đang giao hàng") || trangThai.equals("Đã thanh toán")) {
            btnHuy.setVisibility(View.GONE);
        }
    }

    // Phương thức để hiển thị hộp thoại xác nhận hủy đơn
    private void showCancelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietDonDatUserActivity.this);
        View dialogView = LayoutInflater.from(ChiTietDonDatUserActivity.this).inflate(R.layout.dialog_xac_nhan, null, false);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        AppCompatButton btnXacNhan = dialogView.findViewById(R.id.btnXacNhanDialog);
        AppCompatButton btnHuy = dialogView.findViewById(R.id.btnHuyDialog);
        TextView tvNoiDung = dialogView.findViewById(R.id.tvNoiDungDialog);
        tvNoiDung.setText("Bạn có chắc muốn hủy đơn hàng không ?");

        btnHuy.setOnClickListener(v -> dialog.dismiss());
        btnXacNhan.setOnClickListener(v -> cancelOrder(dialog));

        dialog.show();
    }

    // Phương thức để xử lý hủy đơn hàng
    private void cancelOrder(AlertDialog dialog) {
        for (DonDatUserDTO donDat : list) {
            donDat.setTrangThai("Hủy");
            int result = donDatUserDAO.updateTrangThai(donDat);

            if (result > 0) {
                Toast.makeText(ChiTietDonDatUserActivity.this, "Hủy thành công", Toast.LENGTH_SHORT).show();
                list = donDatUserDAO.donDat().stream().filter(it-> Objects.equals(it.getTenKhachHang(), MainActivity.currentAccount.getTenUser())).collect(Collectors.toList());
                dialog.dismiss();
                onBackPressed();
            } else {
                Toast.makeText(ChiTietDonDatUserActivity.this, "Hủy thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
