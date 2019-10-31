package ufc.russas.encontrosuniversitarios;

import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.DiaEvento;
import ufc.russas.encontrosuniversitarios.model.ProgramacaoAtividades;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoAtividadesTeste {

    private List<Atividade> gerarAtividades(){

        List<Atividade> atividades = new ArrayList<>();
        atividades.add(new Atividade("Java", new DateTime(2019,6,12,0,0)));

        atividades.add(new Atividade("JUnit", new DateTime(2019,6,11,0,0)));
        atividades.add(new Atividade("Mockito", new DateTime(2019,6,11,0,0)));
        atividades.add(new Atividade("PHPUnit", new DateTime(2019,6,11,0,0)));
        atividades.add(new Atividade("Vue", new DateTime(2019,6,13,0,0)));

        atividades.add(new Atividade("PHP", new DateTime(2019,6,12,0,0)));

        atividades.add(new Atividade("Jenkins", new DateTime(2019,6,11,0,0)));
        atividades.add(new Atividade("TDD", new DateTime(2019,6,11,0,0)));

        atividades.add(new Atividade("React", new DateTime(2019,6,13,0,0)));
        atividades.add(new Atividade("Angular", new DateTime(2019,6,13,0,0)));
        return atividades;
    }

    private List<Atividade> gerarAtividadesDoDia(){
        List<Atividade> atividades = new ArrayList<>();
        atividades.add(new Atividade("Java", DateTime.now(), DateTime.now(), null));

        atividades.add(new Atividade("JUnit", DateTime.now(), DateTime.now(), null));
        atividades.add(new Atividade("Mockito", DateTime.now(), DateTime.now(), null));
        atividades.add(new Atividade("PHPUnit", DateTime.now(), DateTime.now(), null));
        atividades.add(new Atividade("Vue", DateTime.now()));

        atividades.add(new Atividade("PHP", DateTime.now()));

        atividades.add(new Atividade("Jenkins", DateTime.now()));
        atividades.add(new Atividade("TDD", DateTime.now()));

        atividades.add(new Atividade("React", DateTime.now(), DateTime.now(), DateTime.now()));
        atividades.add(new Atividade("Angular", DateTime.now(), DateTime.now(), DateTime.now()));
        return atividades;
    }

    @Test
    public void agruparAtividadesPeloEstado(){
        ProgramacaoAtividades programacaoAtividades = new ProgramacaoAtividades();
        List<List<Atividade>> listaEstadosAtividades = programacaoAtividades.dividirAtividadesEmEstados(gerarAtividadesDoDia());

        Assert.assertEquals(4,listaEstadosAtividades.get(0).size());
        Assert.assertEquals(4,listaEstadosAtividades.get(1).size());
        Assert.assertEquals(2,listaEstadosAtividades.get(2).size());

        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Atividade("Java")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Atividade("JUnit")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Atividade("Mockito")));
        Assert.assertEquals(true,listaEstadosAtividades.get(0).contains(new Atividade("PHPUnit")));

        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Atividade("Vue")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Atividade("PHP")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Atividade("Jenkins")));
        Assert.assertEquals(true,listaEstadosAtividades.get(1).contains(new Atividade("TDD")));

        Assert.assertEquals(true,listaEstadosAtividades.get(2).contains(new Atividade("React")));
        Assert.assertEquals(true,listaEstadosAtividades.get(2).contains(new Atividade("Angular")));
    }

    @Test
    public void agruparAtividadesEmDias(){
        ProgramacaoAtividades programacaoAtividades = new ProgramacaoAtividades();
        List<Atividade> atividades = gerarAtividades();
        List<DiaEvento> diasEvento = programacaoAtividades.agruparAtividadesEmDias(atividades);

        Assert.assertEquals(3,diasEvento.size());
        Assert.assertEquals(2,diasEvento.get(0).getItemCount());
        Assert.assertEquals(5,diasEvento.get(1).getItemCount());
        Assert.assertEquals(3,diasEvento.get(2).getItemCount());
    }

    @Test
    public void testarAtividadesDistribuidasEmCadaDia(){
        ProgramacaoAtividades programacaoAtividades = new ProgramacaoAtividades();
        List<Atividade> atividades = gerarAtividades();
        List<DiaEvento> diasEvento = programacaoAtividades.agruparAtividadesEmDias(atividades);

        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("JUnit")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("PHPUnit")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("Mockito")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("Jenkins")));
        Assert.assertEquals(true,diasEvento.get(1).getItems().contains(new Atividade("TDD")));

        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("Java")));
        Assert.assertEquals(true,diasEvento.get(0).getItems().contains(new Atividade("PHP")));

        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("Vue")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("React")));
        Assert.assertEquals(true,diasEvento.get(2).getItems().contains(new Atividade("Angular")));

    }
}
