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
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.btl_android.Adapter.AdapterDonHuyUser;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.MainActivity;
import com.example.btl_android.R;

public class FragDonHuyUser extends Fragment {

    RecyclerView recyclerView;
    List<DonDatUserDTO> list;
    DonDatUserDAO donDatUserDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_don_huy_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rcvDonHuyFragment);
        donDatUserDAO = new DonDatUserDAO(getContext());
        list = donDatUserDAO.donHuy().stream().filter(it-> Objects.equals(it.getTenKhachHang(), MainActivity.currentAccount.getTenUser())).collect(Collectors.toList());
        AdapterDonHuyUser adapterDonHuyUser = new AdapterDonHuyUser(getContext(),list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                ,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterDonHuyUser);
        adapterDonHuyUser.notifyDataSetChanged();



        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        donDatUserDAO = new DonDatUserDAO(getContext());
        list = donDatUserDAO.donHuy().stream().filter(it-> Objects.equals(it.getTenKhachHang(), MainActivity.currentAccount.getTenUser())).collect(Collectors.toList());
        AdapterDonHuyUser adapterDonHuyUser = new AdapterDonHuyUser(getContext(),list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                ,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterDonHuyUser);
        adapterDonHuyUser.notifyDataSetChanged();


    }
}
