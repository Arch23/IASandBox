/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToe.MiniMaxTree;

import statistics.benchmark.Benchmark;
import statistics.benchmark.BenchmarkXML;
import iasandbox.TicTacToeLogic;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class TreeMiniMax {

    private static TreeMiniMax instance;

    private MMNode arvore;
    private int jogadormax, contador, height;

    public static TreeMiniMax getInstance() {
        return ((instance == null) ? (instance = new TreeMiniMax()) : instance);
    }

    private TreeMiniMax() {
        startAlg();
    }

    public void geraArvore(MMNode node, int h) {
        if (TicTacToeLogic.getInstance().checkBoard(node.getMap(), h) == -1) {
            node.setFilhos(geraFilhos(node, h));
            Iterator<MMNode> it = node.getFilhos().iterator();
            contador++;
            while (it.hasNext()) {
                geraArvore(it.next(), (h + 1));
            }
        }
        if (node.getFilhos().isEmpty()) { //Se for folha( sem filhos )
            node.setUtilidade(calcUtLeaf(node.getMap(), h));
        } else { //Se não for filhos
            node.setUtilidade(calcUtFather(node, h));
        }
//        Writer.getInstance().writeNode(node,h);        
    }

    public int calcUtFather(MMNode node, int h) {
        int aux;
        if (h % 2 == 0) {
            aux = Integer.MIN_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() > aux) {
//                    node.setUtilidade(node.getFilhos().get(i).getUtilidade());
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        } else {
            aux = Integer.MAX_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() < aux) {
//                    node.setUtilidade(node.getFilhos().get(i).getUtilidade());
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        }
        return (aux);
    }

    public void startAlg() {
        setRoot();
        long ini = System.nanoTime();
        geraArvore(arvore, height);
        long fim = System.nanoTime();
        Benchmark aux = new BenchmarkXML().xmltoBenchmark();
        aux.setMinMaxGeraArvore(aux.getMinMaxGeraArvore()+(fim - ini));
        aux.setMinMaxVezesGerouArvore(aux.getMinMaxVezesGerouArvore()+1);
        new BenchmarkXML().geraXMLfile(aux);
    }

    private void setRoot() {
        int[][] map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        arvore = new MMNode(map);
    }

    public ArrayList<Position> getZerosPos(MMNode no) {
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

    private ArrayList<MMNode> geraFilhos(MMNode no, int h) {
        MMNode filho;
        ArrayList<Position> zeros = getZerosPos(no);
        ArrayList<MMNode> filhos = new ArrayList<>();
        for (int i = 0; i < zeros.size(); i++) {
            int map[][] = no.getMap();
            map[zeros.get(i).getX()][zeros.get(i).getY()] = (((h % 2) == 0) ? 1 : 2);
            filho = new MMNode(map);
            filhos.add(filho);
        }
        return filhos;
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

    public boolean compMap(int[][] map1, int[][] map2) {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                if (map1[i][j] != map2[i][j]) {
                    return (false);
                }
            }
        }
        return (true);
    }

    public boolean containsMap(int[][] mapOut, int[][] mapInner) {
        for (int i = 0; i < mapOut.length; i++) {
            for (int j = 0; j < mapOut[i].length; j++) {
                if ((mapOut[i][j] == 1 && mapInner[i][j] == 2) || (mapOut[i][j] == 2 && mapInner[i][j] == 1)) {
                    return (false);
                }
            }
        }
        return (true);
    }

    public MMNode getNode(MMNode node, int[][] map) {
        if (compMap(map, node.getMap())) {
            return (node);
        } else {
            MMNode aux = null;
            Iterator<MMNode> it = node.getFilhos().iterator();
            while (it.hasNext()) {
                MMNode n = it.next();
                if (containsMap(n.getMap(), map)) {
                    aux = getNode(n, map);
                    if (aux != null) {
                        return (aux);
                    }
//                    else{
//                        return(aux);
//                    }
                }
            }
            return (aux);
        }
    }

    public int getJogadorMax() {
        return jogadormax;
    }

    public void setJogadorMax(int jogador) {
        this.jogadormax = jogador;
    }

    public MMNode getArvore() {
        return arvore;
    }

    //Para debug APAGAR DEPOIS    
    public void printTree(int[][] map) {
        System.out.println("");
        for (int i = 0; i < 3; i++) {
            System.out.println("");
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
    }
}
