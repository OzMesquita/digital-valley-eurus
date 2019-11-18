package ufc.russas.encontrosuniversitarios.model;

import androidx.annotation.Nullable;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class EventDay extends ExpandableGroup<Activity> {

    public EventDay(String title, List<Activity> items) {
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