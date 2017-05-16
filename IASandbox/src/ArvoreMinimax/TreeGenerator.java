/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreMinimax;

import IOStream.Writer;
import iasandbox.TicTacToe;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class TreeGenerator {

    private Node arvore;
    private int jogadormax, contador, height;

    public void geraArvore(Node node, int h) {
        if (TicTacToe.getInstance().checkBoard(node.getMap(), h) == -1) {
            node.setFilhos(geraFilhos(node, h));
            Iterator<Node> it = node.getFilhos().iterator();
            contador++;
            while (it.hasNext()) {
                int fH = h + 1;
                geraArvore(it.next(), fH);
            }
        }
        if (node.getFilhos().isEmpty()) { //Se for folha( sem filhos )
            node.setUtilidade(calcUtLeaf(node.getMap(), h));
        } else { //Se não for filhos
            calcUtFather(node, h);
        }
        Writer.getInstance().writeNode(node);
    }

    public void calcUtFather(Node node, int h) {
        int aux;
        if (h % 2 == 0) {
            aux = Integer.MIN_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() > aux) {
                    node.setUtilidade(node.getFilhos().get(i).getUtilidade());
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        } else {
            aux = Integer.MAX_VALUE;
            for (int i = 0; i < node.getFilhos().size(); i++) {
                if (node.getFilhos().get(i).getUtilidade() < aux) {
                    node.setUtilidade(node.getFilhos().get(i).getUtilidade());
                    aux = node.getFilhos().get(i).getUtilidade();
                }
            }
        }
    }

    public void startAlg() {
        setRoot();
        geraArvore(arvore, height);
    }

    private void setRoot() {
        int[][] map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
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

    private ArrayList<Node> geraFilhos(Node no, int h) {
        Node filho;
        ArrayList<Position> zeros = getZerosPos(no);
        ArrayList<Node> filhos = new ArrayList<>();
        for (int i = 0; i < zeros.size(); i++) {
            int map[][] = no.getMap();
            map[zeros.get(i).getX()][zeros.get(i).getY()] = (((h % 2) == 0) ? 1 : 2);
            filho = new Node(map);
            filhos.add(filho);
        }
        return filhos;
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

    public void start() {
        Writer.getInstance().initWriter();
        height = 0;
        startAlg();
        System.out.println("Nós gerados: " + contador);
        Writer.getInstance().closeWriter();
    }
}
