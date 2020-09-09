package com.example.medifind2020;

import java.util.Map;

public class MedItem {

    public String name, gen_name, color;

    public MedItem() { }

    public MedItem(String name, String gen_name, String color) {
        this.name = name;
        this.gen_name = gen_name;
        this.color = color;
    }

    public MedItem(Map<String, Object> map) {
        try {
            this.name = map.get("name").toString();
            this.gen_name = map.get("gen_name").toString();
            this.color = map.get("color").toString();
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

    public String getColor() { return color; }

    public void setBrandName(String name) {
        this.name = name;
    }

    public void setGenName(String gen_name) {
        this.gen_name = gen_name;
    }

    public void setColor(String color) { this.color = color; }
}
