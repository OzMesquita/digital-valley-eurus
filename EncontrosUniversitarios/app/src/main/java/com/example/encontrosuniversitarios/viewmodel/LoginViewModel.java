package com.example.encontrosuniversitarios.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

//    public ObservableField<String> matricula = new ObservableField<>("");
//    public ObservableField<String> senha = new ObservableField<>("");
//    public ObservableBoolean estadoLogin = new ObservableBoolean();
//    public ObservableBoolean loginFeito = new ObservableBoolean();
//
//
//    public void onLoginClick(){
//
//        String mEmail = matricula.get();
//        String mPassword = senha.get();
//
//        if (mEmail.equals("") || mPassword.equals("")){
//            return;
//        }
//
//        // inicia o loading
//        estadoLogin.set(true);
//
//        // simula um delay de 1,5 segundo
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                estadoLogin.set(false);
//                loginFeito.set(true);
//            }
//        },1500);
//
//
//    }

    public MutableLiveData<String> matricula = new MutableLiveData<>();
    public MutableLiveData<String> senha = new MutableLiveData<>();


    public void logar() {
        Log.i("nada", "loog");

    }
}
