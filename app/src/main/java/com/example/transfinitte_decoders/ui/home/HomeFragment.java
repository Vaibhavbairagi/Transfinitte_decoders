package com.example.transfinitte_decoders.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.transfinitte_decoders.Adapters.HomeDeptRecyclerAdapter;
import com.example.transfinitte_decoders.Adapters.HomeSliderAdapter;
import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;

import com.example.transfinitte_decoders.adminDoc.ambulance;
import com.example.transfinitte_decoders.adminDoc.data;
import com.example.transfinitte_decoders.firestore.UserPrescriptionRecords;
import com.example.transfinitte_decoders.location;
import com.example.transfinitte_decoders.pojos.DepartmentsPojo;
import com.example.transfinitte_decoders.pojos.HomeSliderPojo;
import com.example.transfinitte_decoders.utils.MyBounceInterpolator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private int CARD_COUNT, currentPage = 0;
    private Context context;
    private ViewPager viewPager;
    private CardView cardView;
    private Button sosButton;
    private RelativeLayout bottom_sheet,bottom_sheet_bg,b1,b2;
    private BottomSheetBehavior sheetBehavior;
    public static RecyclerView recyclerView;
    public static HomeDeptRecyclerAdapter recyclerAdapter;
    Button btn_ambulance, btn_apollo, btn_volunteer;
    public static ProgressBar progressBar;
    data available;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = container.getContext();
        viewPager = root.findViewById(R.id.home_view_pager);
        cardView= root.findViewById(R.id.sos_card);
        sosButton= root.findViewById(R.id.sos_button);
        bottom_sheet= root.findViewById(R.id.home_bottom_sheet);
        bottom_sheet_bg=root.findViewById(R.id.bottom_sheet_bg);
        b1= root.findViewById(R.id.b1);
        b2=root.findViewById(R.id.b2);
        sheetBehavior=BottomSheetBehavior.from(bottom_sheet);
        recyclerView= root.findViewById(R.id.home_departments_recycler_view);
        progressBar= root.findViewById(R.id.progress_bar);

        btn_ambulance = root.findViewById(R.id.btn_ambulance);
        btn_apollo = root.findViewById(R.id.btn_apollo);
        btn_volunteer = root.findViewById(R.id.btn_volunteer);
        FirebaseFirestore.getInstance().collection("Docs").whereEqualTo("title", "loc").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                available = documentSnapshot.toObject(data.class);

                            }
                        } else {
                            //Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btn_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:8056167057"));
                    if(available.getAvailable())startActivity(callIntent);
                    else Toast.makeText(context, " Not Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Permissions not granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), location.class);
                startActivity(intent);
            }
        });
        btn_apollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()){
                    onCall();
                }
                else{
                    Toast.makeText(context, "Permissions not granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setUpViewPager();

        setUpDeptRecyclerView();

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b2.getVisibility()!=View.VISIBLE){
                    b2.setVisibility(View.VISIBLE);
                }
                if(b1.getVisibility()!=View.GONE){
                    b1.setVisibility(View.GONE);
                }
                final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                cardView.startAnimation(myAnim);
                cardView.postOnAnimationDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                            //link onclick functions here
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }
                },500);

            }
        });
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                    case BottomSheetBehavior.STATE_DRAGGING:
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:{

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        bottom_sheet_bg.setVisibility(View.GONE);
                    }
                    break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                bottom_sheet_bg.setVisibility(View.VISIBLE);
                bottom_sheet_bg.setAlpha(v);
            }
        });
        bottom_sheet_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


        return root;
    }

    public void onCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:8534898277"));
        startActivity(callIntent);
    }
    private void setUpDeptRecyclerView() {

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerAdapter= new HomeDeptRecyclerAdapter(MainActivity.recyclerdata);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setdata(MainActivity.recyclerdata);
    }

    private void setUpViewPager(){

        ArrayList<HomeSliderPojo> cardComponents = new ArrayList<>();
        fetchDiseaseDetails(cardComponents);
        CARD_COUNT = cardComponents.size();
        viewPager.setAdapter(new HomeSliderAdapter(context, cardComponents,bottom_sheet,bottom_sheet_bg,b1,b2));
        final Handler handler = new Handler();
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == CARD_COUNT) {
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, 3000, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void fetchDiseaseDetails(ArrayList<HomeSliderPojo> cardComponents) {
        cardComponents.add(new HomeSliderPojo(R.drawable.home_cold_disease_image,"COLD"));
        ArrayList<String> a1= new ArrayList<>();
        a1.add("Stay Hydrated.");
        a1.add("Sip warm liquids.");
        a1.add("Add moisture to the air.");
        a1.add("Take rest.");
        a1.add("Drink Orange Juice.");
        cardComponents.get(0).setRemedies(a1);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_cough_disease_image,"COUGH"));
        ArrayList<String> a2= new ArrayList<>();

        a2.add("Do Salt-Water gargle.");
        a2.add("Drink Honey tea.");
        a2.add("Drink Ginger tea.");
        a2.add("Stay hydrated.");
        a2.add("Eat Marshmallow root.");
        cardComponents.get(1).setRemedies(a2);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_fever_disease_image,"FEVER"));
        ArrayList<String> a3= new ArrayList<>();
        a3.add("Take a sponge bath with lukewarm water.");
        a3.add("Wear light pajamas or clothing.");
        a3.add("Get plenty of water.");
        a3.add("Drink plenty of water.");
        a3.add("Eat popsicles.");
        cardComponents.get(2).setRemedies(a3);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_bodypain_disease_image,"BODY PAIN"));
        ArrayList<String> a4= new ArrayList<>();
        a4.add("Drink ginger tea.");
        a4.add("Use hot and cold compresses.");
        a4.add("Drink apple cider vinegar.");
        a4.add("Give rest to your body.");
        a4.add("apply ice to the affected area.");
        cardComponents.get(3).setRemedies(a4);
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    onCall();
                } else {
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}