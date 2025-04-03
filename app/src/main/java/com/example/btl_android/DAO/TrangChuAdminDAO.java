package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.SanPhamRauAdminDTO;

public class TrangChuAdminDAO {
    MyDBHelper myDBHelper;

    public TrangChuAdminDAO(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public ArrayList<SanPhamRauAdminDTO> getDSSanPhamAdmin() {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tb_san_pham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThemSanPham(String tensanpham, int dongia, String img, String mota, String loai, String nhacungcap) {
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_san_pham", tensanpham);
        values.put("don_gia", dongia);
        values.put("img_url", img);
        values.put("mo_ta", mota);
        values.put("loai", loai);
        values.put("nhacungcap", nhacungcap);

        long check = sqLiteDatabase.insert("tb_san_pham", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean SuaSanPham(int id_san_pham, String tensanpham, int dongia,String mo_ta, String loai){
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(" ten_san_pham", tensanpham);
        values.put(" don_gia ", dongia);
        values.put("mo_ta", mo_ta);
        values.put("loai", loai);

        int check = sqLiteDatabase.update("tb_san_pham",values,"  id_san_pham =?",new String[]{String.valueOf(id_san_pham)});

        getDSSanPhamAdmin();
        return check !=0;
    }
    // Trong TrangChuAdminDAO.java
    // Câu lệnh xóa sản phẩm
    public boolean XoaSanPham(int idSanPham) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        int result = db.delete("tb_san_pham", "id_san_pham = ?", new String[]{String.valueOf(idSanPham)});
        db.close();
        return result > 0;
    }


    public ArrayList<SanPhamRauAdminDTO> getDSSanPhamRauAdmin() {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Rau%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<SanPhamRauAdminDTO> getDSSanPhamCuAdmin() {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Củ%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }


    public ArrayList<SanPhamRauAdminDTO> getDSSanPhamQuaAdmin() {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE loai LIKE '%Quả%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<SanPhamRauAdminDTO> timKiemRau(String tenSp) {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSp + "%' AND loai LIKE '%Rau%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<SanPhamRauAdminDTO> timKiemCu(String tenSp) {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSp + "%' AND loai LIKE '%Củ%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<SanPhamRauAdminDTO> timKiemQua(String tenSp) {
        ArrayList<SanPhamRauAdminDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  tb_san_pham WHERE ten_san_pham LIKE '%" + tenSp + "%' AND loai LIKE '%Quả%' ", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPhamRauAdminDTO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }


}
