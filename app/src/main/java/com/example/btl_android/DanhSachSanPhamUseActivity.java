package com.example.btl_android;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_android.Adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import com.example.btl_android.Adapter.AdapterDanhSachSanPham;
import com.example.btl_android.Adapter.MyViewPagerAdapter;
import com.example.btl_android.DAO.DanhSachSanPhamDAO;
import com.example.btl_android.DTO.DanhSachSanPhamDTO;
import com.example.btl_android.Fragment.FragCuDanhSachSanPhamUse;
import com.example.btl_android.Fragment.FragQuaDanhSachSanPhamUse;
import com.example.btl_android.Fragment.FragRauDanhSachSanPhamUse;

public class DanhSachSanPhamUseActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    EditText edtSearch;
    AdapterDanhSachSanPham danhSachSanPham;
    ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham_use);

        mTabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_paper);
        edtSearch = findViewById(R.id.edt_search);
        ivBack = findViewById(R.id.ivBackDanhSachSanPham);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        DanhSachSanPhamDAO danhSachSanPhamDAO = new DanhSachSanPhamDAO(this);
//        ArrayList<DanhSachSanPhamDTO> list = danhSachSanPhamDAO.getDSSanPham();

//        danhSachSanPham = new AdapterDanhSachSanPham(list,this);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // cố định tab ko cho vuốt
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.getTabAt(position).select();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Chức năng tìm kiếm
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if (mTabLayout.getSelectedTabPosition() == 0) {

                    String tenSanPhamRau = s.toString();
                    timKiemSanPhamRau(tenSanPhamRau);

                } else if (mTabLayout.getSelectedTabPosition() == 1) {

                    String tenSanPhamCu = s.toString();
                    timKiemSanPhamCu(tenSanPhamCu);

                }else if (mTabLayout.getSelectedTabPosition() == 2) {

                    String tenSanPhamQua = s.toString();
                    timKiemSanPhamQua(tenSanPhamQua);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void timKiemSanPhamQua(String tenSanPhamQua) {
        DanhSachSanPhamDAO sachSanPhamDAO = new DanhSachSanPhamDAO(this);

        ArrayList<DanhSachSanPhamDTO> list = sachSanPhamDAO.timKiemSanPhamQua(tenSanPhamQua);
        AdapterDanhSachSanPham adapterDanhSachSanPham = new AdapterDanhSachSanPham(list, this);

        if (list.size() > 0) {

            FragQuaDanhSachSanPhamUse.recyclerDanhSachSanPhamQuaUse.setAdapter(adapterDanhSachSanPham);
            adapterDanhSachSanPham.notifyDataSetChanged();


        } else {


            Toast.makeText(this, "Không có sản phẩm quả", Toast.LENGTH_SHORT).show();

        }

    }

    private void timKiemSanPhamCu(String tenSanPhamCu) {
        DanhSachSanPhamDAO sachSanPhamDAO = new DanhSachSanPhamDAO(this);

        ArrayList<DanhSachSanPhamDTO> list = sachSanPhamDAO.timKiemSanPhamCu(tenSanPhamCu);
        AdapterDanhSachSanPham adapterDanhSachSanPham = new AdapterDanhSachSanPham(list, this);

        if (list.size() > 0) {

            FragCuDanhSachSanPhamUse.recyclerviewDanhSachSanPhamCu.setAdapter(adapterDanhSachSanPham);
            adapterDanhSachSanPham.notifyDataSetChanged();


        } else {


            Toast.makeText(this, "Không có sản phẩm củ", Toast.LENGTH_SHORT).show();

        }

    }

    private void timKiemSanPhamRau(String tenSanPhamRau) {

        DanhSachSanPhamDAO sachSanPhamDAO = new DanhSachSanPhamDAO(this);

        ArrayList<DanhSachSanPhamDTO> list = sachSanPhamDAO.timKiemSanPhamRau(tenSanPhamRau);
        AdapterDanhSachSanPham adapterDanhSachSanPham = new AdapterDanhSachSanPham(list, this);

        if (list.size() > 0) {

            FragRauDanhSachSanPhamUse.recyclerDanhSachSanPhamUse.setAdapter(adapterDanhSachSanPham);
            adapterDanhSachSanPham.notifyDataSetChanged();


        } else {


            Toast.makeText(this, "Không có sản phẩm rau", Toast.LENGTH_SHORT).show();

        }


    }
}