package com.example.rilafragments.fragments.buscador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuscadorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BuscadorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}