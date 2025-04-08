package com.example.btl_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.btl_android.Adapter.DangGiaoHangAdapter;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.R;

public class DangGiaoHangAdminFragment extends Fragment {
    RecyclerView recyclerChuanBijHang;
    List<DonDatUserDTO> list;
    DonDatUserDAO donDatUserDAO;
    DangGiaoHangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dang_chuan_bi_hang_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerChuanBijHang = view.findViewById(R.id.recyclerChuanBiHang);

        donDatUserDAO = new DonDatUserDAO(getContext());
        list = donDatUserDAO.selectDangGiaoHang();

        adapter = new DangGiaoHangAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerChuanBijHang.setLayoutManager(manager);
        recyclerChuanBijHang.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        donDatUserDAO = new DonDatUserDAO(getContext());
        list = donDatUserDAO.selectDangGiaoHang();
        adapter = new DangGiaoHangAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerChuanBijHang.setLayoutManager(manager);
        recyclerChuanBijHang.setAdapter(adapter);
    }
}
