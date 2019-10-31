package ufc.russas.encontrosuniversitarios;

import ufc.russas.encontrosuniversitarios.model.Atividade;
import ufc.russas.encontrosuniversitarios.model.exceptions.AtividadeFinalizadaAntesDoHorarioIniciadoException;

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
        Boolean resultado = atividade.verificarAtividadeOcorridaDentre(checkIn,checkOut);
        Assert.assertEquals(true,resultado);
    }

    @Test
    public void atividadeComecouEFinalizouAntesDoCheckIn(){
        Atividade atividade = new Atividade("Atividade", agora,agora,new DateTime(agora.getMillis()+100));
        DateTime checkIn = new DateTime(agora.getMillis()+150);
        DateTime checkOut = new DateTime(agora.getMillis()+200);

        Boolean resultado = atividade.verificarAtividadeOcorridaDentre(checkIn,checkOut);
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void atividadeComecouDepoisDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora);
        DateTime checkIn = new DateTime(agora.getMillis()-300);
        DateTime checkOut = new DateTime(agora.getMillis()-200);

        Boolean resultado = atividade.verificarAtividadeOcorridaDentre(checkIn,checkOut);
        Assert.assertNull(resultado);
    }

    @Test
    public void atividadeComecouAntesDoCheckInFinalizouAntesDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,agora,new DateTime(agora.getMillis()+200));
        DateTime checkIn = new DateTime(agora.getMillis()+100);
        DateTime checkOut = new DateTime(agora.getMillis()+300);

        Boolean resultado = atividade.verificarAtividadeOcorridaDentre(checkIn,checkOut);
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void atividadeComecouDepoisDoCheckInFinalizouDepoisDoCheckOut(){
        Atividade atividade = new Atividade("Atividade", agora,agora,null);
        DateTime checkIn = new DateTime(agora.getMillis()-100);
        DateTime checkOut = new DateTime(agora.getMillis()+100);

        Boolean resultado = atividade.verificarAtividadeOcorridaDentre(checkIn,checkOut);
        Assert.assertNull(resultado);
    }

    @Test
    public void iniciarAtividade(){
        Atividade atividade = new Atividade("Atividade",agora);
        Boolean resultado = atividade.iniciar();
        Assert.assertEquals(true,resultado);
        Assert.assertNotNull(atividade.getHorarioInicio());
    }

    @Test
    public void finalizarAtividade() throws AtividadeFinalizadaAntesDoHorarioIniciadoException{
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
    public void finalizarAtividadeJaFinalizada() throws AtividadeFinalizadaAntesDoHorarioIniciadoException{
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());
        Boolean resultado = atividade.finalizar();
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void finalizarAtividadeNaoIniciada() throws AtividadeFinalizadaAntesDoHorarioIniciadoException{
        Atividade atividade = new Atividade("Atividade", agora);
        Boolean resultado = atividade.finalizar();
        Assert.assertEquals(false,resultado);
    }

    @Test
    public void iniciarAtividadeFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());
        Boolean resultado = atividade.iniciar();
        Assert.assertEquals(false,resultado);
    }

    @Test(expected =  AtividadeFinalizadaAntesDoHorarioIniciadoException.class)
    public void finalizarAtividadeAntesDoTempoDoHorarioIniciado() throws AtividadeFinalizadaAntesDoHorarioIniciadoException{
        long minuto = 6000;
        DateTime horarioInicio = new DateTime(DateTime.now().getMillis()+minuto);
        Atividade atividade = new Atividade("Atividade", agora,horarioInicio,null);
        atividade.finalizar();
        Assert.assertNull(atividade.getHorarioFinal());
    }

    @Test
    public void verificarAtividadeIniciada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),null);

        Assert.assertEquals(true,atividade.atividadeIniciada());
    }

    @Test
    public void verificarAtividadeIniciadaEmAtividadeFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());

        Assert.assertEquals(false,atividade.atividadeIniciada());
    }

    @Test
    public void verificarAtividadeNaoIniciada(){
        Atividade atividade = new Atividade("Atividade", agora);

        Assert.assertEquals(false,atividade.atividadeIniciada());
    }

    @Test
    public void verificarAtividadeFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),DateTime.now());

        Assert.assertEquals(true,atividade.atividadeFinalizada());
    }

    @Test
    public void verificarAtividadeFinalizadaEmAtividadeNaoIniciada(){
        Atividade atividade = new Atividade("Atividade", agora);

        Assert.assertEquals(false,atividade.atividadeFinalizada());
    }

    @Test
    public void verificarAtividadeNaoFinalizada(){
        Atividade atividade = new Atividade("Atividade", agora,DateTime.now(),null);

        Assert.assertEquals(false,atividade.atividadeFinalizada());
    }

}
