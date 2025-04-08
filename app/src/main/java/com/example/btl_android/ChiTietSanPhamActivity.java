package com.example.btl_android;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.List;

import com.example.btl_android.DAO.GioHangDAO;
import com.example.btl_android.DAO.SanPhamTrangChuDAO;
import com.example.btl_android.DTO.GioHangDTO;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    ImageView ivBack, ivAnhSp;
    List<SanPhamTrangChuUserDTO> list;
    SanPhamTrangChuDAO sanPhamTrangChuDAO;
    TextView tvTenSp, tvGiaSp, tvMoTaSp;
    LinearLayout layoutThemVaoGioHang;
    List<GioHangDTO> listGioHang;
    GioHangDAO gioHangDAO;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        ivBack = findViewById(R.id.ivbackChiTietSanPham);
        tvTenSp = findViewById(R.id.tvTenSanPham);
        tvGiaSp = findViewById(R.id.tvGiaTienChiTienSanPham);
        tvMoTaSp = findViewById(R.id.tvMoTaSanPhamChiTietSanPham);
        ivAnhSp = findViewById(R.id.ivAnhChiTietSp);
        layoutThemVaoGioHang = findViewById(R.id.layoutThemVaoGioHangChiTietSanPham);

        sanPhamTrangChuDAO = new SanPhamTrangChuDAO(this);
        list = sanPhamTrangChuDAO.getAll();
        gioHangDAO = new GioHangDAO(this);
        listGioHang = gioHangDAO.getAll();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("PRODUCT", MODE_PRIVATE);

        String tenSanPham = sharedPreferences.getString("tenSp", "");
        int giaSp = sharedPreferences.getInt("doGia", 0);
        String anhSp = sharedPreferences.getString("anhSp", "");
        String moTaSp = sharedPreferences.getString("moTa", "");

        tvTenSp.setText(tenSanPham);
        tvGiaSp.setText(decimalFormat.format(giaSp) + " VND");
        int resImg = ((this).getResources().getIdentifier(anhSp, "drawable", getPackageName()));
        ivAnhSp.setImageResource(resImg);
        tvMoTaSp.setText(moTaSp);


        try {
            byte[] imageBytes = Base64.decode(anhSp, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (bitmap != null) {
                ivAnhSp.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        layoutThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GioHangDTO objGioHang = new GioHangDTO();
                objGioHang.setTenSanPham(tenSanPham);
                objGioHang.setGiaSanPham(giaSp);
                objGioHang.setSoLuongSanPham(1);
                objGioHang.setImgSanPham(anhSp);
                objGioHang.setTongTienCuaSp(giaSp);

                long kq = gioHangDAO.addRow(objGioHang);

                if (kq > 0) {

                    Toast.makeText(ChiTietSanPhamActivity.this, "Thêm thành công"
                            , Toast.LENGTH_SHORT).show();
                    listGioHang.clear();
                    listGioHang.addAll(gioHangDAO.getAll());

                } else {

                    Toast.makeText(ChiTietSanPhamActivity.this, "Thêm thất bại"
                            , Toast.LENGTH_SHORT).show();

                }


            }
        });

        //Kiểm tra nếu tên tài khoản là admin thì ẩn đi layout them giỏ hàng
        SharedPreferences sharedPreferences1 = getSharedPreferences("USER", MODE_PRIVATE);
        String tenTaiKhoan = sharedPreferences1.getString("tenDangNhap", "");

        if (tenTaiKhoan.equals("admin")) {

            layoutThemVaoGioHang.setVisibility(View.GONE);

        } else {

            layoutThemVaoGioHang.setVisibility(View.VISIBLE);

        }


    }
}