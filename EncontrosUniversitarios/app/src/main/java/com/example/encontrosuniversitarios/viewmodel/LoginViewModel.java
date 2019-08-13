package com.example.encontrosuniversitarios.viewmodel;

import android.os.Handler;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import java.util.Observable;

public class LoginViewModel {

    public ObservableField<String> matricula = new ObservableField<>("");
    public ObservableField<String> senha = new ObservableField<>("");
    public ObservableBoolean estadoLogin = new ObservableBoolean();
    public ObservableBoolean loginFeito = new ObservableBoolean();


    public void onLoginClick(){

        String mEmail = matricula.get();
        String mPassword = senha.get();

        if (mEmail.equals("") || mPassword.equals("")){
            return;
        }

        // inicia o loading
        estadoLogin.set(true);

        // simula um delay de 1,5 segundo
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                estadoLogin.set(false);
                loginFeito.set(true);
            }
        },1500);


    }
}
