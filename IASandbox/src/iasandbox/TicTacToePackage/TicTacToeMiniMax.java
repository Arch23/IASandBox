/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;

import ArvoreMinimax.Node;
import ArvoreMinimax.TreeMiniMax;
import iasandbox.TicTacToe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author noda2
 */
public class TicTacToeMiniMax implements ticTacToePlayer {

    private int player;
    private Node atual;
    private Random rand = new Random();
    private double level = 0;

    public TicTacToeMiniMax(int number) {
        player = number;
        atual = null;
    }

    @Override
    public int[] logic() {

        //pegar a situação atual do jogo se o player não a tiver, provavelmente apenas quando ele tiver que fazer o primeiro movimento
        if (atual == null) {
            atual = TreeMiniMax.getInstance().getNode(TreeMiniMax.getInstance().getArvore(), TicTacToe.getInstance().getMap());
        } else {//se não for o primeiro movimento dele
//          //procura o proximo apenas nos filhos do estado anterios
            atual = TreeMiniMax.getInstance().getNode(atual, TicTacToe.getInstance().getMap());
        }

        ArrayList<Node> options = getOptions();

        int randomNum = (rand.nextInt(options.size()));
        int[] move = move(atual.getMap(), options.get(randomNum).getMap());

        if (move == null) {
            System.err.println("ERROOOOOOOOOO!");
        }
        return (move);
    }

//    private Node getSon(int[][] map){
//        Iterator<Node> it = atual.getFilhos().iterator();
//        while(it.hasNext()){
//            Node son=it.next();
//            TreeMiniMax.getInstance().printTree(son.getMap());
//            if(TreeMiniMax.getInstance().compMap(son.getMap(),map)){
//                System.out.println("ACHOU O FILHO PERDIDOOOOOO!");
//                return(son);
//            }
//        }
//        return(null);
//    }
    private ArrayList<Node> getOptions() {
        ArrayList<Node> res = new ArrayList<>();
        double dif = (rand.nextDouble()*(1 - 0.1 )+0.1);
        if (player == 1) {
            //jogador MAX
            //achar o maior valor entre os filhos
            Iterator<Node> it = atual.getFilhos().iterator();
            
            if (dif > level) {
                int max = Integer.MIN_VALUE;
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (max < tmp.getUtilidade()) {
                        max = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade máxima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (tmp.getUtilidade() == max) {
                        res.add(tmp);
                    }
                }
            } else {
                int min = Integer.MAX_VALUE;
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (min > tmp.getUtilidade()) {
                        min = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade mínima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (tmp.getUtilidade() == min) {
                        res.add(tmp);
                    }
                }
            }
        } else {
            //jogador MIN
            //achar o maior valor entre os filhos
            Iterator<Node> it = atual.getFilhos().iterator();
            if(dif>level){
                int min = Integer.MAX_VALUE;
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (min > tmp.getUtilidade()) {
                        min = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade mínima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (tmp.getUtilidade() == min) {
                        res.add(tmp);
                    }
                }
            }else{
                int max = Integer.MIN_VALUE;
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (max < tmp.getUtilidade()) {
                        max = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade máxima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    Node tmp = it.next();
                    if (tmp.getUtilidade() == max) {
                        res.add(tmp);
                    }
                }
            }
        }
        return (res);
    }

    private int[] move(int[][] ant, int[][] prox) {
        int[] res = new int[2];
        for (int i = 0; i < ant.length; i++) {
            for (int j = 0; j < ant[i].length; j++) {
                if (ant[i][j] != prox[i][j]) {
                    res[0] = i;
                    res[1] = j;
                    return (res);
                }
            }
        }
        return (null);
    }
}
