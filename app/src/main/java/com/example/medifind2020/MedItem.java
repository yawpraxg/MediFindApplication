package com.example.medifind2020;

import java.util.Map;

public class MedItem {

    public String name, gen_name, color, size, prop, dosage, side_eff, image;

    public MedItem() { }

    public MedItem(String name, String gen_name, String color, String size, String prop, String dosage, String side_eff, String image) {
        this.name = name;
        this.gen_name = gen_name;
        this.color = color;
        this.size = size;
        this.prop = prop;
        this.dosage = dosage;
        this.side_eff = side_eff;
        this.image = image;
    }

    public MedItem(Map<String, Object> map) {
        try {
            this.name = map.get("name").toString();
            this.gen_name = map.get("gen_name").toString();
            this.color = map.get("color").toString();
            this.size = map.get("size").toString();
            this.prop = map.get("prop").toString();
            this.dosage = map.get("dosage").toString();
            this.side_eff = map.get("side_eff").toString();
            this.image = map.get("image").toString();
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

    public String getImage() {
        return image;
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

    public void setImage(String image) {
        this.image = image;
    }
}
