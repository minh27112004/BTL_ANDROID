package com.example.btl_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.example.btl_android.Adapter.QlTrangThaiDonDatAdminAdapter;
import com.example.btl_android.R;

public class FragmentDanhSachDonDatAdmin extends Fragment {
    private TabLayout tabLayoutDanhSachDonDatAdmin;
    private ViewPager2 viewPagerDanhSachDonDatAdmin;
    private QlTrangThaiDonDatAdminAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_danh_sach_don_admin, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutDanhSachDonDatAdmin = view.findViewById(R.id.tabLayoutDonDatAdmin);
        viewPagerDanhSachDonDatAdmin = view.findViewById(R.id.viewPager2DonDatAdmin);
        adapter = new QlTrangThaiDonDatAdminAdapter(getActivity());
        viewPagerDanhSachDonDatAdmin.setAdapter(adapter);
        new TabLayoutMediator(tabLayoutDanhSachDonDatAdmin, viewPagerDanhSachDonDatAdmin, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đơn đặt");
                    break;
                case 1:
                    tab.setText("Giao hàng");
                    break;
                case 2:
                    tab.setText("Hoàn thành");
                    break;
            }
        }).attach();
    }
}
