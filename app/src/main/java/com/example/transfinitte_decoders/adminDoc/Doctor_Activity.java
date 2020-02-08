package com.example.transfinitte_decoders.adminDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.transfinitte_decoders.R;

public class Doctor_Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button add_patient;
    Button dis;
    RadioButton availability;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_2);

        add_patient = findViewById(R.id.add_patient);


        radioGroup = findViewById(R.id.radio_group);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        availability = findViewById(selectedId);

        if(availability.getText().toString().equals("Not Available")){
            add_patient.setEnabled(false);
        }

        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("TAG", availability.getText().toString());
                Intent intent = new Intent(Doctor_Activity.this, Medical_Report.class);
                startActivity(intent);
            }
        });



    }
}