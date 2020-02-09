package com.example.transfinitte_decoders.Adapters;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfinitte_decoders.DoctorsActivity;
import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.pojos.DepartmentsPojo;
import com.example.transfinitte_decoders.ui.home.HomeFragment;

import java.util.ArrayList;

public class HomeDeptRecyclerAdapter extends RecyclerView.Adapter<HomeDeptRecyclerAdapter.MyDeptViewHolder> {
    DepartmentsPojo deptNames;
    public HomeDeptRecyclerAdapter(DepartmentsPojo deptNames){
        this.deptNames=deptNames;
    }

    public void setdata(DepartmentsPojo dept){
        deptNames=dept;
        Handler handler= new Handler();
        notifyDataSetChanged();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeFragment.progressBar.setVisibility(View.GONE);
                HomeFragment.recyclerView.setVisibility(View.VISIBLE);

            }
        },1000);
    }
    @NonNull
    @Override
    public MyDeptViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cutsom_dept_layout_item,parent,false);
        final TextView textView=view.findViewById(R.id.dept_name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(parent.getContext(),DoctorsActivity.class);
                intent.putExtra("dept",textView.getText());
                parent.getContext().startActivity(intent);
            }
        });
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
