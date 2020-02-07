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

import java.util.List;

public class DispenserAdapter extends RecyclerView.Adapter<DispenserAdapter.MyViewHolder> {


    private List<Dispenser> dispenserList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, availability, quantity;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_of_the_medicine);

            quantity = view.findViewById(R.id.quantity_of_the_medicine);
        }
    }

    public DispenserAdapter(List<Dispenser> dispenserList) {
        this.dispenserList = dispenserList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dispenser_medicines_list, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Dispenser dispenserMedicine = dispenserList.get(position);
        holder.name.setText(dispenserMedicine.getMedicine());
        holder.quantity.setText(String.valueOf(dispenserMedicine.getQuantity()));


    }



    @Override
    public int getItemCount() {
        return dispenserList.size();
    }
}
