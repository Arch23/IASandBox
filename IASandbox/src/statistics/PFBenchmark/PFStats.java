/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.PFBenchmark;

import iasandbox.PathfindingLogic;

/**
 *
 * @author Gabriel
 */
public class PFStats {

    private int NosVisitados;
    private int NosSolution;
    private long TempoDecisao;
    private int TamMatriz;

    public int getNosVisitados() {
        return NosVisitados;
    }

    public void setNosVisitados(int NosVisitados) {
        this.NosVisitados = NosVisitados;
    }

    public void setTempoDecisao(long TempoDecisao) {
        this.TempoDecisao = TempoDecisao;
    }

    public long getTempoDecisao() {
        return TempoDecisao;
    }

    public int getNosSolution() {
        return NosSolution;
    }

    public void setNosSolution(int NosSolution) {
        this.NosSolution = NosSolution;
    }

    public int getTamMatriz() {
        return TamMatriz;
    }

    public void setTamMatriz(int TamMatriz) {
        this.TamMatriz = TamMatriz;
    }

    public PFStats() {

    }

    public PFStats(int NosVisitados, int NosSolution, long TempoDecisao) {
        this.NosVisitados = NosVisitados;
        this.NosSolution = NosSolution;
        this.TempoDecisao = TempoDecisao;
        this.TamMatriz = PathfindingLogic.getInstance().getSize();
    }

}
