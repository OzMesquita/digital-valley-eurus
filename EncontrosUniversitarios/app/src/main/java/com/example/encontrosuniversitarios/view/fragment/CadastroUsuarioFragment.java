package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.viewmodel.CadastroUsuarioViewModel;

public class CadastroUsuarioFragment extends Fragment {
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtMatricula;
    private EditText edtSenha;
    private CadastroUsuarioViewModel cadastroUsuarioViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cadastroUsuarioViewModel = ViewModelProviders.of(this).get(CadastroUsuarioViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        Button salvar = view.findViewById(R.id.buttonSalvarCadastro);
        Button entre = view.findViewById(R.id.buttonEntre);
        edtNome = view.findViewById(R.id.edtNome);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMatricula = view.findViewById(R.id.edtMatricula);
        edtSenha = view.findViewById(R.id.edtSenha);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroUsuarioViewModel.cadastrarUsuario(edtNome.getText().toString(), edtMatricula.getText().toString(),
                        edtEmail.getText().toString(), edtSenha.getText().toString(), new CadastroUsuarioListener() {
                            @Override
                            public void onSuccess(String message) {
                                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                                navitageToLoginFragment();
                            }

                            @Override
                            public void onEmptyField(String field) {
                                showEmptyFieldMessage(field);
                            }

                            @Override
                            public void onInvalidEmail(String message) {
                                edtEmail.setError(getContext().getResources().getString(R.string.invalid_email_message));
                            }

                            @Override
                            public void onInvalidPassword(String message) {
                                edtSenha.setError(getContext().getResources().getString(R.string.invalid_password_message));
                            }

                            @Override
                            public void onInvalidMatricula(String message) {
                                edtMatricula.setError(getContext().getResources().getString(R.string.invalid_matricula_message));
                            }

                            @Override
                            public void onAlreadyTakenEmail() {
                                Toast.makeText(getContext(),R.string.already_taken_email_message,Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onAlreadyTakenMatricula() {
                                Toast.makeText(getContext(),R.string.already_taken_matricula_message,Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        entre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navitageToLoginFragment();
            }
        });
        return view;
    }

    private void showEmptyFieldMessage(String campo) {
        switch (campo) {
            case "Nome":
                edtNome.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
            case "Matricula":
                edtMatricula.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
            case "Senha":
                edtSenha.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
            case "Email":
                edtEmail.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
        }
    }

    private void navitageToLoginFragment(){
        Log.i("tee","naviTeste");
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();
    }


}
