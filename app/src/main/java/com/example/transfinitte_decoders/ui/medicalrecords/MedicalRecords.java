package com.example.transfinitte_decoders.ui.medicalrecords;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.CustomAdapter;
import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;

import java.util.ArrayList;

public class MedicalRecords extends Fragment {

    RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    public static MedicalRecords newInstance() {
        return new MedicalRecords();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.medical_records_fragment, container, false);

        recyclerView=view.findViewById(R.id.lv_med);
        adapter = new CustomAdapter(MainActivity.data.getPrescriptions());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
