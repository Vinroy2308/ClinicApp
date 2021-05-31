package com.example.medbay;

public class Doctor2 {
    int id;
    String name;
    String specialize;

    public Doctor2(int id, String name, String specialize) {
        this.id = id;
        this.name = name;
        this.specialize = specialize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }
}
