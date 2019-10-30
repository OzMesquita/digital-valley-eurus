package com.example.encontrosuniversitarios.view.fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.CriterioAtividade;
import com.example.encontrosuniversitarios.view.adapter.AvaliacaoAtividadeAdapter;
import com.example.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;
import java.util.List;

public class AvaliacaoAtividadeFragment extends Fragment {
    private final static String ATIVIDADE = "atividade";
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private List<CriterioAtividade> criterios;
    private TextView txtMedia;

    public AvaliacaoAtividadeFragment() {

    }

    public static AvaliacaoAtividadeFragment newInstance(Atividade atividade) {
        AvaliacaoAtividadeFragment fragment = new AvaliacaoAtividadeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ATIVIDADE,atividade);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        if(getArguments() != null){
            Atividade atividade = getArguments().getParcelable(ATIVIDADE);
            avaliacaoAtividadeViewModel.init(atividade);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_avaliacao_atividade, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Avaliação");
        TextView txtActivityName = view.findViewById(R.id.activity_name);
        final ProgressBar progressBar = view.findViewById(R.id.progress_avaliacao);
        txtMedia = view.findViewById(R.id.media);
        Button confirmarAvaliacao = view.findViewById(R.id.confirmar_avaliacao);
        final EditText comentarios = view.findViewById(R.id.comentarios);
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        txtActivityName.setText(avaliacaoAtividadeViewModel.getAtividade().getNome());
        final RecyclerView criteriosRec = view.findViewById(R.id.lista_criterios);
        criteriosRec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        avaliacaoAtividadeViewModel.getCriterios().observe(this, new Observer<List<CriterioAtividade>>() {
            @Override
            public void onChanged(List<CriterioAtividade> criterioAtividades) {
                if(criterioAtividades!=null){
                    criterios = criterioAtividades;
                    AvaliacaoAtividadeAdapter adapter = new AvaliacaoAtividadeAdapter(criterios,criterioListener);
                    criteriosRec.setAdapter(adapter);
                    criterioListener.onNotaChanged();
                }
            }
        });
        confirmarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avaliacaoAtividadeViewModel.avaliarAtividade(comentarios.getText().toString(),
                        getContext(), null);
            }
        });
        avaliacaoAtividadeViewModel.listarCriterios();

        return view;
    }

    private CriterioListener criterioListener = new CriterioListener() {
        @Override
        public void onNotaChanged() {
            double total = 0;
            for(CriterioAtividade criterioAtividade:criterios){
                total += criterioAtividade.getNota();
            }
            txtMedia.setText("Media: "+total/criterios.size());
        }
    };


}


