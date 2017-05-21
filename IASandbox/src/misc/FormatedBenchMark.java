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

    public FormatedBenchMark(String Jogador, long TempoTotal, long JogadasRealizadas, long MediaTempoJogada) {
        this.Jogador = Jogador;
        this.TempoTotal = TempoTotal;
        this.JogadasRealizadas = JogadasRealizadas;
        this.MediaTempoJogada = MediaTempoJogada;
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

}
