package com.example.transfinitte_decoders.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.transfinitte_decoders.Adapters.HomeDeptRecyclerAdapter;
import com.example.transfinitte_decoders.Adapters.HomeSliderAdapter;
import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;
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
    private RecyclerView recyclerView;
    public static HomeDeptRecyclerAdapter recyclerAdapter;

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
        a1.add("1Remedy1");
        a1.add("1Remedy2");
        a1.add("1Remedy3");
        a1.add("1Remedy4");
        a1.add("1Remedy5");
        cardComponents.get(0).setRemedies(a1);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_cough_disease_image,"COUGH"));
        ArrayList<String> a2= new ArrayList<>();
        a2.add("2Remedy1");
        a2.add("2Remedy2");
        a2.add("2Remedy3");
        a2.add("2Remedy4");
        a2.add("2Remedy5");
        cardComponents.get(1).setRemedies(a2);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_fever_disease_image,"FEVER"));
        ArrayList<String> a3= new ArrayList<>();
        a3.add("3Remedy1");
        a3.add("3Remedy2");
        a3.add("3Remedy3");
        a3.add("3Remedy4");
        a3.add("3Remedy5");
        cardComponents.get(2).setRemedies(a3);
        cardComponents.add(new HomeSliderPojo(R.drawable.home_bodypain_disease_image,"BODY PAIN"));
        ArrayList<String> a4= new ArrayList<>();
        a4.add("4Remedy1");
        a4.add("4Remedy2");
        a4.add("4Remedy3");
        a4.add("4Remedy4");
        a4.add("4Remedy5");
        cardComponents.get(3).setRemedies(a4);
    }
}