package com.example.encontrosuniversitarios;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.DiaEvento;
import com.example.encontrosuniversitarios.model.DiaEventoAtividade;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoViewModelTeste {

    private List<Atividade> gerarAtividades(){
        List<Atividade> atividades = new ArrayList<>();
        atividades.add(new Atividade("Java", new DateTime(2019,6,12,0,0), null, null));

        atividades.add(new Atividade("JUnit", new DateTime(2019,6,11,0,0), null, null));
        atividades.add(new Atividade("Mockito", new DateTime(2019,6,11,0,0), null, null));
        atividades.add(new Atividade("PHPUnit", new DateTime(2019,6,11,0,0), null, null));
        atividades.add(new Atividade("Vue", new DateTime(2019,6,13,0,0), null, null));

        atividades.add(new Atividade("PHP", new DateTime(2019,6,12,0,0), null, null));

        atividades.add(new Atividade("Jenkis", new DateTime(2019,6,11,0,0), null, null));
        atividades.add(new Atividade("TDD", new DateTime(2019,6,11,0,0), null, null));

        atividades.add(new Atividade("React", new DateTime(2019,6,13,0,0), null, null));
        atividades.add(new Atividade("Angular", new DateTime(2019,6,13,0,0), null, null));
        return atividades;
    }

    @Test
    public void agruparAtividadesEmDias(){
        DiaEventoAtividade diaEventoAtividade = new DiaEventoAtividade();
        List<Atividade> atividades = gerarAtividades();
        List<DiaEvento> diasEvento = diaEventoAtividade.agruparAtividadesEmDias(atividades);

        Assert.assertEquals(3,diasEvento.size());
        Assert.assertEquals(3,diasEvento.get(0).getItemCount());
        Assert.assertEquals(2,diasEvento.get(1).getItemCount());
        Assert.assertEquals(3,diasEvento.get(2).getItemCount());
    }

    @Test
    public void testarAtividadesDistribuidasEmCadaDia(){
        DiaEventoAtividade diaEventoAtividade = new DiaEventoAtividade();
        List<Atividade> atividades = gerarAtividades();
        List<DiaEvento> diasEvento = diaEventoAtividade.agruparAtividadesEmDias(atividades);

        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("JUnit")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("PHPUnit")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("Mockito")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("Jenkins")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("TDD")));

        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("Java")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("PHP")));

        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("Vue")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("React")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("Angular")));

    }
}
