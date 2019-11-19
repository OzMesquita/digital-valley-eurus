package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.DadosFrequenciaUsuario;
import ufc.russas.encontrosuniversitarios.model.dao.repositorio.webservice.ResponseListener;
import ufc.russas.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import ufc.russas.encontrosuniversitarios.helper.ScanHelper;
import ufc.russas.encontrosuniversitarios.viewmodel.RealizarFrequenciaViewModel;

import java.util.List;

public class RealizarFrequenciaFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private RealizarFrequenciaViewModel realizarFrequenciaViewModel;
    private RecyclerView recyclerView;
    private TextView txtSala;
    private ProgressBar frequenciaProgress;
    private ProgressBar dialogoProgress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_frequencia, container, false);
        Button btnReadQRCode = view.findViewById(R.id.btnReadQRCode);
        Button btnMatricula = view.findViewById(R.id.freq_matricula);
        realizarFrequenciaViewModel = ViewModelProviders.of(this).get(RealizarFrequenciaViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_frequencia);
        txtSala = view.findViewById(R.id.sala);
        frequenciaProgress = view.findViewById(R.id.frequencia_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        btnReadQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanHelper scanHelper = new ScanHelper(0, getActivity(),getResources().getString(R.string.qrcode_titulo));
                scanHelper.showScan();
            }
        });

        btnMatricula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMatriculaDialog();
            }
        });

        realizarFrequenciaViewModel.getAtividadesFrequencia().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                if(atividades!=null && atividades.size()>=1){
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                    MySharedPreferences.getInstance(getContext()).setRoom(atividades.get(0).getLocal().getSala().getId());
                    String sala = atividades.get(0).getLocal().getSala().getNumero() == 0 ? "":""+atividades.get(0).getLocal().getSala().getNumero();
                    txtSala.setText(getContext().getResources().getText(R.string.texto_realizar_frequencia).toString()+" "+sala);
                }
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities(),false);
                recyclerView.setAdapter(programacaoDoDiaAdapter);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        realizarFrequenciaViewModel.carregarAtividadesFrequencia(getContext(), new AtividadesListener() {
            @Override
            public void onLoading() {
                recyclerView.setVisibility(View.GONE);
                frequenciaProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDone() {
                recyclerView.setVisibility(View.VISIBLE);
                frequenciaProgress.setVisibility(View.GONE);
            }
        });
    }

    AtividadesListener listener = new AtividadesListener() {
        @Override
        public void onLoading() {
            dialogoProgress.setVisibility(View.VISIBLE);
        }

        @Override
        public void onDone() {
            dialogoProgress.setVisibility(View.GONE);
        }
    };

    public void showMatriculaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View customLayout = getLayoutInflater().inflate(R.layout.matricula_dialog, null);
        dialogoProgress = customLayout.findViewById(R.id.matricula_progress);
        Button getUser = customLayout.findViewById(R.id.get_usuario);
        Button frequencia = customLayout.findViewById(R.id.realizar_frequencia);
        final TextView userName = customLayout.findViewById(R.id.nome_usuario_matricula);
        final EditText matricula = customLayout.findViewById(R.id.matricula_frequencia);
        builder.setPositiveButton(R.string.fechar,null);

        realizarFrequenciaViewModel.getUsuarioFrequencia().observe(this, new Observer<DadosFrequenciaUsuario>() {
            @Override
            public void onChanged(DadosFrequenciaUsuario usuario) {
                if(usuario!=null) {
                    userName.setText(usuario.getNome());
                }else{
                    userName.setText("");
                    matricula.setText("");
                }
            }
        });
        getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(matricula.getText().toString().length() == 6) {
                    listener.onLoading();
                    realizarFrequenciaViewModel.buscarUsuarioPorMatricula(new ResponseListener() {
                        @Override
                        public void onSuccess(Object response) {
                            listener.onDone();
                            realizarFrequenciaViewModel.initDadosFrequencia((DadosFrequenciaUsuario) response);
                        }

                        @Override
                        public void onFailure(String message) {
                            listener.onDone();
                            realizarFrequenciaViewModel.initDadosFrequencia(null);
                            matricula.setError("Não foi possível encontrar essa matrícula.");
                        }
                    },matricula.getText().toString());
                }else{
                    matricula.setError("Matrícula inválida");
                }
            }
        });
        frequencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(realizarFrequenciaViewModel.getUsuarioFrequencia().getValue() != null) {
                    listener.onLoading();
                    realizarFrequenciaViewModel.realizarCheckInCheckOut(new CheckInCheckOutListener() {
                        @Override
                        public void onSuccess(String message) {
                            listener.onDone();
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCheckedInOnDifferentRoom(String message) {
                            listener.onDone();
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onInvalidQRCode(String message) {
                            listener.onDone();
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(String message) {
                            listener.onDone();
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }
                    },getContext());
                }else{
                    matricula.requestFocus();
                    Toast.makeText(getContext(),"Verifique a matrícula antes de realizar a frequência",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setView(customLayout);
        builder.show();
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
