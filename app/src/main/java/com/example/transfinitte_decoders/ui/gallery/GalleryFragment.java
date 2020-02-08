package com.example.transfinitte_decoders.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.service;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
Button b1,b2,b3,b4,b5;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        b1=root.findViewById(R.id.b1);
        b2=root.findViewById(R.id.b2);
        b3=root.findViewById(R.id.b3);
        b4=root.findViewById(R.id.b4);
        b5=root.findViewById(R.id.b5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(), service.class);
                i.putExtra("click",1);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(), service.class);
                i.putExtra("click",2);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(), service.class);
                i.putExtra("click",3);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(), service.class);
                i.putExtra("click",4);
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(), service.class);
                i.putExtra("click",5);
                startActivity(i);
            }
        });

        return root;
    }
}