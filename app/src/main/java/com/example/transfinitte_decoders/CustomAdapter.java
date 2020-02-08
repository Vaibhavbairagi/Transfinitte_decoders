package com.example.transfinitte_decoders;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.firestore.Prescription;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Prescription> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewdiaby;
        TextView textViewdiagwith;
        TextView textViewfol;
        CheckBox mo,af,ni;
        TextView medView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewdiaby = (TextView) itemView.findViewById(R.id.txt_diagby);
            this.textViewfol = itemView.findViewById(R.id.txt_followup);
            this.textViewdiagwith = (TextView) itemView.findViewById(R.id.txt_diagwith);
            this.medView = itemView.findViewById(R.id.txt_meds);
            mo=itemView.findViewById(R.id.mo);
            af=itemView.findViewById(R.id.af);
            ni=itemView.findViewById(R.id.ni);
        }
    }

    public CustomAdapter(List<Prescription> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewdiaby = holder.textViewdiaby;
        TextView textViewdiagwith = holder.textViewdiagwith;
        TextView textViewfol=holder.textViewfol;
        TextView medView=holder.medView;
        CheckBox mo=holder.mo,af=holder.af,ni=holder.ni;

        textViewdiaby.setText(dataSet.get(listPosition).getDoctor());
        textViewdiagwith.setText(dataSet.get(listPosition).getDiagnosissymptoms());
        textViewfol.setText(dataSet.get(listPosition).getFollowUp());
        medView.setText(dataSet.get(listPosition).getMeds());
        mo.setChecked(dataSet.get(listPosition).isMorning());
        af.setChecked(dataSet.get(listPosition).isAfternoon());
        ni.setChecked(dataSet.get(listPosition).isNight());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
