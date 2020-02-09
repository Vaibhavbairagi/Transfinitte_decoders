package com.example.transfinitte_decoders.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.transfinitte_decoders.R;
import com.example.transfinitte_decoders.pojos.HomeSliderPojo;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class HomeSliderAdapter extends PagerAdapter {
    private ArrayList<HomeSliderPojo> cardComponents;
    private Context context;
    private RelativeLayout bottom_sheet,bottom_sheet_bg,b1,b2;
    private BottomSheetBehavior sheetBehavior;
    public HomeSliderAdapter(Context context, ArrayList<HomeSliderPojo> cardComponents, RelativeLayout bottom_sheet, RelativeLayout bottom_sheet_bg, RelativeLayout b1, RelativeLayout b2){
        this.cardComponents=cardComponents;
        this.context=context;
        this.bottom_sheet=bottom_sheet;
        this.bottom_sheet_bg=bottom_sheet_bg;
        this.b1=b1;
        this.b2=b2;
        sheetBehavior=BottomSheetBehavior.from(bottom_sheet);
    }
    @Override
    public int getCount() {
        return cardComponents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final int pos=position;
        View cardView=LayoutInflater.from(context).inflate(R.layout.home_slider_card_layout,container,false);
        ImageView diseaseImage= cardView.findViewById(R.id.home_card_image);
        TextView diseaseName=cardView.findViewById(R.id.home_card_disease_name);
        diseaseImage.setImageResource(cardComponents.get(position).getImageId());
        diseaseName.setText(cardComponents.get(position).getDiseaseName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1.getVisibility()!=View.VISIBLE){
                    b1.setVisibility(View.VISIBLE);
                }
                if(b2.getVisibility()!=View.GONE){
                    b2.setVisibility(View.GONE);
                }
                if (sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                    ImageView imageView= bottom_sheet.findViewById(R.id.sheet_disease_image);
                    imageView.setImageResource(cardComponents.get(pos).getImageId());
                    TextView t1=bottom_sheet.findViewById(R.id.rt1);
                    t1.setText(cardComponents.get(pos).getRemedies().get(0));
                    TextView t2=bottom_sheet.findViewById(R.id.rt2);
                    t2.setText(cardComponents.get(pos).getRemedies().get(1));
                    TextView t3=bottom_sheet.findViewById(R.id.rt3);
                    t3.setText(cardComponents.get(pos).getRemedies().get(2));
                    TextView t4=bottom_sheet.findViewById(R.id.rt4);
                    t4.setText(cardComponents.get(pos).getRemedies().get(3));
                    TextView t5=bottom_sheet.findViewById(R.id.rt5);
                    t5.setText(cardComponents.get(pos).getRemedies().get(4));
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
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

        container.addView(cardView);
        return cardView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
