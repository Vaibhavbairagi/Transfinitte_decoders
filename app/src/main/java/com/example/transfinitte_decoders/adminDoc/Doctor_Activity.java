package com.example.transfinitte_decoders.adminDoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.transfinitte_decoders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Doctor_Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button add_patient;
    Button dis;
    RadioButton availability;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_);




        radioGroup = findViewById(R.id.radio_group);




    }
}
