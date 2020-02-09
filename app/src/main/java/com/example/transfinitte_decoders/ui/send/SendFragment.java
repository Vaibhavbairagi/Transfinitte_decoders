package com.example.transfinitte_decoders.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.chatbot.Chat_Head_Service;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getContext().startService(new Intent(getContext(), Chat_Head_Service.class));
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        Intent out = new Intent(getActivity(),MainActivity.class);
        startActivity(out);
        getActivity().finish();
        return root;
    }
}