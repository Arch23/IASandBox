/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlfaBeta;

import IOStream.Writer;
import iasandbox.TicTacToe;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class TreeAlfaBeta {

    private static TreeAlfaBeta instance;
    
    private ABNode arvore;
    private int jogadormax, contador, height,contado=0;
    
    public static TreeAlfaBeta getInstance(){return((instance==null)?(instance=new TreeAlfaBeta()):instance);}
    
    private TreeAlfaBeta(){
        startAlg();
    }
    
    public void geraArvore(ABNode node, int h) {
        //verifica se o jogo não acabou
        if(TicTacToe.getInstance().checkBoard(node.getMap(), h)==-1){
            ArrayList<ABNode> filhos = new ArrayList<>();
            ArrayList<Position> zeros = getZerosPos(node);
            Iterator<Position> it = zeros.iterator();
            int indice=0;
            while(it.hasNext()){
                Position pos = it.next();
                int[][] map = node.getMap();
                map[pos.getX()][pos.y] =(((h%2)==0)?(1):(2));
                filhos.add(new ABNode(map,node.getAlfa(),node.getBeta()));
                
                geraArvore(filhos.get(indice),(h+1));
                //verificar se é max ou min
                if((h%2)==0){
                    //max
                    if(filhos.get(indice).getUtilidade()>node.getAlfa()){
                        node.setAlfa(filhos.get(indice).getUtilidade());
                    }
                }else{
                    //min
                    if(filhos.get(indice).getUtilidade()<node.getBeta()){
                        node.setBeta(filhos.get(indice).getUtilidade());
                    }
                }
                indice++;
            }
            
        }else{
            
        }//nó terminal por que o jogo acabou
    }

    public int calcUtFather(ABNode node, int h) {
        int aux;
        if (h % 2 == 0) {
            aux = Integer.MIN_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() > aux) {
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        } else {
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
        ArrayList<Position> freeposition = new ArrayList<>();
        for (int i = 0; i < no.getMap().length; i++) {
            for (int j = 0; j < no.getMap()[i].length; j++) {
                if (no.getMap()[i][j] == 0) {
                    freeposition.add(new Position(i, j));
                }
            }
        }
        return freeposition;
    }

    private ArrayList<ABNode> geraFilhos(ABNode no, int h) {
    }

    private int calcUtLeaf(int[][] map, int h) {
        int res = TicTacToe.getInstance().checkBoard(map, h);
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
//                    else{
//                        return(aux);
//                    }
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
    public void printTree(int[][] map){
        System.out.println("");
        for(int i=0;i<3;i++){
            System.out.println("");
            for(int j=0;j<3;j++){
                System.out.print(map[i][j]+" ");
            }
        }
    }
    
    public static void main(String args[]){
        TreeAlfaBeta obj = new TreeAlfaBeta();
        obj.start();
    }
    
    
    public void start(){
//        Writer.getInstance().initWriter("AlfaBeta.txt");
        contador=0;
        startAlg();
        System.out.println("Nós gerados: "+contador);
//        Writer.getInstance().closeWriter();
    }
}
