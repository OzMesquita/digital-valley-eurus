package ufc.russas.encontrosuniversitarios;

import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.model.EventDay;
import ufc.russas.encontrosuniversitarios.model.ScheduleActivities;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivitiesTeste {

    private List<Activity> gerarAtividades(){

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Java", new DateTime(2019,6,12,0,0)));

        activities.add(new Activity("JUnit", new DateTime(2019,6,11,0,0)));
        activities.add(new Activity("Mockito", new DateTime(2019,6,11,0,0)));
        activities.add(new Activity("PHPUnit", new DateTime(2019,6,11,0,0)));
        activities.add(new Activity("Vue", new DateTime(2019,6,13,0,0)));

        activities.add(new Activity("PHP", new DateTime(2019,6,12,0,0)));

        activities.add(new Activity("Jenkins", new DateTime(2019,6,11,0,0)));
        activities.add(new Activity("TDD", new DateTime(2019,6,11,0,0)));

        activities.add(new Activity("React", new DateTime(2019,6,13,0,0)));
        activities.add(new Activity("Angular", new DateTime(2019,6,13,0,0)));
        return activities;
    }

    private List<Activity> gerarAtividadesDoDia(){
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Java", DateTime.now(), DateTime.now(), null));

        activities.add(new Activity("JUnit", DateTime.now(), DateTime.now(), null));
        activities.add(new Activity("Mockito", DateTime.now(), DateTime.now(), null));
        activities.add(new Activity("PHPUnit", DateTime.now(), DateTime.now(), null));
        activities.add(new Activity("Vue", DateTime.now()));

        activities.add(new Activity("PHP", DateTime.now()));

        activities.add(new Activity("Jenkins", DateTime.now()));
        activities.add(new Activity("TDD", DateTime.now()));

        activities.add(new Activity("React", DateTime.now(), DateTime.now(), DateTime.now()));
        activities.add(new Activity("Angular", DateTime.now(), DateTime.now(), DateTime.now()));
        return activities;
    }

    @Test
    public void agruparAtividadesPeloEstado(){
        ScheduleActivities scheduleActivities = new ScheduleActivities();
        List<List<Activity>> listaEstadosAtividades = scheduleActivities.dividirAtividadesEmEstados(gerarAtividadesDoDia());

        Assert.assertEquals(4,listaEstadosAtividades.get(0).size());
        Assert.assertEquals(4,listaEstadosAtividades.get(1).size());
        Assert.assertEquals(2,listaEstadosAtividades.get(2).size());

        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Activity("Java")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Activity("JUnit")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Activity("Mockito")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Activity("PHPUnit")));

        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Activity("Vue")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Activity("PHP")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Activity("Jenkins")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Activity("TDD")));

        Assert.assertEquals(true,listaEstadosAtividades.get(2).contains(new Activity("React")));
        Assert.assertEquals(true,listaEstadosAtividades.get(2).contains(new Activity("Angular")));
    }

    @Test
    public void agruparAtividadesEmDias(){
        ScheduleActivities scheduleActivities = new ScheduleActivities();
        List<Activity> activities = gerarAtividades();
        List<EventDay> diasEvento = scheduleActivities.agruparAtividadesEmDias(activities);

        Assert.assertEquals(3,diasEvento.size());
        Assert.assertEquals(2,diasEvento.get(0).getItemCount());
        Assert.assertEquals(5,diasEvento.get(1).getItemCount());
        Assert.assertEquals(3,diasEvento.get(2).getItemCount());
    }

    @Test
    public void testarAtividadesDistribuidasEmCadaDia(){
        ScheduleActivities scheduleActivities = new ScheduleActivities();
        List<Activity> activities = gerarAtividades();
        List<EventDay> diasEvento = scheduleActivities.agruparAtividadesEmDias(activities);

        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Activity("JUnit")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Activity("PHPUnit")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Activity("Mockito")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Activity("Jenkins")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Activity("TDD")));

        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Activity("Java")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Activity("PHP")));

        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Activity("Vue")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Activity("React")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Activity("Angular")));

    }
}
