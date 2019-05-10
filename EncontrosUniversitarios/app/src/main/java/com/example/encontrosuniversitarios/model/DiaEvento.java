package com.example.encontrosuniversitarios.model;

import com.example.encontrosuniversitarios.model.Atividade;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DiaEvento extends ExpandableGroup<Atividade> {

    public DiaEvento(String title, List<Atividade> items) {
        super(title, items);
    }
}
