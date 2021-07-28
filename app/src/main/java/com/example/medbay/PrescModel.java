package com.example.medbay;

public class PrescModel {
    String medicine;
    String dosage;
    int days;

    public PrescModel(String medicine, String dosage, int days) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.days = days;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDays() {
        return days+"";
    }

    public void setDays(int days) {
        this.days = days;
    }
}
