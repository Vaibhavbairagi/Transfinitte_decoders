package com.example.transfinitte_decoders.firestore;

public class entry{
    String medicine;
    int quantity;

    public entry(String medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public entry() {
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
