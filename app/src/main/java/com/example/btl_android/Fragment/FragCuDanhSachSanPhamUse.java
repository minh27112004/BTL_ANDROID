package com.example.btl_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.btl_android.Adapter.AdapterDanhSachSanPhamCu;
import com.example.btl_android.DAO.DanhSachSanPhamDAO;
import com.example.btl_android.DTO.DanhSachSanPhamDTO;
import com.example.btl_android.R;

public class FragCuDanhSachSanPhamUse extends Fragment {

    public static RecyclerView recyclerviewDanhSachSanPhamCu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cu_danh_sach_san_pham_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerviewDanhSachSanPhamCu = view.findViewById(R.id.recyclerviewDanhSachSanPhamCu);
        DanhSachSanPhamDAO danhSachSanPhamDAO = new DanhSachSanPhamDAO(getContext());
        ArrayList<DanhSachSanPhamDTO> list = danhSachSanPhamDAO.getDSSanPhamCu();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerviewDanhSachSanPhamCu.setLayoutManager(gridLayoutManager);

        AdapterDanhSachSanPhamCu adapterDanhSachSanPhamCu = new AdapterDanhSachSanPhamCu(list, getContext());
        recyclerviewDanhSachSanPhamCu.setAdapter(adapterDanhSachSanPhamCu);
    }
}
