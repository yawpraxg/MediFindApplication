package com.example.medifind2020;

public class List {

    public String image, brandName, genName;

    public List() {

    }

    public List(String image, String brandName, String genName) {
        this.image = image;
        this.brandName = brandName;
        this.genName = genName;
    }

    public String getImage() {
        return image;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getGenName() {
        return genName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }
}
