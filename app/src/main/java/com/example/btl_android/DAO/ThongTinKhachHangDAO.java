package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.TaiKhoanDTO;
import com.example.btl_android.DTO.ThongTinKhachHangDTO;

public class ThongTinKhachHangDAO {
    private MyDBHelper myDBHelper;
    private  SQLiteDatabase db;


    public ThongTinKhachHangDAO(Context context) {
        myDBHelper = new MyDBHelper(context);

    }

    public ArrayList<ThongTinKhachHangDTO> getAll() {
        ArrayList<ThongTinKhachHangDTO> list = new ArrayList<>();
        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id_khach_hang, ten_khach_hang, email, so_dien_thoai, gioi_tinh, ngay_sinh FROM tb_thong_tin_khach_hang", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThongTinKhachHangDTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        return list;
    }




    public int updateRow(ThongTinKhachHangDTO objThongTinKhachHang) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten_khach_hang", objThongTinKhachHang.getTenKhachHang());
        values.put("email", objThongTinKhachHang.getEmail());
        values.put("so_dien_thoai", objThongTinKhachHang.getSoDienThoai());
        values.put("gioi_tinh", objThongTinKhachHang.getGioiTinh());
        values.put("ngay_sinh", objThongTinKhachHang.getNgaySinh());
        return db.update("tb_thong_tin_khach_hang", values, "id_khach_hang=?", new String[]{String.valueOf(objThongTinKhachHang.getIdKhachHang())});
    }
    public ThongTinKhachHangDTO getThongtinByname(String tendangnhap){
        SQLiteDatabase db= myDBHelper.getReadableDatabase();
        Cursor cursor = db.query("tb_thong_tin_khach_hang",null,"ten_khach_hang=?",new String[]{tendangnhap},null,null,null);
        ThongTinKhachHangDTO thongTinKhachHangDTO = null;
        if(cursor!=null && cursor.moveToFirst()){
            int id_khach_hang = cursor.getInt(cursor.getColumnIndexOrThrow("id_khach_hang"));
            String ten_khach_hang = cursor.getString(cursor.getColumnIndexOrThrow("ten_khach_hang"));
            String so_dien_thoai = cursor.getString(cursor.getColumnIndexOrThrow("so_dien_thoai"));
            String dia_chi = cursor.getString(cursor.getColumnIndexOrThrow("dia_chi"));
            thongTinKhachHangDTO = new ThongTinKhachHangDTO(ten_khach_hang,so_dien_thoai,dia_chi);
        }
        cursor.close();
        return  thongTinKhachHangDTO;

    }

}
