package com.example.transfinitte_decoders.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.pojos.DepartmentsPojo;

import java.util.ArrayList;

public class HomeDeptRecyclerAdapter extends RecyclerView.Adapter<HomeDeptRecyclerAdapter.MyDeptViewHolder> {
    DepartmentsPojo deptNames;
    public HomeDeptRecyclerAdapter(DepartmentsPojo deptNames){
        this.deptNames=deptNames;
    }

    public void setdata(DepartmentsPojo dept){
        deptNames=dept;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyDeptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cutsom_dept_layout_item,parent,false);
        return new MyDeptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDeptViewHolder holder, int position) {
        holder.deptName.setText(deptNames.getDepartments().get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("TAGs", deptNames.getDepartments().size()+" ");
        return deptNames.getDepartments().size();
    }

    public class MyDeptViewHolder extends RecyclerView.ViewHolder{
        private TextView deptName;
        public MyDeptViewHolder(@NonNull View itemView) {
            super(itemView);
            deptName=itemView.findViewById(R.id.dept_name);
        }
    }
}
