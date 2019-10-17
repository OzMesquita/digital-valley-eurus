package com.example.encontrosuniversitarios.view.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ProgramacaoAbasAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private List<String> titulos;
    public ProgramacaoAbasAdapter(FragmentManager fm){
        super(fm);
        fragments = new ArrayList<>();
        titulos = new ArrayList<>();
    }

    public void addFragment(Fragment fragment,String titulo){
        this.fragments.add(fragment);
        this.titulos.add(titulo);
    }


    public void add(Fragment fragment, int position){
        fragments.add(position,fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos.get(position);
    }
}
