/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreMinimax;

import java.util.ArrayList;
import static javafx.application.Application.launch;

/**
 *
 * @author Gabriel
 */
public class TreeGenerator {

    private Node arvore;
    private static ArrayList<Position> freepositon = new ArrayList<>();
    private int jogadormax;

    public void geraArvore() {

    }

    public static ArrayList<Position> getZerosPos(Node no) {
        for (int i = 0; i < no.getMap().length; i++) {
            for (int j = 0; j < no.getMap()[i].length; j++) {
                if (no.getMap()[i][j] == 0) {
                    freepositon.add(new Position(i, j));
                }
            }
        }
        return freepositon;
    }

    public static ArrayList<Node> geraFilhosMax(Node no) {
        System.out.println("Foi");
        Node filho;
        ArrayList<Position> zeros = getZerosPos(no);
        ArrayList<Node> filhos = new ArrayList<>();
        System.out.println("Teste " + zeros.size());
        for (int i = 0; i < zeros.size(); i++) {
            int map[][] = no.getMap();
            filho = new Node();
            map[zeros.get(i).getX()][zeros.get(i).getY()] = 1;
            filho.setMap(map);
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
        ArrayList<Node> teste;
        Node no = new Node();
        int map[][] ={{0,0,1},{1,0,1},{1,2,2}};
        no.setMap(map);
        teste = geraFilhosMax(no);
        for (int aux = 0; aux < teste.size(); aux++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(teste.get(aux).getMap()[i][j] + "\t");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }
}
