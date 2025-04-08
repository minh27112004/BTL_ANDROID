package com.example.btl_android.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.btl_android.DoiMatKhauUserActivity;
import com.example.btl_android.R;
import com.example.btl_android.ThongTinCaNhanActivity;
import com.example.btl_android.WelcomeActivity;

public class FragTaiKhoanAdmin extends Fragment {
    TextView txtTaiKhoanAdmin;
    Button btnThongTinAdmin, btnDoiMatKhauAdmin, btnDangXuatAdmin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tai_khoan_admin, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTaiKhoanAdmin = view.findViewById(R.id.txtTenTaiKhoanAdmin);
        btnThongTinAdmin = view.findViewById(R.id.btnThongTinAdmin);
        btnDoiMatKhauAdmin = view.findViewById(R.id.btnDoiMatKhauAdmin);
        btnDangXuatAdmin = view.findViewById(R.id.btnDangXuatAdmin);

        //xu ly lấy tên ang nhâp
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String logUserName = sharedPreferences.getString("userName", "");
        txtTaiKhoanAdmin.setText(logUserName);


        btnThongTinAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ThongTinCaNhanActivity.class));
            }
        });

        btnDoiMatKhauAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DoiMatKhauUserActivity.class));
            }
        });

        btnDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_xac_nhan, null, false);

                builder.setView(view1);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnXacNhan, btnHuy;
                TextView tvNoiDung;

                btnHuy = view1.findViewById(R.id.btnHuyDialog);
                btnXacNhan = view1.findViewById(R.id.btnXacNhanDialog);
                tvNoiDung = view1.findViewById(R.id.tvNoiDungDialog);
                tvNoiDung.setText("Bạn có chắc muốn đăng xuất không?");

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(getContext(), WelcomeActivity.class));

                    }
                });


                dialog.show();


            }
        });

    }
}
