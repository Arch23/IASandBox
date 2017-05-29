/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.PFBenchmark;

/**
 *
 * @author Gabriel
 */
public class PFStats {
   private int NosVisitados;
   private int NosSolution;
   private long TempoDecisao;

    public int getNosVisitados() {
        return NosVisitados;
    }

    public void setNosVisitados(int NosVisitados) {
        this.NosVisitados = NosVisitados;
    }


    public void setTempoDecisao(long TempoDecisao) {
        this.TempoDecisao = TempoDecisao;
    }
   public PFStats(){
       
   }

    public PFStats(int NosVisitados, int NosSolution, long TempoDecisao) {
        this.NosVisitados = NosVisitados;
        this.NosSolution= NosSolution;
        this.TempoDecisao = TempoDecisao;
    }

    public int getNosSolution() {
        return NosSolution;
    }

    public void setNosSolution(int NosSolution) {
        this.NosSolution = NosSolution;
    }
   
}
