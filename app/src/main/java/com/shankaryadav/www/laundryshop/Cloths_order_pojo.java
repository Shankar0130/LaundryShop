package com.shankaryadav.www.laundryshop;

public class Cloths_order_pojo {

    public String cloths_name;
    public String cloths_price;

    public Cloths_order_pojo(String cloths_name, String cloths_price) {
        this.cloths_name = cloths_name;
        this.cloths_price = cloths_price;
    }

    public Cloths_order_pojo() {
    }

    public String getCloths_name() {
        return cloths_name;
    }

    public void setCloths_name(String cloths_name) {
        this.cloths_name = cloths_name;
    }

    public void setCloths_price(String cloths_price) {
        this.cloths_price = cloths_price;
    }

    public String getCloths_price() {
        return cloths_price;
    }
}
