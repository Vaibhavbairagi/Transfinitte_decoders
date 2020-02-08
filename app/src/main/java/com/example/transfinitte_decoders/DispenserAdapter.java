package com.example.transfinitte_decoders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.firestore.entry;

import java.util.List;

public class DispenserAdapter extends RecyclerView.Adapter<DispenserAdapter.MyViewHolder> {


    private List<entry> dispenserList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_of_the_medicine);

            quantity = view.findViewById(R.id.quantity_of_the_medicine);
        }
    }

    public void setDispenserList(List<entry> data){
        dispenserList=data;
        notifyDataSetChanged();
    }

    public DispenserAdapter(List<entry> dispenserList) {
        this.dispenserList = dispenserList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_dispenser, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        DispenserAdapter.MyViewHolder myViewHolder = new DispenserAdapter.MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        entry entry_med_dis = dispenserList.get(position);
        holder.name.setText(entry_med_dis.getMedicine());
        holder.quantity.setText(String.valueOf(entry_med_dis.getQuantity()));

    }



    @Override
    public int getItemCount() {
        return dispenserList.size();
    }
}
