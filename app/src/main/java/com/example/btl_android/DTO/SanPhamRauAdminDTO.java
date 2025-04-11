package com.example.btl_android.DTO;

import java.io.Serializable;

public class SanPhamRauAdminDTO implements Serializable {
    private int id_san_pham;
    private  int id_loai_san_pham;
    private String ten_san_pham;
    private int don_gia;
    private String img_url;
    private String mo_ta;
    private int so_luong;
    private String nhacungcap;
    private String loai;

    public SanPhamRauAdminDTO() {
    }

    public SanPhamRauAdminDTO(int id_san_pham, int id_loai_san_pham, String ten_san_pham, int don_gia, String img_url, String mo_ta, int so_luong,String nhacungcap, String loai) {
        this.id_san_pham = id_san_pham;
        this.id_loai_san_pham = id_loai_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.don_gia = don_gia;
        this.img_url = img_url;
        this.mo_ta = mo_ta;
        this.so_luong = so_luong;
        this.nhacungcap = nhacungcap;
        this.loai = loai;
    }

    public SanPhamRauAdminDTO(String ten_san_pham, int don_gia, String img_url, String mo_ta, String nhacungcap, String loai) {
        this.ten_san_pham = ten_san_pham;
        this.don_gia = don_gia;
        this.img_url = img_url;
        this.mo_ta = mo_ta;
        this.nhacungcap = nhacungcap;
        this.loai = loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public int getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(int id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public int getId_loai_san_pham() {
        return id_loai_san_pham;
    }

    public void setId_loai_san_pham(int id_loai_san_pham) {
        this.id_loai_san_pham = id_loai_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public int getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }
}
