package com.example.transfinitte_decoders.adminDoc;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.firestore.Prescription;
import com.example.transfinitte_decoders.firestore.UserPrescriptionRecords;
import com.example.transfinitte_decoders.notification.MyNotificationPublisher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Medical_Report extends AppCompatActivity {
    EditText rollno;
    EditText symptoms;
    EditText med;
    EditText followup;
    CheckBox morning;
    CheckBox afternoon;
    CheckBox night;
    UserPrescriptionRecords data;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__report);
        rollno = (EditText)findViewById(R.id.roll_no);
        med = (EditText)findViewById(R.id.prescription);
        symptoms = (EditText)findViewById(R.id.symptoms);
        followup = (EditText) findViewById(R.id.followup);
        morning = (CheckBox)findViewById(R.id.morning);
        afternoon = (CheckBox)findViewById(R.id.afternoon);
        night = (CheckBox)findViewById(R.id.night);
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("userId", "naya").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                data = documentSnapshot.toObject(UserPrescriptionRecords.class);

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        submit  = (Button)findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prescription prescription = new Prescription(symptoms.getText().toString(),med.getText().toString(),followup.getText().toString(),"mohan",morning.isChecked(),afternoon.isChecked(),night.isChecked());
                data.getPrescriptions().add(prescription);
                FirebaseFirestore.getInstance().collection("Users").document("TEST").set(data);
                //to be executed on clicking medical records
                /*AlarmManager alarmMgr;
                PendingIntent alarmIntent;
                alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), AlarmReciever.class);
                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + 10,
                        AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);*/
            }
        });
    }
    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("NITT Hospital")
                .setContentText("Time for your Meds")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.medicine)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, LoginActivityDoc.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
