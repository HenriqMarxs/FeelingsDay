package com.example.feelingsday.ui.RegisterTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterTaskViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RegisterTaskViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Você não possui nenhum contato adicionado");
    }

    public LiveData<String> getText() {
        return mText;
    }
}