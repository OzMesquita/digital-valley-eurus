package com.example.encontrosuniversitarios;

import com.example.encontrosuniversitarios.model.Atividade;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class AtividadeTeste{

    private DateTime agora = DateTime.now();

    @Test
    public void atividadeComecouDepoisDoCheckInFinalizouAntesDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,agora,new DateTime(agora.getMillis()+100));
        DateTime checkIn = new DateTime(agora.getMillis()-100);
        DateTime checkOut = new DateTime(agora.getMillis()+200);
        System.out.println(checkIn.getMillis());
        System.out.println(checkOut.getMillis());
        System.out.println(atividade.getHorarioInicio().getMillis());
        System.out.println(atividade.getHorarioFinal().getMillis());
        Boolean resultado = atividade.verificarAtividadeAconteceuDentre(checkIn,checkOut);
        Assert.assertEquals(true,resultado);
    }

    @Test
    public void atividadeComecouEFinalizouAntesDoCheckIn(){
        Atividade atividade = new Atividade("Atividade", agora,agora,new DateTime(agora.getMillis()+100));
        DateTime checkIn = new DateTime(agora.getMillis()+150);
        DateTime checkOut = new DateTime(agora.getMillis()+200);

        Boolean resultado = atividade.verificarAtividadeAconteceuDentre(checkIn,checkOut);
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void atividadeComecouDepoisDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,null,null);
        DateTime checkIn = new DateTime(agora.getMillis()-300);
        DateTime checkOut = new DateTime(agora.getMillis()-200);

        Boolean resultado = atividade.verificarAtividadeAconteceuDentre(checkIn,checkOut);
        Assert.assertNull(resultado);
    }

    @Test
    public void atividadeComecouAntesDoCheckInFinalizouAntesDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,agora,new DateTime(agora.getMillis()+200));
        DateTime checkIn = new DateTime(agora.getMillis()+100);
        DateTime checkOut = new DateTime(agora.getMillis()+300);

        Boolean resultado = atividade.verificarAtividadeAconteceuDentre(checkIn,checkOut);
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void atividadeComecouDepoisDoCheckInFinalizouDepoisDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,agora,null);
        DateTime checkIn = new DateTime(agora.getMillis()-100);
        DateTime checkOut = new DateTime(agora.getMillis()+100);

        Boolean resultado = atividade.verificarAtividadeAconteceuDentre(checkIn,checkOut);
        Assert.assertNull(resultado);
    }

    @Test
    public void iniciarAtividade(){
        Atividade atividade = new Atividade("Atividade", agora,null,null);
        Boolean resultado = atividade.iniciar();
        Assert.assertEquals(true,resultado);
        Assert.assertNotNull(atividade.getHorarioInicio());
    }

    @Test
    public void finalizarAtividade(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),null);
        Boolean resultado = atividade.finalizar();
        Assert.assertEquals(true,resultado);
        Assert.assertNotNull(atividade.getHorarioFinal());
    }

    @Test
    public void iniciarAtividadeJaIniciada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),null);
        Boolean resultado = atividade.iniciar();
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void finalizarAtividadeJaFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());
        Boolean resultado = atividade.finalizar();
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void finalizarAtividadeNaoIniciada(){
        Atividade atividade = new Atividade("Atividade", agora,null,null);
        Boolean resultado = atividade.finalizar();
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void iniciarAtividadeFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());
        Boolean resultado = atividade.iniciar();
        Assert.assertEquals(false,resultado);
    }

}
