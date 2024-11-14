package com.example.feelingsday.ui.home;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.feelingsday.R;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("12AM -Proxima tarefa");

    }


    public LiveData<String> getText() {
        return mText;
    }
}