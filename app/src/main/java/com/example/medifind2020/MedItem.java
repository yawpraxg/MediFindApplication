package com.example.medifind2020;

import android.util.Log;

import java.util.Arrays;
import java.util.Map;

public class MedItem {
    public String id;
    public String name, gen_name, color, size, prop, dosage,side_eff, thumbnail, img1, img2;



    public MedItem() { }

    public MedItem(String name, String gen_name, String color, String size, String prop, String dosage, String side_eff, String thumbnail, String img1, String img2) {
        this.name = name;
        this.gen_name = gen_name;
        this.color = color;
        this.size = size;
        this.prop = prop;
        this.dosage = dosage;
        this.side_eff = side_eff;
        this.thumbnail = thumbnail;
        this.img1 = img1;
        this.img2 = img2;
    }

    public MedItem(Map<String, Object> map, String id) {
        try {
            this.id = id;
            this.name = map.get("name").toString();
            this.gen_name = map.get("gen_name").toString();
            this.color = map.get("color").toString();
            this.size = map.get("size").toString();
            this.prop = map.get("prop").toString();
            this.dosage = map.get("dosage").toString();
            this.side_eff = map.get("side_eff").toString();
            this.thumbnail = map.get("thumbnail").toString();
            this.img1 = map.get("img1").toString();
            this.img2 = map.get("img2").toString();

            Log.d("MedItem", Arrays.toString(map.keySet().toArray()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getGenName() {
        return gen_name;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getProp() {
        return prop;
    }

    public String getDosage() {
        return dosage;
    }

    public String getSideEff() {
        return side_eff;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImg1() { return img1; }

    public String getImg2() { return img2; }

    public void setBrandName(String name) {
        this.name = name;
    }

    public void setGenName(String gen_name) {
        this.gen_name = gen_name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setSideEff(String side_eff) {
        this.side_eff = side_eff;
    }

    public void setThumbnail(String image) {
        this.thumbnail = image;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
