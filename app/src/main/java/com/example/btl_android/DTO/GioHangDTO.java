package com.example.btl_android.DTO;

public class GioHangDTO {

    public int id;
    public  int idSanPham;
    public String imgSanPham;
    public String tenSanPham;
    public int soLuongSanPham;
    public int giaSanPham;
    public int tongTienCuaSp;

    public int getTongTienCuaSp() {
        return tongTienCuaSp;
    }

    public void setTongTienCuaSp(int tongTienCuaSp) {
        this.tongTienCuaSp = tongTienCuaSp;
    }

    public GioHangDTO() {
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSanPham() {
        return imgSanPham;
    }

    public void setImgSanPham(String imgSanPham) {
        this.imgSanPham = imgSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String toString(){
        return this.tenSanPham;
    }

}
