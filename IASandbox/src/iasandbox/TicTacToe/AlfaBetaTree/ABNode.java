/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToe.AlfaBetaTree;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class ABNode {
    private int map[][]= new int[3][3];
    private int utilidade;
    private int alfa,beta;
    
    
    private ArrayList<ABNode> filhos;
    
    public ABNode(int[][] map,int a,int b){
        this.map = map;
        alfa=a;
        beta=b;
        filhos = new ArrayList<>();
    }
    
    public int[][] getMap() {
        int[][] tempMap = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tempMap[i][j]=map[i][j];
            }
        }
        return tempMap;
    }

    public int getAlfa() {
        return alfa;
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public ArrayList<ABNode> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<ABNode> filhos) {
        this.filhos = filhos;
    }

    public int getUtilidade() {
        return utilidade;
    }

    public void setUtilidade(int utilidade) {
        this.utilidade = utilidade;
    }
}
