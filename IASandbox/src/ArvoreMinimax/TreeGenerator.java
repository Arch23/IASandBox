/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreMinimax;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class TreeGenerator {

    private Node arvore;
    private int jogadormax,contador,height;

    public void geraArvore(Node node,int h) {
        node.setFilhos(geraFilhosMax(node,h));
        Iterator<Node> it = node.getFilhos().iterator();
        contador++;
        while(it.hasNext()){
            int fH = h+1;
            geraArvore(it.next(),fH);
        }
    }
    
    public void startAlg(){
        setRoot();
        geraArvore(arvore,height);
    }

    private void setRoot(){
        int[][] map = {{0,0,0},{0,0,0},{0,0,0}};
        arvore = new Node(map);
    }
    
    public ArrayList<Position> getZerosPos(Node no) {
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

    public ArrayList<Node> geraFilhosMax(Node no,int h) {
        Node filho;
        ArrayList<Position> zeros = getZerosPos(no);
        ArrayList<Node> filhos = new ArrayList<>();
        for (int i = 0; i < zeros.size(); i++) {
            int map[][] = no.getMap();
            map[zeros.get(i).getX()][zeros.get(i).getY()] = (((h%2)==0)?1:2);
            filho = new Node(map);
            filhos.add(filho);
        }
        return filhos;
    }

    public void insereNode(Node no) {

    }

    public int getJogadorMax() {
        return jogadormax;
    }

    public void setJogadorMax(int jogador) {
        this.jogadormax = jogador;
    }

    public static void main(String[] args) {
        TreeGenerator obj = new TreeGenerator();
        obj.start();
    }
    
    public void start(){
        height=0;
        startAlg();
        System.out.println("Nós gerados: "+contador);
    }
    
    public void printMap(int[][] map){
        for(int i=0;i<3;i++){
            System.out.println("");
            for(int j=0;j<3;j++){
                System.out.print(map[i][j]+" ");
            }
        }
    }
}
