package com.example.lola;

public class CartModel {

    private String name,categiry,price,quentity,code,total,imageString;
    private int imgId;
    private byte imgbyte[];

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategiry() {
        return categiry;
    }

    public void setCategiry(String categiry) {
        this.categiry = categiry;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public byte[] getImgbyte() {
        return imgbyte;
    }

    public void setImgbyte(byte[] imgbyte) {
        this.imgbyte = imgbyte;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
