package com.example.transfinitte_decoders.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.pojos.Docs;

import java.util.List;

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<DoctorRecyclerViewAdapter.MyDoctorViewHolder> {

    private List<Docs> doc;

    public DoctorRecyclerViewAdapter(List<Docs> doc) {
        this.doc = doc;
    }

    @NonNull
    @Override
    public MyDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_details_item_card,parent,false);

        return new MyDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDoctorViewHolder holder, int position) {
        holder.docName.setText(doc.get(position).getName());
        holder.docPost.setText(doc.get(position).getPost());
        holder.docProfile.setText(doc.get(position).getProfile());
        boolean avail=doc.get(position).isAvailable();
        if(!avail){
            holder.btn_parent.setClickable(false);
            holder.btn_parent.setFocusable(false);
            holder.btn_parent.setBackgroundColor(Color.parseColor("#B3ffffff"));
            holder.bookAppointment.setText("Not Available");
        }
    }

    @Override
    public int getItemCount() {
        return doc.size();
    }

    public void setDoc(List<Docs> data){
        doc=data;
        notifyDataSetChanged();
    }

    public class MyDoctorViewHolder extends RecyclerView.ViewHolder{
        private TextView docName,docPost,docProfile;
        private Button bookAppointment;
        private RelativeLayout btn_parent;
        public MyDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            docName=itemView.findViewById(R.id.doc_name);
            docPost=itemView.findViewById(R.id.doc_post);
            docProfile=itemView.findViewById(R.id.doc_profile);
            bookAppointment=itemView.findViewById(R.id.book_appointment_btn);
            btn_parent=itemView.findViewById(R.id.book_btn_parent_card);

        }
    }
}
