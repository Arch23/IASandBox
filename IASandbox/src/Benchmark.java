/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class Benchmark {

    private static Benchmark instance = null;
    private long tempCalcArv;
    private long tempoAchaDecisoes;
    private long tempoTomadaDecisaoTot;

    public static Benchmark getInstance() {
        return (instance == null ? instance = new Benchmark() : instance);
    }
    private Benchmark(){};

    public long getTempCalcArv() {
        return tempCalcArv;
    }

    public long getTempoAchaDecisoes() {
        return tempoAchaDecisoes;
    }

    public long getTempoTomadaDecisaoTot() {
        return tempoTomadaDecisaoTot;
    }
    
    
}
