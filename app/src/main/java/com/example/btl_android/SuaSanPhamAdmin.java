package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import com.example.btl_android.DAO.TrangChuAdminDAO;
import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.SanPhamRauAdminDTO;

public class SuaSanPhamAdmin extends AppCompatActivity {
    EditText edtSuatensp, edtSuaGiasp, edtSuaNhaCungCap, edtSuaMoTa;
    AppCompatButton btnSuasp, btnXoaSp;
    Spinner spnCategorySuaSp;
    ImageView ivBack;

    private SanPhamRauAdminDTO dto;
    private TrangChuAdminDAO dao;
    private MyDBHelper dbHelper;

    int positionSpinner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham_admin);

        // Ánh xạ các view
        edtSuatensp = findViewById(R.id.edtSuaTenSp);
        edtSuaGiasp = findViewById(R.id.edtSuaGiaSp);
        edtSuaNhaCungCap = findViewById(R.id.edtSuaNhaCungCap);
        edtSuaMoTa = findViewById(R.id.edtSuaMoTa);
        spnCategorySuaSp = findViewById(R.id.spnCategorySuaSp);
        ivBack = findViewById(R.id.ivBackSuaSp);
        btnSuasp = findViewById(R.id.btnSuaSp);
        btnXoaSp = findViewById(R.id.btnXoaSp);

        // Khởi tạo DAO và DBHelper
        dbHelper = new MyDBHelper(SuaSanPhamAdmin.this);
        dao = new TrangChuAdminDAO(SuaSanPhamAdmin.this);

        // Nhận dữ liệu từ Intent
        getDataIntent();

        // Lấy danh sách loại sản phẩm
        getDataAdmin(spnCategorySuaSp);

        // Xử lý sự kiện khi nhấn nút Sửa sản phẩm
        btnSuasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = edtSuatensp.getText().toString().trim();
                String newPrice = edtSuaGiasp.getText().toString().trim();
                String mota = edtSuaMoTa.getText().toString().trim();

                if (newName.isEmpty() || newPrice.isEmpty()) {
                    Toast.makeText(SuaSanPhamAdmin.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = dao.SuaSanPham(dto.getId_san_pham(), newName, Integer.parseInt(newPrice), mota, getTenLoai((int) spnCategorySuaSp.getSelectedItemId()));
                    if (result) {
                        Toast.makeText(SuaSanPhamAdmin.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(SuaSanPhamAdmin.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Xử lý sự kiện khi nhấn nút Xóa sản phẩm
        btnXoaSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dao.XoaSanPham(dto.getId_san_pham());
                if (result) {
                    Toast.makeText(SuaSanPhamAdmin.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();  // Quay lại màn hình trước đó
                } else {
                    Toast.makeText(SuaSanPhamAdmin.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện khi nhấn nút quay lại
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    // Lấy loại sản phẩm từ spinner
    private String getTenLoai(int positionSpinner) {
        String tenLoai = "";

        switch (positionSpinner) {
            case 0:
                tenLoai = "Rau";
                break;
            case 1:
                tenLoai = "Củ";
                break;
            case 2:
                tenLoai = "Quả";
                break;
        }
        return tenLoai;
    }

    // Đổ dữ liệu vào spinner
    private void getDataAdmin(Spinner spinnerAdmin) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Rau");
        list.add("Củ");
        list.add("Quả");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        spinnerAdmin.setAdapter(adapter);
        spinnerAdmin.setSelection(positionSpinner);
    }

    // Lấy dữ liệu sản phẩm từ Intent
    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            dto = (SanPhamRauAdminDTO) intent.getSerializableExtra("dto");
            edtSuatensp.setText(dto.getTen_san_pham());
            edtSuaGiasp.setText(String.valueOf(dto.getDon_gia()));
            edtSuaNhaCungCap.setText(dto.getNhacungcap());
            edtSuaMoTa.setText(dto.getMo_ta());

            // Set vị trí spinner theo loại sản phẩm
            switch (dto.getLoai()) {
                case "Rau":
                    positionSpinner = 0;
                    break;
                case "Củ":
                    positionSpinner = 1;
                    break;
                case "Quả":
                    positionSpinner = 2;
                    break;
            }
            spnCategorySuaSp.setSelection(positionSpinner);
        }
    }
}
