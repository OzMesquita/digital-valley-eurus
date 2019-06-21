package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AtividadeDadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AtividadeDadosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "nomeAtividade";

    // TODO: Rename and change types of parameters
    private String nomeAtividade;

    public AtividadeDadosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AtividadeDadosFragment newInstance(String nomeAtividade) {
        AtividadeDadosFragment fragment = new AtividadeDadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nomeAtividade);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nomeAtividade = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividade_dados, container, false);
        TextView nomeAtv = view.findViewById(R.id.nome_atividade);
        nomeAtv.setText(this.nomeAtividade);
        return view;
    }

}
