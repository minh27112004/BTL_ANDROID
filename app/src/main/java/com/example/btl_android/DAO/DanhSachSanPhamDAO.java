package com.example.btl_android.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.DanhSachSanPhamDTO;

public class DanhSachSanPhamDAO {
    MyDBHelper myDBHelper;

    public DanhSachSanPhamDAO(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public ArrayList<DanhSachSanPhamDTO> getDSSanPham() {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tb_san_pham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> getDSSanPhamRau() {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Rau%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> getDSSanPhamCu() {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Củ%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> getDSSanPhamQua() {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Quả%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> timKiemSanPhamRau(String tenSanPham) {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSanPham + "%' AND loai LIKE 'Rau' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> timKiemSanPhamCu(String tenSanPham) {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSanPham + "%' AND loai LIKE 'Củ' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public ArrayList<DanhSachSanPhamDTO> timKiemSanPhamQua(String tenSanPham) {
        ArrayList<DanhSachSanPhamDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSanPham + "%' AND loai LIKE 'Quả' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhSachSanPhamDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }

        return list;
    }

}
