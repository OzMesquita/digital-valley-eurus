package com.example.encontrosuniversitarios.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.encontrosuniversitarios.R;
//import com.example.encontrosuniversitarios.databinding.FragmentLoginBinding;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button recuperarSenha;
    private EditText edtEmail;
    private EditText edtSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Button botaoLogar, cadastrar;
        botaoLogar = view.findViewById(R.id.buttonEntrarPerfil);
        cadastrar = view.findViewById(R.id.buttonCadastrar);
        edtEmail = view.findViewById(R.id.editTextEmail);
        edtSenha = view.findViewById(R.id.editTextSenha);
        recuperarSenha = view.findViewById(R.id.buttonEsqueciSenha);

        recuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRedefinirSenhaDialog();
            }
        });

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.realizarLogin(edtEmail.getText().toString(), edtSenha.getText().toString(),
                        new LoginListener() {
                            @Override
                            public void onSuccess(Usuario usuario) {
                                MySharedPreferences preferences = MySharedPreferences.getInstance(getContext());
                                preferences.setUserData(usuario);

                                changeLoginFragmentOnLogin(preferences.getUserAccessLevel());
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(getContext(), getContext().getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onEmptyField(String field) {
                                showEmptyFieldMessage(field);
                            }

                            @Override
                            public void onInvalidPassword(String message) {
                                edtSenha.setError(getContext().getResources().getString(R.string.invalid_passord_login_message));
                            }

                            @Override
                            public void onInvalidEmail(String message) {
                                edtEmail.setError(getContext().getResources().getString(R.string.invalid_email_message));
                            }

                            @Override
                            public void onUnregisteredEmail() {
                                edtEmail.setError(getContext().getResources().getString(R.string.unregistered_email_message));
                            }

                            @Override
                            public void onWrongPassword() {
                                edtSenha.setError(getContext().getResources().getString(R.string.wrong_passord_login_message));
                            }
                        });
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navitageToCadastrarUsuarioFragment();
            }
        });

        return view;
    }

    private void showEmptyFieldMessage(String campo) {
        switch (campo) {
            case "Matricula":
                edtEmail.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
            case "Senha":
                edtSenha.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
        }
    }

    private void changeLoginFragmentOnLogin(int accessLevel) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (accessLevel == 0) {
            ft.replace(R.id.fragment_container, new AtividadesAlunoFragment());
        } else if (accessLevel == 1) {
            ft.replace(R.id.fragment_container, new RealizarFrequenciaFragment());
        } else {
            ft.replace(R.id.fragment_container, new AtividadesProfessorFragment());
        }
        ft.addToBackStack(null);
        ft.commit();

    }

    private void navitageToCadastrarUsuarioFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new CadastroUsuarioFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void showRedefinirSenhaDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View customLayout = getLayoutInflater().inflate(R.layout.redefinir_senha_dialog, null);

        Button redefinir = customLayout.findViewById(R.id.button_redefinir_senha);
        final EditText email = customLayout.findViewById(R.id.edit_redefinir_senha);

       redefinir.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loginViewModel.recuperacaoSenha(email.getText().toString(), new RedefinicaoSenhaListener() {
                   @Override
                   public void onSuccess() {
                       Toast.makeText(getContext(),"O email de recuperação de senha foi enviado para: "+email.getText(), Toast.LENGTH_LONG).show();
                   }
                   @Override
                   public void onFailure(String message) {

                   }
                   @Override
                   public void onInvalidField() {
                       email.setError("Email inválido");
                   }
               });
           }
       });
        builder.setView(customLayout);
        builder.show();
    }
}

