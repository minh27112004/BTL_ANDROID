package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.DonDatUserDTO;

public class DonDatUserDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public DonDatUserDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }

    public long addRow(DonDatUserDTO objDonDat){

        ContentValues values = new ContentValues();
        values.put("id_san_pham",objDonDat.getIdSanPham());
        values.put("ten_khach_hang",objDonDat.getTenKhachHang());
        values.put("danh_sach_san_pham",objDonDat.getTenSanPham());
        values.put("so_dien_thoai",objDonDat.getSoDienThoai());
        values.put("dia_chi",objDonDat.getDiaChi());
        values.put("ngay_dat",objDonDat.getNgayDat());
        values.put("trang_thai",objDonDat.getTrangThai());
        values.put("tong_tien",objDonDat.getTongTien());

        return db.insert("tb_hoa_don",null,values);

    }
    public int updateTrangThai(DonDatUserDTO objDonDat){

        ContentValues values = new ContentValues();
        values.put("trang_thai",objDonDat.getTrangThai());

        return db.update("tb_hoa_don",values,"id_hoa_don=?",new String[]{objDonDat.getId()+""});

    }



    public List<DonDatUserDTO> getAll(){

        List<DonDatUserDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_hoa_don",null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idSp = cursor.getInt(1);
                String tenKhach = cursor.getString(2);
                String dsSanPham = cursor.getString(3);
                String soDienThoai = cursor.getString(4);
                String diaChi = cursor.getString(5);
                String ngayDat = cursor.getString(6);
                String trangThai = cursor.getString(7);
                int tongTien = cursor.getInt(8);
                DonDatUserDTO objDonDat = new DonDatUserDTO();
                objDonDat.setId(id);
                objDonDat.setIdSanPham(idSp);
                objDonDat.setTenKhachHang(tenKhach);
                objDonDat.setTenSanPham(dsSanPham);
                objDonDat.setSoDienThoai(soDienThoai);
                objDonDat.setDiaChi(diaChi);
                objDonDat.setNgayDat(ngayDat);
                objDonDat.setTrangThai(trangThai);
                objDonDat.setTongTien(tongTien);

                list.add(objDonDat);


                cursor.moveToNext();

            }


        }



        return list;


    }

    public List<DonDatUserDTO> donHuy(){

        List<DonDatUserDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_hoa_don where trang_thai like '%Hủy%'",null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idSp = cursor.getInt(1);
                String tenKhach = cursor.getString(2);
                String dsSanPham = cursor.getString(3);
                String soDienThoai = cursor.getString(4);
                String diaChi = cursor.getString(5);
                String ngayDat = cursor.getString(6);
                String trangThai = cursor.getString(7);
                int tongTien = cursor.getInt(8);

                DonDatUserDTO objDonDat = new DonDatUserDTO();
                objDonDat.setId(id);
                objDonDat.setIdSanPham(idSp);
                objDonDat.setTenKhachHang(tenKhach);
                objDonDat.setTenSanPham(dsSanPham);
                objDonDat.setSoDienThoai(soDienThoai);
                objDonDat.setDiaChi(diaChi);
                objDonDat.setNgayDat(ngayDat);
                objDonDat.setTrangThai(trangThai);
                objDonDat.setTongTien(tongTien);


                list.add(objDonDat);


                cursor.moveToNext();

            }


        }



        return list;


    }

    public List<DonDatUserDTO> donDat(){

        List<DonDatUserDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_hoa_don where trang_thai like '%Đã đặt%'",null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idSp = cursor.getInt(1);
                String tenKhach = cursor.getString(2);
                String dsSanPham = cursor.getString(3);
                String soDienThoai = cursor.getString(4);
                String diaChi = cursor.getString(5);
                String ngayDat = cursor.getString(6);
                String trangThai = cursor.getString(7);
                int tongTien = cursor.getInt(8);

                DonDatUserDTO objDonDat = new DonDatUserDTO();
                objDonDat.setId(id);
                objDonDat.setIdSanPham(idSp);
                objDonDat.setTenKhachHang(tenKhach);
                objDonDat.setTenSanPham(dsSanPham);
                objDonDat.setSoDienThoai(soDienThoai);
                objDonDat.setDiaChi(diaChi);
                objDonDat.setNgayDat(ngayDat);
                objDonDat.setTrangThai(trangThai);
                objDonDat.setTongTien(tongTien);


                list.add(objDonDat);


                cursor.moveToNext();

            }


        }



        return list;


    }

    public List<DonDatUserDTO> selectDangGiaoHang() {
        List<DonDatUserDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_hoa_don where trang_thai like '%Đang giao hàng%'", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idSp = cursor.getInt(1);
                String tenKhach = cursor.getString(2);
                String dsSanPham = cursor.getString(3);
                String soDienThoai = cursor.getString(4);
                String diaChi = cursor.getString(5);
                String ngayDat = cursor.getString(6);
                String trangThai = cursor.getString(7);
                int tongTien = cursor.getInt(8);

                DonDatUserDTO objDonDat = new DonDatUserDTO();
                objDonDat.setId(id);
                objDonDat.setIdSanPham(idSp);
                objDonDat.setTenKhachHang(tenKhach);
                objDonDat.setTenSanPham(dsSanPham);
                objDonDat.setSoDienThoai(soDienThoai);
                objDonDat.setDiaChi(diaChi);
                objDonDat.setNgayDat(ngayDat);
                objDonDat.setTrangThai(trangThai);
                objDonDat.setTongTien(tongTien);


                list.add(objDonDat);


                cursor.moveToNext();

            }


        }
        return list;
    }

    public List<DonDatUserDTO> selectHoanThanhGiaoHang() {
        List<DonDatUserDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_hoa_don where trang_thai like '%Đã thanh toán%'", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idSp = cursor.getInt(1);
                String tenKhach = cursor.getString(2);
                String dsSanPham = cursor.getString(3);
                String soDienThoai = cursor.getString(4);
                String diaChi = cursor.getString(5);
                String ngayDat = cursor.getString(6);
                String trangThai = cursor.getString(7);
                int tongTien = cursor.getInt(8);

                DonDatUserDTO objDonDat = new DonDatUserDTO();
                objDonDat.setId(id);
                objDonDat.setIdSanPham(idSp);
                objDonDat.setTenKhachHang(tenKhach);
                objDonDat.setTenSanPham(dsSanPham);
                objDonDat.setSoDienThoai(soDienThoai);
                objDonDat.setDiaChi(diaChi);
                objDonDat.setNgayDat(ngayDat);
                objDonDat.setTrangThai(trangThai);
                objDonDat.setTongTien(tongTien);


                list.add(objDonDat);


                cursor.moveToNext();

            }


        }
        return list;
    }



}
