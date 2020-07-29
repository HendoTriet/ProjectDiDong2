package com.example.projectdidong.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModelLop extends ViewModel {

    private MutableLiveData<String> mText;

    public ModelLop ( ) {
        mText = new MutableLiveData<> ( );
        mText.setValue ( "This is slideshow fragment" );
    }

    public LiveData<String> getText ( ) {
        return mText;
    }
}