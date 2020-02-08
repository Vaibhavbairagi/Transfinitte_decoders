package com.example.transfinitte_decoders.ui.dispenser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DispenserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DispenserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}