package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ProgressBar;
import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.viewmodel.AvaliacaoAtividadeViewModel;
import java.util.List;


public class AtividadesProfessorFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private AvaliacaoAtividadeViewModel avaliacaoAtividadeViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades_professor, container, false);
        recyclerView = view.findViewById(R.id.atividades_professor);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        avaliacaoAtividadeViewModel = ViewModelProviders.of(this).get(AvaliacaoAtividadeViewModel.class);
        progressBar = view.findViewById(R.id.professor_progress);
        avaliacaoAtividadeViewModel.getAtividadesAvaliação().observe(this, new Observer<List<Atividade>>() {
            @Override
            public void onChanged(List<Atividade> atividades) {
                if (atividades != null && atividades.size() >= 1) {
                    MySharedPreferences.getInstance(getContext()).setCoordinatorActivities(atividades);
                    MySharedPreferences.getInstance(getContext()).setRoom(atividades.get(0).getLocal().getSala().getId());
                }
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, MySharedPreferences.getInstance(getContext()).getCoordinatorActivities(), true);
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
        avaliacaoAtividadeViewModel.carregarAtividades(getContext(), new AtividadesListener() {
            @Override
            public void onLoading() {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            @Override
            public void onDone() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}


