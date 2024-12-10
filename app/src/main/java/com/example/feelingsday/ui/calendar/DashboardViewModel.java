package com.example.feelingsday.ui.calendar;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Tarefas da data selecionada");
    }

    public LiveData<String> getText() {
        return mText;
    }
}