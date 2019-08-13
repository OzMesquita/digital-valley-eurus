package com.example.encontrosuniversitarios.view.fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.databinding.FragmentLoginBinding;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    FragmentLoginBinding view;
    ProgressDialog progressDialog;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button botaoLogar;
        botaoLogar = view.findViewById(R.id.buttonEntrarPerfil);
        final  EditText editTextMatri = view.findViewById(R.id.editTextMatricula);
        final EditText editTextSenh = view.findViewById(R.id.editTextSenha);


        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logar();
            }
        });


        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }

}

//    public void onLoginClick(View view){
//        view.getViewModel().onLoginClick();
//    }
//
//    public void bindLoadingState() {
//
//        view.getViewModel().estadoLogin.addOnPropertyChangedCallback(
//                new Observable.OnPropertyChangedCallback() {
//                    @Override
//                    public void onPropertyChanged(Observable observable, int i) {
//                        boolean estadoLogin= view.getViewModel().estadoLogin.get();
//                        if (estadoLogin){
//                            //progressDialog =
////                                    ProgressDialog.show(LoginFragment.this,"Carregando",
////                                            "Aguarde...",true, false);
//                        } else {
//                            progressDialog.dismiss();
//                        }
//                    }
//                });
//
//        view.getViewModel().loginFeito.addOnPropertyChangedCallback(
//                new Observable.OnPropertyChangedCallback() {
//                    @Override
//                    public void onPropertyChanged(Observable observable, int i) {
//                        boolean loginFeito = view.getViewModel().loginFeito.get();
//
//                        if (loginFeito){
////                            startActivity(
////                                    new Intent(
////                                            FragmentLogin.this, MainActivity.class));
//                        }
//                    }
//                });
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        view.getViewModel().onLoginClick();
//    }
//}
