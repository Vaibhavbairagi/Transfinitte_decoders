package com.example.transfinitte_decoders.firestore;

import java.util.ArrayList;
import java.util.List;

public class Prescription {

    String diagnosis;
    String symptoms;
    List<dosage> meds;
    String followUp;
    String doctor;

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public List<dosage> getMeds() {
        return meds;
    }

    public void setMeds(List<dosage> meds) {
        this.meds = meds;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public Prescription(String diagnosis, String symptoms, List<dosage> meds, String followUp, String doctor) {
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.meds = meds;
        this.followUp = followUp;
        this.doctor = doctor;
    }

    public Prescription() {
        meds = new ArrayList<>();
        diagnosis=symptoms=followUp=doctor="NONE";
        meds.add(new dosage("paracetamol",10));
        meds.add(new dosage("paracetamol",10));
        meds.add(new dosage("paracetamol",10));
    }
}
