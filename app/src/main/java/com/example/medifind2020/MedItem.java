package com.example.medifind2020;

import android.util.Log;

import java.util.Arrays;
import java.util.Map;

public class MedItem {
    public String id;
    public String name, gen_name, color, size, prop, dosage, side_eff, thumbnail;



    public MedItem() { }

    public MedItem(String name, String gen_name, String color, String size, String prop, String dosage, String side_eff, String thumbnail) {
        this.name = name;
        this.gen_name = gen_name;
        this.color = color;
        this.size = size;
        this.prop = prop;
        this.dosage = dosage;
        this.side_eff = side_eff;
        this.thumbnail = thumbnail;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
