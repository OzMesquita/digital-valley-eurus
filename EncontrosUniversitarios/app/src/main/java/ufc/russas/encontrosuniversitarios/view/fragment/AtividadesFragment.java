package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import ufc.russas.encontrosuniversitarios.view.adapter.ProgramacaoDoDiaAdapter;
import ufc.russas.encontrosuniversitarios.ProgramacaoListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.model.Atividade;

import java.util.ArrayList;
import java.util.List;

public class AtividadesFragment extends Fragment implements ProgramacaoListInterface {
    private RecyclerView atividadesRecyclerView;
    private ProgramacaoDoDiaAdapter programacaoDoDiaAdapter;
    private List<Atividade> atividades;

    private static final String ATIVIDADES_ARGS = "ATIVIDADES";
    private static final String ATIVIDADES_EMPTY = "EMPTY";


    public static AtividadesFragment newInstance(List<Atividade> atividades,int index) {
        AtividadesFragment fragment = new AtividadesFragment();
        Bundle args = new Bundle();
        Parcelable []atividadesParcelable = new Parcelable[atividades.size()];
        for(int i=0;i<atividades.size();i++){
            atividadesParcelable[i] = atividades.get(i);
        }
        args.putParcelableArray(ATIVIDADES_ARGS,atividadesParcelable);
        String emptyText = "";
        switch (index){
            case 0:
                emptyText = "Não existem atividades ocorrendo no momento";
                break;
            case 1:
                emptyText = "Todas as atividades de hoje já ocorreram";
                break;
            case 2:
                emptyText = "Não existem atividades finalizadas";
                break;
        }
        args.putString(ATIVIDADES_EMPTY,emptyText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            atividades = new ArrayList<>();
            Parcelable []atividadesParcelable = getArguments().getParcelableArray(ATIVIDADES_ARGS);
            for(Parcelable p:atividadesParcelable){
                atividades.add((Atividade) p);
            }
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividades, container, false);
        TextView textView = view.findViewById(R.id.empty_list);
        atividadesRecyclerView = view.findViewById(R.id.programacao_do_dia_recycler_view);
        atividadesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades,null,false);
        atividadesRecyclerView.setAdapter(programacaoDoDiaAdapter);

        if(atividades.size()== 0){
            String emptyListMessage = getArguments().getString(ATIVIDADES_EMPTY);
            textView.setText(emptyListMessage);
            textView.clearAnimation();
            textView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        programacaoDoDiaAdapter.notifyDataSetChanged();
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
        programacaoDoDiaAdapter.notifyDataSetChanged();
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        return programacaoDoDiaAdapter;
    }
}
