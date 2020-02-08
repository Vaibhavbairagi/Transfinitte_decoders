package com.example.transfinitte_decoders.adminDoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.transfinitte_decoders.DispenserAdapter;
import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.firestore.InventoryRecords;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class dispenser_activity extends AppCompatActivity {

    public InventoryRecords data_dispenser;
    RecyclerView recyclerView;
    private static DispenserAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispenser_activity);
        data_dispenser = new InventoryRecords();
        recyclerView=(RecyclerView)findViewById(R.id.medicines_recycle);
        adapter = new DispenserAdapter(data_dispenser.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseFirestore.getInstance().collection("Docs").whereEqualTo("title", "inventory").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.e("TAG", "inside onComplete");
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                data_dispenser = documentSnapshot.toObject(InventoryRecords.class);
                                adapter.setDispenserList(data_dispenser.getData());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
