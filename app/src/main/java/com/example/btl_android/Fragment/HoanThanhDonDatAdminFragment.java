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

import com.example.btl_android.Adapter.HoanThanhDonDatAdminAdapter;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.R;

public class HoanThanhDonDatAdminFragment extends Fragment {
    RecyclerView recyclerViewHoanThanh;
    List<DonDatUserDTO> list;
    DonDatUserDAO donDatUserDAO;
    HoanThanhDonDatAdminAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dang_giao_hang_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewHoanThanh = view.findViewById(R.id.recyclerHoanThanhGiaoHang);

        donDatUserDAO = new DonDatUserDAO(getContext());
        list = donDatUserDAO.selectHoanThanhGiaoHang();

        adapter = new HoanThanhDonDatAdminAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewHoanThanh.setLayoutManager(manager);
        recyclerViewHoanThanh.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = donDatUserDAO.selectHoanThanhGiaoHang();
        adapter.setData(list);
    }
}
