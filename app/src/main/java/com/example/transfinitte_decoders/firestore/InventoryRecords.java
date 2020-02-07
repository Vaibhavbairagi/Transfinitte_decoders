package com.example.transfinitte_decoders.firestore;

import java.util.ArrayList;
import java.util.List;

public class InventoryRecords {
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

    List<entry> data;

    public InventoryRecords() {
        data = new ArrayList<>();
    }

    public InventoryRecords(List<entry> data) {
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
FirebaseFirestore.getInstance().collection("Docs").whereEqualTo("userId", "inventory").get()
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
