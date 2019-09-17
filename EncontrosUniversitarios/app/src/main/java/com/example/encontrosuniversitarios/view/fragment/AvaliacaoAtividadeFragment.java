package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.encontrosuniversitarios.R;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoAtividadeFragment extends Fragment {
    private List<NumberPicker> ns;
    private float soma;
    public AvaliacaoAtividadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avaliacao_atividade, container, false);
        ns = new ArrayList<>();
        NumberPicker n = view.findViewById(R.id.nota1);
        n.setMinValue(1);
        n.setMaxValue(10);
        NumberPicker n2 = view.findViewById(R.id.nota2);
        n2.setMinValue(1);
        n2.setMaxValue(10);
        NumberPicker n3 = view.findViewById(R.id.nota3);
        n3.setMinValue(1);
        n3.setMaxValue(10);
        NumberPicker n4 = view.findViewById(R.id.nota4);
        n4.setMinValue(1);
        n4.setMaxValue(10);
        ns.add(n);
        ns.add(n2);
        ns.add(n3);
        ns.add(n4);
        final TextView t = view.findViewById(R.id.media);
        for(int i=0;i<ns.size();i++){
            ns.get(i).setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    float soma = 0;
                    for(NumberPicker nb:ns){
                        soma+=nb.getValue();
                    }
                    t.setText("Media: "+soma/ns.size());
                }
            });
        }
        return view;
    }
}
