package com.example.transfinitte_decoders.adminDoc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfinitte_decoders.Doctor_Feedback;
import com.example.transfinitte_decoders.R;
import com.google.firebase.auth.FirebaseAuth;

public class Doctor_Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button add_patient;
    RadioButton availability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_2);

        add_patient = findViewById(R.id.add_patient);
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                availability = findViewById(selectedId);
                onRadioButtonClicked(availability);
                Log.e("TAG", "yes");
            }
        });


        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor_Activity.this, Medical_Report.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_for_admin_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Doctor_Activity.this, LoginActivityDoc.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dispenser:
                Intent intent1 = new Intent(Doctor_Activity.this, dispenser_activity.class);
                startActivity(intent1);
                break;
            case R.id.view_feedback:
                Intent intent2 = new Intent(Doctor_Activity.this, Doctor_Feedback.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        int selectedId = radioGroup.getCheckedRadioButtonId();
//        availability = findViewById(selectedId);
//        onRadioButtonClicked(availability);
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.available:
                if(checked){
                    Log.e("TAG", "available");
                    add_patient.setEnabled(true);
                    break;
                }
            case R.id.not_available:
                if(checked){
                    Log.e("TAG", "not available");
                    add_patient.setEnabled(false);
                    break;
                }
        }
    }
}