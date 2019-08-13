package com.example.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.viewmodel.CadastroUsuarioViewModel;

public class CadastroUsuarioFragment extends Fragment {
    private CadastroUsuarioViewModel cadastroUsuarioViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cadastroUsuarioViewModel = ViewModelProviders.of(this).get(CadastroUsuarioViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        Button salvar = view.findViewById(R.id.buttonSalvarCadastro);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"clicou",Toast.LENGTH_LONG).show();
                cadastroUsuarioViewModel.cadastrarUsuario("Matheus", "1212",
                        "mariana@example.com", "324uyg", new CadastroUsuarioListener() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onEmptyField(String message) {

                            }

                            @Override
                            public void onInvalidEmail(String message) {

                            }

                            @Override
                            public void onInvalidPassword(String message) {

                            }

                            @Override
                            public void onInvalidMatricula(String message) {

                            }

                            @Override
                            public void onAlreadyTakenEmail() {
                                Toast.makeText(getContext(),"AlreadyTakenEmail",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onAlreadyTakenMatricula() {
                                Toast.makeText(getContext(),"AlreadyTakenMatricula",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
