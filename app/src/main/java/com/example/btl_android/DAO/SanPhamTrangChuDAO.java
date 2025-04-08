package com.example.btl_android.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;

public class SanPhamTrangChuDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;


    public SanPhamTrangChuDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }

    public List<SanPhamTrangChuUserDTO> getAll(){

        List<SanPhamTrangChuUserDTO> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tb_san_pham",null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            while (!cursor.isAfterLast()){

                int idSanPham = cursor.getInt(0);
                int idLoai = cursor.getInt(1);
                String tenSanPham = cursor.getString(2);
                int donGia = cursor.getInt(3);
                String imgSanPham = cursor.getString(4);
                String moTa = cursor.getString(5);
                int soLuong = cursor.getInt(6);

                SanPhamTrangChuUserDTO objSanPham = new SanPhamTrangChuUserDTO();
                objSanPham.setIdSanPhamUser(idSanPham);
                objSanPham.setIdLoaiSanPham(idLoai);
                objSanPham.setTenSanPhamUser(tenSanPham);
                objSanPham.setGiaSanPhamUser(donGia);
                objSanPham.setAnhSanPhamUser(imgSanPham);
                objSanPham.setMoTaSp(moTa);
                objSanPham.setSoLuongSp(soLuong);

                list.add(objSanPham);

                cursor.moveToNext();


            }
        }


        return list;

    }


    public List<SanPhamTrangChuUserDTO> timKiemSanPhamTrangChu(String tenSpSeach){

        List<SanPhamTrangChuUserDTO> list = new ArrayList<>();

        Cursor cursor =
                db.rawQuery("SELECT * FROM tb_san_pham " +
                        "WHERE ten_san_pham LIKE '%"+ tenSpSeach + "%' ",null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            while (!cursor.isAfterLast()){

                int idSanPham = cursor.getInt(0);
                int idLoai = cursor.getInt(1);
                String tenSanPham = cursor.getString(2);
                int donGia = cursor.getInt(3);
                String imgSanPham = cursor.getString(4);
                String moTa = cursor.getString(5);
                int soLuong = cursor.getInt(6);

                SanPhamTrangChuUserDTO objSanPham = new SanPhamTrangChuUserDTO();
                objSanPham.setIdSanPhamUser(idSanPham);
                objSanPham.setIdLoaiSanPham(idLoai);
                objSanPham.setTenSanPhamUser(tenSanPham);
                objSanPham.setGiaSanPhamUser(donGia);
                objSanPham.setAnhSanPhamUser(imgSanPham);
                objSanPham.setMoTaSp(moTa);
                objSanPham.setSoLuongSp(soLuong);

                list.add(objSanPham);

                cursor.moveToNext();


            }
        }


        return list;

    }


}
