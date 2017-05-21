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
public class FormatedGameStats {

    private String Player1;
    private String Player2;
    private int Vitorias;
    private int Derrotas;
    private int Empate;
    private double porcentV;
    private double porcentD;
    private double porcentE;

    public FormatedGameStats(String Player1, String Player2, int Vitorias, int Derrotas, int Empate) {
        this.Player1 = Player1;
        this.Player2 = Player2;
        this.Vitorias = Vitorias;
        this.Derrotas = Derrotas;
        this.Empate = Empate;

        this.porcentV = Math.round(100 * (double) Vitorias / (Vitorias + Derrotas + Empate));
        this.porcentD = Math.round(100 * (double) Derrotas / (Vitorias + Derrotas + Empate));
        this.porcentE = Math.round(100 * (double) Empate / (Vitorias + Derrotas + Empate));
    }

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String Player1) {
        this.Player1 = Player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String Player2) {
        this.Player2 = Player2;
    }

    public int getVitorias() {
        return Vitorias;
    }

    public void setVitorias(int Vitorias) {
        this.Vitorias = Vitorias;
    }

    public int getDerrotas() {
        return Derrotas;
    }

    public void setDerrotas(int Derrotas) {
        this.Derrotas = Derrotas;
    }

    public int getEmpate() {
        return Empate;
    }

    public void setEmpate(int Empate) {
        this.Empate = Empate;
    }

    public double getPorcentV() {
        return porcentV;
    }

    public void setPorcentV(double porcentV) {
        this.porcentV = porcentV;
    }

    public double getPorcentD() {
        return porcentD;
    }

    public void setPorcentD(double porcentD) {
        this.porcentD = porcentD;
    }

    public double getPorcentE() {
        return porcentE;
    }

    public void setPorcentE(double porcentE) {
        this.porcentE = porcentE;
    }

}
