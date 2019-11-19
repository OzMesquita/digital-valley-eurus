package ufc.russas.encontrosuniversitarios.view.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.view.fragment.AlterarSenhaListener;
import ufc.russas.encontrosuniversitarios.view.fragment.RedefinicaoSenhaListener;
import ufc.russas.encontrosuniversitarios.viewmodel.LoginViewModel;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
        final ProgressBar progressBar = findViewById(R.id.send_email_progress);
        final ProgressBar recoverProgressBas = findViewById(R.id.recover_password_progress);
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

                    @Override
                    public void onLoading() {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onDone() {
                        progressBar.setVisibility(View.GONE);
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

                            @Override
                            public void onLoading() {
                                recoverProgressBas.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onDone() {
                                recoverProgressBas.setVisibility(View.GONE);
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
