package com.example.transfinitte_decoders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class service extends AppCompatActivity {
LinearLayout l1,l2,l3,l4;
ScrollView l5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        int i =getIntent().getIntExtra("click",1);
        if(i==1){
            l1=findViewById(R.id.l1);
            l1.setVisibility(View.VISIBLE);
        }
        if(i==2){
            l5=findViewById(R.id.l2);
            l5.setVisibility(View.VISIBLE);
        }
        if(i==3){
            l2=findViewById(R.id.l3);
            l2.setVisibility(View.VISIBLE);
        }
        if(i==4){
            l3=findViewById(R.id.l4);
            l3.setVisibility(View.VISIBLE);
        }
        if(i==5){
            l4=findViewById(R.id.l5);
            l4.setVisibility(View.VISIBLE);
        }

    }
}
