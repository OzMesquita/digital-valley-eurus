package ufc.russas.encontrosuniversitarios.model;

import androidx.annotation.Nullable;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DiaEvento extends ExpandableGroup<Atividade> {

    public DiaEvento(String title, List<Atividade> items) {
        super(title, items);
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.toString().equals(obj.toString());
    }
}