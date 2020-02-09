package com.example.transfinitte_decoders.firestore;

import java.util.ArrayList;
import java.util.List;

public class Prescription {

    String diagnosissymptoms;
    String meds;
    String followUp;
    String doctor;
    boolean morning,afternoon,night;
    String feedback;

    public Prescription() {
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public String getDiagnosissymptoms() {
        return diagnosissymptoms;
    }

    public void setDiagnosissymptoms(String diagnosissymptoms) {
        this.diagnosissymptoms = diagnosissymptoms;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Prescription(String diagnosissymptoms, String meds, String followUp, String doctor, boolean morning, boolean afternoon, boolean night) {
        this.diagnosissymptoms = diagnosissymptoms;
        this.meds = meds;
        this.followUp = followUp;
        this.doctor = doctor;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Prescription(String diagnosissymptoms, String meds, String followUp, String doctor, boolean morning, boolean afternoon, boolean night, String feedback) {
        this.diagnosissymptoms = diagnosissymptoms;
        this.meds = meds;
        this.followUp = followUp;
        this.doctor = doctor;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
        this.feedback = feedback;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }


}
