package com.example.btl_android.DTO;

public class ThongKeAdmin {
    private int imgThongKe;
    private String tenSpThongKe;
    private String soLuongSPmua;

    public ThongKeAdmin(int imgThongKe, String tenSpThongKe, String soLuongSPmua) {
        this.imgThongKe = imgThongKe;
        this.tenSpThongKe = tenSpThongKe;
        this.soLuongSPmua = soLuongSPmua;
    }

    public int getImgThongKe() {
        return imgThongKe;
    }

    public void setImgThongKe(int imgThongKe) {
        this.imgThongKe = imgThongKe;
    }

    public String getTenSpThongKe() {
        return tenSpThongKe;
    }

    public void setTenSpThongKe(String tenSpThongKe) {
        this.tenSpThongKe = tenSpThongKe;
    }

    public String getSoLuongSPmua() {
        return soLuongSPmua;
    }

    public void setSoLuongSPmua(String soLuongSPmua) {
        this.soLuongSPmua = soLuongSPmua;
    }
}
