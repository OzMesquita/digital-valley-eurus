package com.example.encontrosuniversitarios;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.Frequencia;
import com.example.encontrosuniversitarios.model.Sala;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class FrequenciaTeste{

    private DateTime agora = DateTime.now();

    @Test(expected = AssertionError.class)
    public void realizarCheckIn(){
        Frequencia frequencia = new Frequencia();
        frequencia.realizarFrequenciaParticipante();

        Assert.assertNotNull(frequencia.getCheckIn());
        Assert.assertNull(frequencia.getCheckOut());
    }

    @Test(expected = AssertionError.class)
    public void realizarCheckOut(){
        Frequencia frequencia = new Frequencia();
        frequencia.realizarFrequenciaParticipante();
        frequencia.realizarFrequenciaParticipante();

        Assert.assertNotNull(frequencia.getCheckIn());
        Assert.assertNotNull(frequencia.getCheckOut());
        Assert.assertNotNull(frequencia.getAtividadesFrequentadas());
    }

    @Test(expected = AssertionError.class)
    public void usuarioFezCheckOutDepoisDeFinalizadaUmatividade(){
        Frequencia frequencia = new Frequencia();
        Sala sala = new Sala();
        frequencia.setSala(sala);
        Atividade atividade = new Atividade("Junit",null,agora,new DateTime(agora.getMillis()+900000));
        Atividade atividade2 = new Atividade("Docker",null,new DateTime(agora.getMillis()+9500000),new DateTime(agora.getMillis()+1800000));
        Atividade atividade3 = new Atividade("CC",null,new DateTime(agora.getMillis()+1850000),null);
        Atividade atividade4 = new Atividade("Software");
        sala.getAtividades().add(atividade);
        sala.getAtividades().add(atividade2);
        sala.getAtividades().add(atividade3);
        sala.getAtividades().add(atividade4);

        frequencia.realizarFrequenciaParticipante();
        frequencia.realizarFrequenciaParticipante();
        frequencia.setCheckIn(agora);
        frequencia.setCheckOut(new DateTime(agora.getMillis()+1820000));

        Assert.assertNotNull(frequencia.getAtividadesFrequentadas());
        Assert.assertEquals(2,frequencia.getAtividadesFrequentadas().size());
        Assert.assertSame(atividade,frequencia.getAtividadesFrequentadas().get(0));
        Assert.assertSame(atividade2,frequencia.getAtividadesFrequentadas().get(1));

    }

    @Test(expected = AssertionError.class)
    public void usuarioFezCheckOutAntesDeFinalizadaUmaAtividade() {
        Frequencia frequencia = new Frequencia();
        Sala sala = new Sala();
        frequencia.setSala(sala);
        Atividade atividade = new Atividade("Junit", null, agora, new DateTime(agora.getMillis() + 900000));
        Atividade atividade2 = new Atividade("Docker", null, new DateTime(agora.getMillis() + 9500000), new DateTime(agora.getMillis() + 1800000));
        Atividade atividade3 = new Atividade("CC", null, new DateTime(agora.getMillis() + 1850000), null);
        Atividade atividade4 = new Atividade("Software");
        sala.getAtividades().add(atividade);
        sala.getAtividades().add(atividade2);
        sala.getAtividades().add(atividade3);
        sala.getAtividades().add(atividade4);

        frequencia.realizarFrequenciaParticipante();
        frequencia.realizarFrequenciaParticipante();
        frequencia.setCheckIn(agora);
        frequencia.setCheckOut(new DateTime(agora.getMillis() + 1900000));

        Assert.assertNotNull(frequencia.getAtividadesFrequentadas());
        Assert.assertEquals(2, frequencia.getAtividadesFrequentadas().size());
        Assert.assertSame(atividade, frequencia.getAtividadesFrequentadas().get(0));
        Assert.assertSame(atividade2, frequencia.getAtividadesFrequentadas().get(1));

    }

    @Test(expected = AssertionError.class)
    public void usuarioFezCheckInAtividadeEmAndamentoCheckOutNoMeioDaProximaAtividade(){
        Frequencia frequencia = new Frequencia();
        Sala sala = new Sala();
        frequencia.setSala(sala);
        Atividade atividade = new Atividade("Junit", null, agora, new DateTime(agora.getMillis() + 900000));
        Atividade atividade2 = new Atividade("Docker", null, new DateTime(agora.getMillis() + 9500000), new DateTime(agora.getMillis() + 1800000));
        Atividade atividade3 = new Atividade("CC", null, new DateTime(agora.getMillis() + 1850000), null);
        Atividade atividade4 = new Atividade("Software");
        sala.getAtividades().add(atividade);
        sala.getAtividades().add(atividade2);
        sala.getAtividades().add(atividade3);
        sala.getAtividades().add(atividade4);

        frequencia.realizarFrequenciaParticipante();
        frequencia.realizarFrequenciaParticipante();
        frequencia.setCheckIn(new DateTime(agora.getMillis() + 920000));
        frequencia.setCheckOut(new DateTime(agora.getMillis() + 1200000));

        Assert.assertNotNull(frequencia.getAtividadesFrequentadas());
        Assert.assertEquals(0, frequencia.getAtividadesFrequentadas().size());
    }



}
