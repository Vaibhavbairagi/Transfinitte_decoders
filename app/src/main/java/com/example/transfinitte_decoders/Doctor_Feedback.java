package com.example.transfinitte_decoders;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfinitte_decoders.firestore.Prescription;
import com.example.transfinitte_decoders.firestore.UserPrescriptionRecords;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Doctor_Feedback extends AppCompatActivity {

    UserPrescriptionRecords data;
    ArrayList<String> feedbackList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__feedback);
        ListView feedbackRL = findViewById(R.id.feedback_lis_view);
        data = new UserPrescriptionRecords();
        feedbackList = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, feedbackList);
        adapter.notifyDataSetChanged();
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("userId", "naya").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                data = documentSnapshot.toObject(UserPrescriptionRecords.class);
                                for (Prescription prescription : data.getPrescriptions()) {
                                    feedbackList.add(prescription.getFeedback());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}

