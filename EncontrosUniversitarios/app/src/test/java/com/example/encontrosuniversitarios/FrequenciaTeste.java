package com.example.encontrosuniversitarios;

import com.example.encontrosuniversitarios.model.Frequencia;

import org.junit.Assert;
import org.junit.Test;

public class FrequenciaTeste{

    @Test
    public void realizarCheckIn(){
        Frequencia frequencia = new Frequencia();
        frequencia.realizarFrequenciaParticipante();
        Assert.assertNotNull(frequencia.getCheckIn());
    }

    @Test
    public void realizarCheckOut(){
        Frequencia frequencia = new Frequencia();
        frequencia.realizarFrequenciaParticipante();
        frequencia.realizarFrequenciaParticipante();

        Assert.assertNotNull(frequencia.getCheckIn());
        Assert.assertNotNull(frequencia.getCheckOut());
        Assert.assertNotNull(frequencia.getAtividadesFrequentadas());
    }


}
