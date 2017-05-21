/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Gabriel
 */
public class FormatedBenchMark {

    private String Jogador;
    private long TempoTotal;
    private long JogadasRealizadas;
    private long MediaTempoJogada;

    private long TempoTotalArvMinMax;
    private long VezesArvMinMax;
    private long MediaArvMinMax;

    public FormatedBenchMark(String Jogador, long TempoTotal, long JogadasRealizadas, long MediaTempoJogada) {
        this.Jogador = Jogador;
        this.TempoTotal = TempoTotal;
        this.JogadasRealizadas = JogadasRealizadas;
        this.MediaTempoJogada = MediaTempoJogada;
    }

    public FormatedBenchMark(String Jogador, long TempoTotalArvMinMax, long VezesArvMinMax) {
        this.Jogador = Jogador;
        this.TempoTotalArvMinMax = TempoTotalArvMinMax;
        this.VezesArvMinMax = VezesArvMinMax;
        this.MediaArvMinMax=(TempoTotalArvMinMax/VezesArvMinMax);
    }
    
    

    public long getTempoTotalArvMinMax() {
        return TempoTotalArvMinMax;
    }

    public void setTempoTotalArvMinMax(long TempoTotalArvMinMax) {
        this.TempoTotalArvMinMax = TempoTotalArvMinMax;
    }

    public long getVezesArvMinMax() {
        return VezesArvMinMax;
    }

    public void setVezesArvMinMax(long VezesArvMinMax) {
        this.VezesArvMinMax = VezesArvMinMax;
    }

    public String getJogador() {
        return Jogador;
    }

    public void setJogador(String Jogador) {
        this.Jogador = Jogador;
    }

    public long getTempoTotal() {
        return TempoTotal;
    }

    public void setTempoTotal(long TempoTotal) {
        this.TempoTotal = TempoTotal;
    }

    public long getJogadasRealizadas() {
        return JogadasRealizadas;
    }

    public void setJogadasRealizadas(long JogadasRealizadas) {
        this.JogadasRealizadas = JogadasRealizadas;
    }

    public long getMediaTempoJogada() {
        return MediaTempoJogada;
    }

    public void setMediaTempoJogada(long MediaTempoJogada) {
        this.MediaTempoJogada = MediaTempoJogada;
    }

    public long getMediaArvMinMax() {
        return MediaArvMinMax;
    }

    public void setMediaArvMinMax(long MediaArvMinMax) {
        this.MediaArvMinMax = MediaArvMinMax;
    }
    
}
