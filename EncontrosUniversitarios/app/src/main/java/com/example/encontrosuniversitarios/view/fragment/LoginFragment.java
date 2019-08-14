package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

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
import com.example.encontrosuniversitarios.databinding.FragmentLoginBinding;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding view;
    private EditText edtMatricula;
    private EditText edtSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false);

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Button botaoLogar, cadastrar;
        botaoLogar = view.findViewById(R.id.buttonEntrarPerfil);
        cadastrar = view.findViewById(R.id.buttonCadastrar);
        edtMatricula = view.findViewById(R.id.editTextMatricula);
        edtSenha = view.findViewById(R.id.editTextSenha);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logar(edtMatricula.getText().toString(), edtSenha.getText().toString(), new LoginListener() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                        navitageToProgramacaoDoDiaFragment();
                    }

                    @Override
                    public void onEmptyField(String field) {
                        showEmptyFieldMessage(field);
                    }

                    @Override
                    public void onInvalidPassword(String message) {
                        Toast.makeText(getContext(), R.string.invalid_passord_login_message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onInvalidMatricula(String message) {
                        Toast.makeText(getContext(), R.string.invalid_matricula_login_message, Toast.LENGTH_LONG).show();
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
                Log.i("printMatricula", "printmatri");
                edtMatricula.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
            case "Senha":
                Log.i("printSenha", "printsenha");
                edtSenha.setError(getContext().getResources().getString(R.string.blank_field_message));
                break;
        }
    }


    private void navitageToProgramacaoDoDiaFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new ProgramacaoDoDiaFragment());
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
}

