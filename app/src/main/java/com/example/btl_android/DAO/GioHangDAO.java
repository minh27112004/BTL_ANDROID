package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.GioHangDTO;

public class GioHangDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public GioHangDAO(Context context) {

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }

    public long addRow(GioHangDTO objGioHang) {

        ContentValues values = new ContentValues();
        values.put("id_san_pham", objGioHang.getIdSanPham());
        values.put("ten_san_pham", objGioHang.getTenSanPham());
        values.put("gia_san_pham", objGioHang.getGiaSanPham());
        values.put("img_url", objGioHang.getImgSanPham());
        values.put("so_luong_sp", objGioHang.getSoLuongSanPham());
        values.put("tongTien1Sp", objGioHang.getTongTienCuaSp());


        return db.insert("tb_gio_hang", null, values);


    }

    //hàm update Số lượng
    public int updateRowSoLuong(GioHangDTO objGioHang) {

        ContentValues values = new ContentValues();
        values.put("tongTien1Sp", objGioHang.getTongTienCuaSp());
        values.put("so_luong_sp", objGioHang.getSoLuongSanPham());

        return db.update("tb_gio_hang", values, "id=?", new String[]{objGioHang.getId() + ""});

    }


    public int deleteRow(GioHangDTO objGioHang) {

        return db.delete("tb_gio_hang", "id=?", new String[]{objGioHang.getId() + ""});

    }

    public int deleteAllDataGioHang() {

        return db.delete("tb_gio_hang", null, null);

    }

    public List<GioHangDTO> getAll() {

        List<GioHangDTO> list = new ArrayList<>();

        Cursor cursor =
                db.rawQuery("select * from tb_gio_hang", null);
        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                int id = cursor.getInt(0);
                int idSanPham = cursor.getInt(1);
                String tenSanPham = cursor.getString(2);
                int donGia = cursor.getInt(3);
                String tenAnh = cursor.getString(4);
                int soLuongSp = cursor.getInt(5);
                int tongTien = cursor.getInt(6);

                GioHangDTO objGioHang = new GioHangDTO();
                objGioHang.setId(id);
                objGioHang.setIdSanPham(idSanPham);
                objGioHang.setTenSanPham(tenSanPham);
                objGioHang.setGiaSanPham(donGia);
                objGioHang.setImgSanPham(tenAnh);
                objGioHang.setSoLuongSanPham(soLuongSp);
                objGioHang.setTongTienCuaSp(tongTien);

                list.add(objGioHang);

                cursor.moveToNext();


            }

        }


        return list;

    }


}
