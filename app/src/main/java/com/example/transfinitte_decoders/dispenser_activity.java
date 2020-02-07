package com.example.transfinitte_decoders;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class dispenser_activity extends AppCompatActivity {

    RecyclerView medicines_recycle;

    private List<Dispenser> dispenserList = new ArrayList<>();
    DispenserAdapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispenser_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        medicines_recycle = findViewById(R.id.medicines_recycle);
        dAdapter = new DispenserAdapter(dispenserList);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getApplicationContext());
        medicines_recycle.setLayoutManager(mLayoutmanager);
        medicines_recycle.setItemAnimator(new DefaultItemAnimator());
        medicines_recycle.setAdapter(dAdapter);

        prepareMovieData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    void prepareMovieData(){
        Dispenser dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);
        dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);
        dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);
        dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);
        dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);
        dispenser = new Dispenser("abc", 30);
        dispenserList.add(dispenser);

        dAdapter.notifyDataSetChanged();
    }
}
