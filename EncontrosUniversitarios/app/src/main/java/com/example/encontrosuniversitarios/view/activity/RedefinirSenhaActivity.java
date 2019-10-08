package com.example.encontrosuniversitarios.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.exceptions.CampoVazioException;
import com.example.encontrosuniversitarios.view.fragment.RedefinicaoSenhaListener;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;

public class RedefinirSenhaActivity extends AppCompatActivity {

    private EditText campoEmail;
    private Button confirmar;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        this.getSupportActionBar().setTitle("Redefinir Senha");

        campoEmail = findViewById(R.id.edit_redefinir_senha);
        confirmar = findViewById(R.id.button_redefinir_senha);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loginViewModel.recuperacaoSenha(campoEmail.getText().toString(), new RedefinicaoSenhaListener() {

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(String message) {

                        }

                        @Override
                        public void onEmptyField(String field) {
                        }
                    });
                } catch (CampoVazioException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
