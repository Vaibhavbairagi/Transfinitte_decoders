package com.example.transfinitte_decoders.firestore;

import java.util.ArrayList;
import java.util.List;

public class UserPrescriptionRecords {
    String userId;
    List<Prescription> prescriptions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public UserPrescriptionRecords() {
        userId="new";
        prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription());
    }

    public UserPrescriptionRecords(String userId, List<Prescription> prescriptions) {
        this.userId = userId;
        this.prescriptions = prescriptions;
    }

    public UserPrescriptionRecords(String userId) {
        this.userId = userId;
        prescriptions = new ArrayList<>();
    }
}

/*
Replace this code into area where you want to fetch (onStart usually)

UserPrescriptionRecords data;
FirebaseFirestore.getInstance().collection("Users").whereEqualTo("userId", "test").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                data = documentSnapshot.toObject(UserPrescriptionRecords.class);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
 */
