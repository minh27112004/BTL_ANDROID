package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.SanPhamRauAdminDTO;

public class TrangChuAdminDAO {
    MyDBHelper myDBHelper;
    SQLiteDatabase db;


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

    public boolean ThemSanPham(String tensanpham, int dongia, String img, String mota, String loai, String nhacungcap,int soluong) {
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ten_san_pham", tensanpham);
        values.put("don_gia", dongia);
        values.put("img_url", img);
        values.put("mo_ta", mota);
        values.put("loai", loai);
        values.put("so_luong", soluong);
        values.put("nhacungcap", nhacungcap);

        long check = sqLiteDatabase.insert("tb_san_pham", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean SuaSanPham(int id_san_pham, String tensanpham, int dongia, String mo_ta, String loai, int soluongthem, int soluonggiam) {
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();
        int soLuongHienTai = 0;

        // Lấy số lượng hiện tại
        Cursor cursor = sqLiteDatabase.query(
                "tb_san_pham",
                new String[]{"so_luong"},
                "id_san_pham = ?",
                new String[]{String.valueOf(id_san_pham)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("so_luong");
            if (columnIndex >= 0) {
                soLuongHienTai = cursor.getInt(columnIndex);
            } else {
                Log.e("SuaSanPham", "Không tìm thấy cột 'so_luong' trong kết quả truy vấn");
                cursor.close();
                return false;
            }
            cursor.close();
        } else {
            if (cursor != null) cursor.close();
            Log.e("SuaSanPham", "Không tìm thấy sản phẩm với ID: " + id_san_pham);
            return false;
        }


        int soLuongMoi = soLuongHienTai + soluongthem - soluonggiam;
        if (soLuongMoi < 0) soLuongMoi = 0; // Tránh âm số

        // Cập nhật dữ liệu
        ContentValues values = new ContentValues();
        values.put("ten_san_pham", tensanpham);
        values.put("don_gia", dongia);
        values.put("mo_ta", mo_ta);
        values.put("loai", loai);
        values.put("so_luong", soLuongMoi);

        int rowsAffected = sqLiteDatabase.update(
                "tb_san_pham",
                values,
                "id_san_pham = ?",
                new String[]{String.valueOf(id_san_pham)}
        );

        return rowsAffected > 0;
    }



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
    public int getSoLuongHienTai(int idSanPham) {
        int soLuong = -1;

        // Sử dụng parameterized query để tránh SQL injection
        Cursor cursor = db.rawQuery("SELECT so_luong FROM tb_san_pham WHERE id_san_pham = ?",
                new String[]{String.valueOf(idSanPham)});

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("so_luong");
                    if (columnIndex >= 0) {
                        soLuong = cursor.getInt(columnIndex);
                    }
                }
            } catch (Exception e) {
                Log.e("SanPhamTrangChuDAO", "Lỗi khi đọc số lượng sản phẩm: " + e.getMessage());
            } finally {
                cursor.close();
            }
        }

        return soLuong;
    }
    public boolean capNhatSoLuong(int idSanPham, int soLuongGiam) {
         db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Lấy số lượng hiện tại
        int soLuongHienTai = getSoLuongHienTai(idSanPham);
        if (soLuongHienTai < soLuongGiam) {
            return false; // Không đủ số lượng để giảm
        }

        values.put("so_luong", soLuongHienTai - soLuongGiam);

        int rowsAffected = db.update(
                "tb_san_pham",
                values,
                "id_san_pham = ?",
                new String[]{String.valueOf(idSanPham)}
        );

        return rowsAffected > 0;
    }
    public int layIdSanPhamTheoTen(String tenSanPham) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id_san_pham FROM tb_san_pham WHERE ten_san_pham = ?",
                new String[]{tenSanPham}
        );

        int id = -1;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
            cursor.close();
        }
        return id;
    }


}
