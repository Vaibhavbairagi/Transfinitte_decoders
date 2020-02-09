package com.example.transfinitte_decoders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transfinitte_decoders.firestore.Prescription;
import com.example.transfinitte_decoders.notification.AlarmReciever;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Prescription> dataSet;
    Context con;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewdiaby;
        TextView textViewdiagwith;
        TextView textViewfol;
        CheckBox mo,af,ni;
        TextView medView;
        TextView feedback;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewdiaby = (TextView) itemView.findViewById(R.id.txt_diagby);
            this.textViewfol = itemView.findViewById(R.id.txt_followup);
            this.textViewdiagwith = (TextView) itemView.findViewById(R.id.txt_diagwith);
            this.medView = itemView.findViewById(R.id.txt_meds);
            mo=itemView.findViewById(R.id.mo);
            af=itemView.findViewById(R.id.af);
            ni=itemView.findViewById(R.id.ni);
            feedback=itemView.findViewById(R.id.feedback);
        }
    }

    public CustomAdapter(List<Prescription> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        con = parent.getContext();
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
        final TextView feedback=holder.feedback;

        textViewdiaby.setText(dataSet.get(listPosition).getDoctor());
        textViewdiagwith.setText(dataSet.get(listPosition).getDiagnosissymptoms());
        textViewfol.setText(dataSet.get(listPosition).getFollowUp());
        medView.setText(dataSet.get(listPosition).getMeds());
        mo.setChecked(dataSet.get(listPosition).isMorning());
        af.setChecked(dataSet.get(listPosition).isAfternoon());
        ni.setChecked(dataSet.get(listPosition).isNight());
        if(dataSet.get(listPosition).getFeedback()!=null)
        feedback.setText("Feedback - " +dataSet.get(listPosition).getFeedback());

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                View view1 = LayoutInflater.from(con).inflate(R.layout.add_feedback, null);
                final EditText editText = view1.findViewById(R.id.companyName);
                builder.setView(view1).setPositiveButton("Save Feedback", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().toString().isEmpty()) {
                            Toast.makeText(con, "Feedback can't be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        dataSet.get(listPosition).setFeedback(editText.getText().toString());
                        feedback.setText("Feedback "+editText.getText().toString());
                        notifyDataSetChanged();
                        MainActivity.data.getPrescriptions().get(listPosition).setFeedback(editText.getText().toString());
                        MainActivity.setPrescriptionRecordData();
                    }
                });
                builder.show();
            }
        });

        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager)con.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(con, AlarmReciever.class);
        Calendar calendar = Calendar.getInstance();
        alarmIntent = PendingIntent.getBroadcast(con, 0, intent, 0);
        if(mo.isChecked()){
            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                calendar.getTimeInMillis()+1000,AlarmManager.INTERVAL_DAY, alarmIntent);
        }
        if(af.isChecked()){
            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    calendar.getTimeInMillis()+2000,AlarmManager.INTERVAL_DAY, alarmIntent);
        }
        if(ni.isChecked()){
            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    calendar.getTimeInMillis()+3000,AlarmManager.INTERVAL_DAY, alarmIntent);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
