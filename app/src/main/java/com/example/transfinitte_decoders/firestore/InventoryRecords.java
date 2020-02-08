package com.example.transfinitte_decoders.firestore;

import java.util.ArrayList;
import java.util.List;

public class InventoryRecords {

    String title;
    List<entry> data;

    public InventoryRecords() {
        data = new ArrayList<>();
        title = "inventory";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InventoryRecords(List<entry> data) {
        title = "inventory";
        this.data = data;
    }

    public List<entry> getData() {
        return data;
    }

    public void setData(List<entry> data) {
        this.data = data;
    }
}

/*
Replace this code into area where you want to fetch (onStart usually)

InventoryRecords data;
FirebaseFirestore.getInstance().collection("Docs").whereEqualTo("title", "inventory").get()
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
