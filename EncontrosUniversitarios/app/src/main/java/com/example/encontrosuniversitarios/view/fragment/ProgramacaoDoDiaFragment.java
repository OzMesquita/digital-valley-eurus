package com.example.encontrosuniversitarios.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.example.encontrosuniversitarios.ProgramacaoAbasAdapter;
import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.google.android.material.tabs.TabLayout;


public class ProgramacaoDoDiaFragment extends Fragment implements ProgramacaoListInterface {
    private ProgramacaoAbasAdapter programacaoAbasAdapter;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programacao_do_dia,container,false);
        programacaoAbasAdapter = new ProgramacaoAbasAdapter(getFragmentManager());

        programacaoAbasAdapter.addFragment(new AtividadesFragment(),"Iniciadas");
        programacaoAbasAdapter.addFragment(new AtividadesFragment(),"Próximas");
        programacaoAbasAdapter.addFragment(new AtividadesFragment(),"Finalizadas");


        ViewPager viewPager = view.findViewById(R.id.programacao_do_dia_view_pager);
        viewPager.setAdapter(programacaoAbasAdapter);

        tabLayout = view.findViewById(R.id.programacao_do_dia_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public Filterable getProgramacaoAdapter() {
        AtividadesFragment programacaoFragment = (AtividadesFragment) this.programacaoAbasAdapter.getItem(tabLayout.getSelectedTabPosition());
        return programacaoFragment.getProgramacaoAdapter();
    }
}
