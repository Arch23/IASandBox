/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreMinimax;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class MMNode {
    private int map[][]= new int[3][3];
    private int utilidade;
    
    private ArrayList<MMNode> filhos;
    
    public MMNode(int[][] map){
        this.map = map;
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

    public void setMap(int[][] map) {
        this.map = map;
    }

    public ArrayList<MMNode> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<MMNode> filhos) {
        this.filhos = filhos;
    }

    public int getUtilidade() {
        return utilidade;
    }

    public void setUtilidade(int utilidade) {
        this.utilidade = utilidade;
    }
    
}
