package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ufc.russas.encontrosuniversitarios.R;

import ufc.russas.encontrosuniversitarios.model.webservice.VerificacaoMatricula;
import ufc.russas.encontrosuniversitarios.viewmodel.CadastroUsuarioViewModel;

public class CadastroUsuarioFragment extends Fragment {
    private TextView txtName;
    private TextView txtMatricula;
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
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        Button salvar = view.findViewById(R.id.buttonSalvarCadastro);
        Button verificarFrequencia = view.findViewById(R.id.get_usuario);
        Button entre = view.findViewById(R.id.buttonEntre);
        final ProgressBar verificarProgress = view.findViewById(R.id.verificar_progress);
        final ProgressBar cadastroProgress = view.findViewById(R.id.cadastrar_progress);
        txtName = view.findViewById(R.id.txtName);
        txtMatricula = view.findViewById(R.id.txtMatricula);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMatricula = view.findViewById(R.id.edtMatricula);
        edtSenha = view.findViewById(R.id.edtSenha);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(cadastroUsuarioViewModel.getVerificacaoMatricula().getValue() != null){

                  cadastroUsuarioViewModel.cadastrarUsuario(txtName.getText().toString(), edtMatricula.getText().toString(),
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
                                  edtEmail.setError(getContext().getResources().getString(R.string.mensagem_email_invalido));
                              }

                              @Override
                              public void onInvalidPassword(String message) {
                                  edtSenha.setError(getContext().getResources().getString(R.string.mensagem_senha_invalida));
                              }

                              @Override
                              public void onInvalidMatricula(String message) {
                                  edtMatricula.setError(getContext().getResources().getString(R.string.mensagem_matricula_invalida_tamanho));
                              }

                              @Override
                              public void onAlreadyTakenEmail() {
                                  edtEmail.setError(getContext().getResources().getString(R.string.mensagem_email_existente));
                              }

                              @Override
                              public void onAlreadyTakenMatricula() {
                                  edtMatricula.setError(getContext().getResources().getString(R.string.mensagem_matricula_existente));
                              }

                              @Override
                              public void onLoading() {
                                  cadastroProgress.setVisibility(View.VISIBLE);
                              }

                              @Override
                              public void onDone() {
                                  cadastroProgress.setVisibility(View.GONE);
                              }

                              @Override
                              public void onFailure() {
                                Toast.makeText(getContext(),"Não foi possível realizar esta operação, verifique sua conexão!",Toast.LENGTH_LONG).show();
                              }
                          });
              }else {
                  edtMatricula.requestFocus();
                  Toast.makeText(getContext(),"Verifique a matrícula antes de criar sua conta.",Toast.LENGTH_LONG).show();
              }
            }
        });
        entre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navitageToLoginFragment();
            }
        });

        cadastroUsuarioViewModel.getVerificacaoMatricula().observe(this, new Observer<VerificacaoMatricula>() {
            @Override
            public void onChanged(VerificacaoMatricula usuario) {
                if(usuario!=null) {
                    txtName.setText(usuario.getDadosValidacaoMatricula().getNome());
                    txtMatricula.setText(usuario.getDadosValidacaoMatricula().getMatricula());
                }else{
                    txtName.setText("");
                    txtMatricula.setText("");
                    edtMatricula.setText("");
                }
            }
        });
        verificarFrequencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroUsuarioViewModel.realizarValidacao(new VerificacaoMatriculaListener() {
                    @Override
                    public void onInvalidMatricula() {
                        edtMatricula.requestFocus();
                        edtMatricula.setError("A matrícula deve possuir seis dígitos.");
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"Não foi possível realizar operação, falha na requisição.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onValidMatricula() {

                    }

                    @Override
                    public void onUnregisteredMatricula() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("A matrícula fornecida não foi encontrada em nossa base de dados, por favor envie um email com sua nome completo, matrícula e curso para: n2s@ufc.br");
                        builder.setPositiveButton("Ok", null);
                        builder.show();
                    }

                    @Override
                    public void onLoading() {
                        verificarProgress.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onDone() {
                        verificarProgress.setVisibility(View.GONE);
                    }
                }, edtMatricula.getText().toString());
            }
        });
        return view;
    }

    private void showEmptyFieldMessage(String campo) {
        switch (campo) {
            case "Matricula":
                edtMatricula.setError(getContext().getResources().getString(R.string.mensagem_campo_obrigatorio));
                break;
            case "Senha":
                edtSenha.setError(getContext().getResources().getString(R.string.mensagem_campo_obrigatorio));
                break;
            case "Email":
                edtEmail.setError(getContext().getResources().getString(R.string.mensagem_campo_obrigatorio));
                break;
        }
    }

    private void navitageToLoginFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
