package com.example.encontrosuniversitarios.view.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.view.fragment.AlterarSenhaListener;
import com.example.encontrosuniversitarios.view.fragment.RedefinicaoSenhaListener;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EsqueciSenhaActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private EditText email;
    private EditText token;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recuperação de senha");
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        Button generateCode = findViewById(R.id.button_generate_token);
        Button updatePassword = findViewById(R.id.button_redefinir_senha);
        email = findViewById(R.id.edit_redefinir_senha);
        token = findViewById(R.id.token);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);

        generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.recuperacaoSenha(email.getText().toString(), new RedefinicaoSenhaListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(),"O email de recuperação de senha foi enviado para: "+email.getText(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(getApplicationContext(),"Não foi possível enviar o email",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onInvalidField() {
                        email.setError("Email inválido");
                    }
                });
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.alterarSenha(token.getText().toString(), password.getText().toString(),
                        confirmPassword.getText().toString(), new AlterarSenhaListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getApplicationContext(),"Sua senha foi alterada com sucesso",Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(getApplicationContext(), "Não foi possível realizar esta operação",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onPasswordsDoesntMatch() {
                                password.setError("As senhas não são iguais");
                                confirmPassword.setError("As senhas não são iguais");
                            }

                            @Override
                            public void onInvalidToken() {
                                Toast.makeText(getApplicationContext(), "O código fornecido é inválido, gere seu código no formulário acima e tente novamente", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onEmptyField(String fieldName) {
                                showFieldErrorMessage(fieldName);
                            }

                            @Override
                            public void onShortPassword() {
                                password.setError("A senha deve conter no mínimo 6 caracteres");
                            }
                        });
            }
        });
    }

    void showFieldErrorMessage(String fieldName){
        String campoObrigatorio = "Este campo é obrigatório";
        switch(fieldName){
            case "TOKEN":
                token.setError(campoObrigatorio);
            case "PASSWORD":
                password.setError(campoObrigatorio);
            case "CPASSWORD":
                confirmPassword.setError(campoObrigatorio);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
