package com.example.btl_android.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.btl_android.DBHelper.MyDBHelper;

public class ThongKeDAO {
    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;
    public ThongKeDAO(Context context){
        myDBHelper = new MyDBHelper(context);
        sqLiteDatabase = myDBHelper.getWritableDatabase();
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tong_tien) FROM tb_hoa_don WHERE ngay_dat  BETWEEN ? AND ? AND trang_thai LIKE '%Đã thanh toán%' ",new String[]{ngaybatdau, ngayketthuc});
        ArrayList<Integer> list = new ArrayList<>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                try {
                    int doanhthu = cursor.getInt(0);
                    list.add(doanhthu);
                }catch (Exception e){
                    list.get(0);
                }
                cursor.moveToNext();

            }

        }
        return list.get(0);
    }
}
