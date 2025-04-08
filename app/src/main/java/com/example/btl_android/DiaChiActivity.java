package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import com.example.btl_android.Adapter.DiaChiUserAdapter;
import com.example.btl_android.Adapter.DiaChiUserAdapter;
import com.example.btl_android.DAO.DiaChiKhachHangDAO;
import com.example.btl_android.DTO.ThongTinKhachHangDTO;

public class DiaChiActivity extends AppCompatActivity {

    RecyclerView recyclerDiaChi;
    private ImageView imgBackDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);

        recyclerDiaChi = findViewById(R.id.recyclerDiaChi);
        imgBackDiaChi = findViewById(R.id.imgBackDiaChi);
        imgBackDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DiaChiKhachHangDAO diaChiKhachHangDAO = new DiaChiKhachHangDAO(this);
        ArrayList<ThongTinKhachHangDTO> list = diaChiKhachHangDAO.getDiaChiAll();
        LinearLayoutManager manager = new LinearLayoutManager(this);

        DiaChiUserAdapter adapter = new DiaChiUserAdapter(list, this);
        recyclerDiaChi.setLayoutManager(manager);
        recyclerDiaChi.setAdapter(adapter);

    }
}