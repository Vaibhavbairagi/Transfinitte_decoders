package com.example.transfinitte_decoders.adminDoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.firestore.Prescription;
import com.example.transfinitte_decoders.firestore.UserPrescriptionRecords;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Medical_Report extends AppCompatActivity {
    EditText rollno;
    EditText symptoms;
    EditText med;
    EditText followup;
    CheckBox morning;
    CheckBox afternoon;
    CheckBox night;
    UserPrescriptionRecords data;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__report);
        rollno = (EditText)findViewById(R.id.roll_no);
        med = (EditText)findViewById(R.id.prescription);
        symptoms = (EditText)findViewById(R.id.symptoms);
        followup = (EditText) findViewById(R.id.followup);
        morning = (CheckBox)findViewById(R.id.morning);
        afternoon = (CheckBox)findViewById(R.id.afternoon);
        night = (CheckBox)findViewById(R.id.night);
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("userId", "naya").get()
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
        submit  = (Button)findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prescription prescription = new Prescription(symptoms.getText().toString(),med.getText().toString(),followup.getText().toString(),LoginActivityDoc.mAuth.getCurrentUser().getEmail(),morning.isChecked(),afternoon.isChecked(),night.isChecked());
                data.getPrescriptions().add(prescription);
                FirebaseFirestore.getInstance().collection("Users").document("TEST").set(data);
            }
        });
    }
}
