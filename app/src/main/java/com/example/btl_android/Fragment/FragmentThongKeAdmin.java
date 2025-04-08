package com.example.btl_android.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Calendar;

import com.example.btl_android.DAO.ThongKeDAO;
import com.example.btl_android.R;

public class FragmentThongKeAdmin extends Fragment {
    EditText edtNgayBatDau, edtNgayKetThuc;
    AppCompatButton btnDoanhThu;
    TextView txtKetQua;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_thong_ke_admin, container, false);


        edtNgayBatDau = view.findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = view.findViewById(R.id.edtNgayKetThuc);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        txtKetQua =view.findViewById(R.id.txtKetQua);


        Calendar calendar = Calendar.getInstance();
        edtNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay ="";
                                String thang = "";
                                if(dayOfMonth<10){
                                    ngay ="0"+dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if((month +1) <10){
                                    thang ="0"+(month+1);
                                }else {
                                    thang = String.valueOf((month+1));
                                }

                                edtNgayBatDau.setText(ngay+"/"+thang+"/"+year);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });


        edtNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay ="";
                                String thang = "";
                                if(dayOfMonth<10){
                                    ngay ="0"+dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if((month +1) <10){
                                    thang ="0"+(month+1);
                                }else {
                                    thang = String.valueOf((month+1));
                                }

                                edtNgayKetThuc.setText(ngay+"/"+thang+"/"+year);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String ngaybatdau = edtNgayBatDau.getText().toString();
                String ngayketthuc = edtNgayKetThuc.getText().toString();
                int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau, ngayketthuc);

                if (doanhthu > 0) {
                    txtKetQua.setText(decimalFormat.format(doanhthu)+" VND");
                }else {

                    txtKetQua.setText("Không có dữ liệu");

                }





            }
        });



        return view;
    }



}
