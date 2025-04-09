package com.example.btl_android.DTO;

import java.io.Serializable;

public class SanPhamTrangChuUserDTO {

    public int idSanPhamUser, idLoaiSanPham;
    public String anhSanPhamUser;
    public String tenSanPhamUser;
    public int giaSanPhamUser;
    public String moTaSp;

    public int soLuongSp;

    public SanPhamTrangChuUserDTO() {
    }

    public SanPhamTrangChuUserDTO(int idSanPhamUser, String anhSanPhamUser, String tenSanPhamUser, int giaSanPhamUser) {
        this.idSanPhamUser = idSanPhamUser;
        this.anhSanPhamUser = anhSanPhamUser;
        this.tenSanPhamUser = tenSanPhamUser;
        this.giaSanPhamUser = giaSanPhamUser;
    }

    public int getIdSanPhamUser() {
        return idSanPhamUser;
    }

    public void setIdSanPhamUser(int idSanPhamUser) {
        this.idSanPhamUser = idSanPhamUser;
    }

    public String getAnhSanPhamUser() {
        return anhSanPhamUser;
    }

    public void setAnhSanPhamUser(String anhSanPhamUser) {
        this.anhSanPhamUser = anhSanPhamUser;
    }

    public String getTenSanPhamUser() {
        return tenSanPhamUser;
    }

    public void setTenSanPhamUser(String tenSanPhamUser) {
        this.tenSanPhamUser = tenSanPhamUser;
    }

    public int getGiaSanPhamUser() {
        return giaSanPhamUser;
    }

    public void setGiaSanPhamUser(int giaSanPhamUser) {
        this.giaSanPhamUser = giaSanPhamUser;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }


    public String getMoTaSp() {
        return moTaSp;
    }

    public void setMoTaSp(String moTaSp) {
        this.moTaSp = moTaSp;
    }

    public int getSoLuongSp() {
        return soLuongSp;
    }

    public void setSoLuongSp(int soLuongSp) {
        this.soLuongSp = soLuongSp;
    }

    public String toString(){

        return "Tên : "+tenSanPhamUser+" Giá : "+giaSanPhamUser;

    }
}
