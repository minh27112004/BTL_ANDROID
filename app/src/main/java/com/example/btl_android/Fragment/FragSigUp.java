package com.example.btl_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.btl_android.DAO.TaiKhoanDAO;
import com.example.btl_android.DTO.TaiKhoanDTO;
import com.example.btl_android.R;


public class FragSigUp extends Fragment {


    private EditText edTenDangNhap,edMatKhau,edNhapLaiMatKhau;
    private AppCompatButton btnDangKy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_sig_up,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Ánh xạ view
        edTenDangNhap = view.findViewById(R.id.edTenDangNhapSigUp);
        edMatKhau = view.findViewById(R.id.edMatKhauSigUp);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauSigUp);
        btnDangKy = view.findViewById(R.id.btnSigUp);

        //Khở tạo lớp DAO
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(getContext());

        //set onclick cho nut đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()) {

                    String tenDangNhap = edTenDangNhap.getText().toString();
                    String matKhau = edMatKhau.getText().toString();
                    String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString();

                    TaiKhoanDTO objTaiKhoan = new TaiKhoanDTO();
                    objTaiKhoan.setTenDangNhap(tenDangNhap);
                    objTaiKhoan.setMatKhau(matKhau);

                    long kq = taiKhoanDAO.addRow(objTaiKhoan);

                    if (kq > 0) {

                        Toast.makeText(getContext(), "Đăng ký thành công"
                                        ,Toast.LENGTH_SHORT).show();

                        edTenDangNhap.setText("");
                        edNhapLaiMatKhau.setText("");
                        edMatKhau.setText("");

                    }



                }



            }

            //Hàm bắt lỗi
            private boolean kiemTra() {


                if (edMatKhau.getText().toString().equals("")
                        ||edNhapLaiMatKhau.getText().toString().equals("")
                        ||edTenDangNhap.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Mời nhập đủ thông tin",
                                    Toast.LENGTH_SHORT).show();

                    return false;

                }

                if (edTenDangNhap.getText().toString().length() < 5) {

                    Toast.makeText(getContext(), "Tên đăng nhập phải trên 5 ký tự"
                                    , Toast.LENGTH_SHORT).show();
                    return false;

                }

                if (edMatKhau.getText().toString().length() < 5) {

                    Toast.makeText(getContext(), "Mật khẩu phải trên 5 ký tự"
                            ,Toast.LENGTH_SHORT).show();
                    return false;

                }


                if (!edMatKhau.getText().toString().equals(edNhapLaiMatKhau.getText().toString())) {

                    Toast.makeText(getContext(), "Mật khẩu không khớp"
                            , Toast.LENGTH_SHORT).show();
                    return false;

                }



                return true;
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}
