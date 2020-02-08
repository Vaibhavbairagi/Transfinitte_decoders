package com.example.transfinitte_decoders.pojos;

import java.util.ArrayList;

public class HomeSliderPojo {
    private int imageId;
    private String diseaseName;
    private ArrayList<String> Remedies;

    public HomeSliderPojo(int imageId, String diseaseName) {
        this.imageId = imageId;
        this.diseaseName = diseaseName;
    }

    public ArrayList<String> getRemedies() {
        return Remedies;
    }

    public void setRemedies(ArrayList<String> remedies) {
        Remedies = remedies;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
