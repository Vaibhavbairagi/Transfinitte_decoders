package com.example.transfinitte_decoders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transfinitte_decoders.Adapters.DoctorRecyclerViewAdapter;
import com.example.transfinitte_decoders.pojos.DocsPojo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DoctorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DoctorRecyclerViewAdapter adapter;
    DocsPojo docsPojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        String deptName="";
        if(bundle!=null){
            deptName=bundle.getString("dept");
        }
        docsPojo= new DocsPojo();

        recyclerView= findViewById(R.id.doctors_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new DoctorRecyclerViewAdapter(docsPojo.getDocs());
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("Docs").whereEqualTo("Department", deptName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG1", task.getResult().toString());
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Log.d("TAG1",  documentSnapshot.toString());
                                docsPojo = documentSnapshot.toObject(DocsPojo.class);
                                adapter.setDoc(docsPojo.getDocs());
                                Log.d("TAG2", docsPojo.toString());

                            }
                        } else {
                            Log.d("TAG", "task.().toString()");
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
