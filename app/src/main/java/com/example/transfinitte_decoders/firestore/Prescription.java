package com.example.transfinitte_decoders.firestore;

import java.util.List;

public class Prescription {
    public class dosage{
        String medicine;
        int perDay;

        public dosage() {
        }

        public String getMedicine() {
            return medicine;
        }

        public void setMedicine(String medicine) {
            this.medicine = medicine;
        }

        public int getPerDay() {
            return perDay;
        }

        public void setPerDay(int perDay) {
            this.perDay = perDay;
        }

        public dosage(String medicine, int perDay) {
            this.medicine = medicine;
            this.perDay = perDay;
        }
    }

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
    }
}
