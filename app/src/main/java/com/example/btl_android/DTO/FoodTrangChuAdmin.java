package com.example.btl_android.DTO;

public class FoodTrangChuAdmin {
    private int imgFoodAdmin;
    private String foodNameAdmin;
    private String foodPriceAdmin;

    public FoodTrangChuAdmin(int imgFoodAdmin, String foodNameAdmin, String foodPriceAdmin) {
        this.imgFoodAdmin = imgFoodAdmin;
        this.foodNameAdmin = foodNameAdmin;
        this.foodPriceAdmin = foodPriceAdmin;
    }

    public int getImgFoodAdmin() {
        return imgFoodAdmin;
    }

    public void setImgFoodAdmin(int imgFoodAdmin) {
        this.imgFoodAdmin = imgFoodAdmin;
    }

    public String getFoodNameAdmin() {
        return foodNameAdmin;
    }

    public void setFoodNameAdmin(String foodNameAdmin) {
        this.foodNameAdmin = foodNameAdmin;
    }

    public String getFoodPriceAdmin() {
        return foodPriceAdmin;
    }

    public void setFoodPriceAdmin(String foodPriceAdmin) {
        this.foodPriceAdmin = foodPriceAdmin;
    }
}
