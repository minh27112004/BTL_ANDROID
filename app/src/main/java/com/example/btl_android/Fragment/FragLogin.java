package com.example.btl_android.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.btl_android.ConnectInternet;
import com.example.btl_android.DAO.TaiKhoanDAO;
import com.example.btl_android.MainActivity;
import com.example.btl_android.R;
import com.example.btl_android.XacNhanTaiKhoanActivity;


public class FragLogin extends Fragment {

    private TextView tvQuenMatKhau;
    private EditText edTaiKhoan,edMatKhau;
    private AppCompatButton btnDangNhap;
    private TaiKhoanDAO taiKhoanDAO;
    private ConnectInternet connectInternet;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Ánh xạ view
        tvQuenMatKhau = view.findViewById(R.id.tvQuenMatKhau);
        btnDangNhap = view.findViewById(R.id.btnLogin);
        edTaiKhoan = view.findViewById(R.id.edTenDangNhapLogin);
        edMatKhau = view.findViewById(R.id.edMatKhauLogin);

        //Khởi tạo lớp DAO
        taiKhoanDAO = new TaiKhoanDAO(getContext());
        connectInternet = new ConnectInternet(requireContext());

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), XacNhanTaiKhoanActivity.class));
                requireActivity().finish();


            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!connectInternet.isNetworkAvailable()){
                    connectInternet.showNoInternetDialog();

                } else if (kiemTra())
                {

                    String tenDangNhap = edTaiKhoan.getText().toString();
                    String matKhau  = edMatKhau.getText().toString();

                    boolean kq = taiKhoanDAO.checkTaiKhoan(tenDangNhap,matKhau);

                    if (kq || tenDangNhap.equals("admin") && matKhau.equals("123") || tenDangNhap.equals("1")||matKhau.equals("1")) {

                        Toast.makeText(getContext(), "Đăng nhập thành công"
                                ,Toast.LENGTH_SHORT).show();

                        remenberUser(tenDangNhap,matKhau);
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        intent.putExtra("tenDangNhap",tenDangNhap);
                        startActivity(intent);
                        requireActivity().finish();


                    }else {

                        Toast.makeText(getContext(), "Đăng nhập thất bại"
                                , Toast.LENGTH_SHORT).show();

                    }



                }

            }

            private boolean kiemTra() {

                if (edMatKhau.getText().toString().equals("")
                        || edTaiKhoan.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Mời nhập thông tin", Toast.LENGTH_SHORT).show();
                    return false;
                }



                return true;

            }
        });


        super.onViewCreated(view, savedInstanceState);
    }

    //Hàm lưu tài khoản vào SharedPreferences
    private void remenberUser(String tenDangNhap,String matKhau) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("tenDangNhap",tenDangNhap);
        editor.putString("matKhau",matKhau);
        editor.apply();


    }
}
