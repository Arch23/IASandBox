/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToe.AlfaBetaTree;

import statistics.IOStream.Writer;
import iasandbox.TicTacToeLogic;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class TreeAlfaBeta {

    private static TreeAlfaBeta instance;
    
    private ABNode arvore;
    private int jogadormax, height;
    
    public static TreeAlfaBeta getInstance(){return((instance==null)?(instance=new TreeAlfaBeta()):instance);}
    
    private TreeAlfaBeta(){
        startAlg();
    }
    
    public ABNode genNewTree(int[][] map){
        ABNode tmp = new ABNode(map,Integer.MIN_VALUE,Integer.MAX_VALUE);
        int height = (9-(getZerosPos(tmp).size()));
        geraArvore(tmp, height);
        return(tmp);
    }
    
    public void geraArvore(ABNode node, int h) {
        //verifica se o jogo não acabou
        if(TicTacToeLogic.getInstance().checkBoard(node.getMap(), h)==-1){
            ArrayList<ABNode> filhos = new ArrayList<>();
            node.setFilhos(filhos);
            ArrayList<Position> zeros = getZerosPos(node);
            Iterator<Position> it = zeros.iterator();
            int indice=0;
            while(it.hasNext()){
                Position pos = it.next();
                int[][] map = node.getMap();
                map[pos.getX()][pos.getY()] =(((h%2)==0)?(1):(2));
                filhos.add(new ABNode(map,node.getAlfa(),node.getBeta()));
                geraArvore(filhos.get(indice),(h+1));
                //verificar se é max ou min
                if((h%2)==0){
                    //max
                    if(filhos.get(indice).getUtilidade()>=node.getAlfa()){
                        node.setAlfa(filhos.get(indice).getUtilidade());
                    }
                }else{
                    //min
                    if(filhos.get(indice).getUtilidade()<=node.getBeta()){
                        node.setBeta(filhos.get(indice).getUtilidade());
                    }
                }
                if(node.getAlfa()>=node.getBeta()){
                    break;
                }
                indice++;
            }
            node.setUtilidade(calcUtFather(node, h));
        }else{//nó terminal por que o jogo acabou
            node.setUtilidade(calcUtLeaf(node.getMap(), h));
        }
    }

    public int calcUtFather(ABNode node, int h) {
        int aux;
        //max
        if (h % 2 == 0) {
            aux = Integer.MIN_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() > aux) {
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        } else {//min
            aux = Integer.MAX_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() < aux) {
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        }
        return(aux);
    }

    public void startAlg() {
        setRoot();
        geraArvore(arvore, height);
    }

    private void setRoot() {
        int[][] map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        arvore = new ABNode(map,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public ArrayList<Position> getZerosPos(ABNode no) {
        ArrayList<Position> freePosition = new ArrayList<>();
        for (int i = 0; i < no.getMap().length; i++) {
            for (int j = 0; j < no.getMap()[i].length; j++) {
                if (no.getMap()[i][j] == 0) {
                    freePosition.add(new Position(i, j));
                }
            }
        }
        
        return freePosition;
    }

    private int calcUtLeaf(int[][] map, int h) {
        int res = TicTacToeLogic.getInstance().checkBoard(map, h);
        switch (res) {
            case (1):
                return (1);
            case (2):
                return (-1);
            default:
                return (0);
        }
    }
    
    public boolean compMap(int[][] map1,int[][] map2){
        for(int i=0;i<map1.length;i++){
            for(int j=0;j<map1[i].length;j++){
                if(map1[i][j]!=map2[i][j]){
                    return(false);
                }
            }
        }
        return(true);
    }
    
    public boolean containsMap(int[][] mapOut,int[][] mapInner){
        for(int i=0;i<mapOut.length;i++){
            for(int j=0;j<mapOut[i].length;j++){
                if((mapOut[i][j]==1 && mapInner[i][j]==2) || (mapOut[i][j]==2 && mapInner[i][j]==1)){
                    return(false);
                }
            }
        }
        return(true);
    }
    
    public ABNode getNode(ABNode node,int[][] map){
        if(compMap(map,node.getMap())){
            return(node);
        }else{
            ABNode aux =null;
            Iterator<ABNode> it = node.getFilhos().iterator();
            while(it.hasNext()){
                ABNode n = it.next();
                if(containsMap(n.getMap(),map)){
                    aux = getNode(n, map);
                    if(aux!=null){
                        return(aux);
                    }
                }
            }
            return(aux);
        }
    }

    public int getJogadorMax() {
        return jogadormax;
    }

    public void setJogadorMax(int jogador) {
        this.jogadormax = jogador;
    }

    public ABNode getArvore() {
        return arvore;
    }
    
    //Para debug APAGAR DEPOIS    
//    public void printTree(int[][] map){
//        System.out.println("");
//        for(int i=0;i<3;i++){
//            System.out.println("");
//            for(int j=0;j<3;j++){
//                System.out.print(map[i][j]+" ");
//            }
//        }
//    }
}
