package com.example.transfinitte_decoders.ui.dispenser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.DispenserAdapter;
import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;

public class DispenserFragment extends Fragment {

    private DispenserViewModel toolsViewModel;
    RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dispenser_fragment, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.medicines_recycle);
        adapter = new DispenserAdapter(MainActivity.data_dispenser.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}