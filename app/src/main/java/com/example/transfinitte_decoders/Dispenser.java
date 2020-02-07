package com.example.transfinitte_decoders;

public class Dispenser {
    private String medicine;
    private int quantity;

    public Dispenser(String medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
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
