package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.ThongTinKhachHangDTO;

public class DiaChiKhachHangDAO {

    MyDBHelper myDBHelper;


    public DiaChiKhachHangDAO(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

//    Hien thị thòn tin địa chỉ khách hàng
    public ArrayList<ThongTinKhachHangDTO> getDiaChiAll() {
        ArrayList<ThongTinKhachHangDTO> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ten_khach_hang, so_dien_thoai, dia_chi FROM tb_thong_tin_khach_hang", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThongTinKhachHangDTO(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int updateRow(ThongTinKhachHangDTO objThongTinkhachHang) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_khach_hang", objThongTinkhachHang.getTenKhachHang());
        values.put("so_dien_thoai", objThongTinkhachHang.getSoDienThoai());
        values.put("dia_chi", objThongTinkhachHang.getDiaChi());

        return db.update("tb_thong_tin_khach_hang", values, "id_khach_hang=?", new String[]{objThongTinkhachHang.getIdKhachHang()+""});
    }
}
