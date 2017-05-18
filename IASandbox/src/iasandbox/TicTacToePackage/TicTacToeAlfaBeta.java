/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;

import AlfaBeta.ABNode;
import AlfaBeta.TreeAlfaBeta;
import IOStream.Writer;
import iasandbox.TicTacToe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author noda2
 */
public class TicTacToeAlfaBeta implements ticTacToePlayer {

    private int player;
    private ABNode atual;
    private Random rand = new Random();

    public TicTacToeAlfaBeta(int number) {
//        Writer.getInstance().initWriter("testeops.txt");
        player = number;
        atual = null;
    }

    @Override
    public int[] logic() {

        //pegar a situação atual do jogo se o player não a tiver, provavelmente apenas quando ele tiver que fazer o primeiro movimento
        if (atual == null) {
            atual = TreeAlfaBeta.getInstance().getNode(TreeAlfaBeta.getInstance().getArvore(), TicTacToe.getInstance().getMap());
        } else {//se não for o primeiro movimento dele
//          //procura o proximo apenas nos filhos do estado anterios
            atual = TreeAlfaBeta.getInstance().getNode(atual, TicTacToe.getInstance().getMap());
//            atual=TreeAlfaBeta.getInstance().genNewTree(TicTacToe.getInstance().getMap());
            if (atual == null) {
                //outro jogador fez movimento inesperado
                atual = TreeAlfaBeta.getInstance().genNewTree(TicTacToe.getInstance().getMap());
                System.out.println("gerou outra árvore...");
                //teve que gerar outra árvore baseada no nó atual
            }

        }

        ArrayList<ABNode> options = getOptions();
        try {
//        Writer.getInstance().printOptions(atual,options,player);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int randomNum = (rand.nextInt(options.size()));
        int[] move = move(atual.getMap(), options.get(randomNum).getMap());

        if (move == null) {
            System.err.println("Erro! sem movimento...");
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
    private ArrayList<ABNode> getOptions() {
        ArrayList<ABNode> res = new ArrayList<>();
        if (player == 1) {
            //jogador MAX
            //achar o maior valor entre os filhos
            Iterator<ABNode> it = atual.getFilhos().iterator();

            int max = Integer.MIN_VALUE;
            while (it.hasNext()) {
                ABNode tmp = it.next();
                if (max < tmp.getUtilidade()) {
                    max = tmp.getUtilidade();
                }
            }
            //pegar todos os nós com a utilidade máxima
            it = atual.getFilhos().iterator();
            while (it.hasNext()) {
                ABNode tmp = it.next();
                if (tmp.getUtilidade() == max) {
                    res.add(tmp);
                }
            }

        } else {
            //jogador MIN
            //achar o maior valor entre os filhos
            Iterator<ABNode> it = atual.getFilhos().iterator();
            int min = Integer.MAX_VALUE;
            while (it.hasNext()) {
                ABNode tmp = it.next();
                if (min > tmp.getUtilidade()) {
                    min = tmp.getUtilidade();
                }
            }
            //pegar todos os nós com a utilidade mínima
            it = atual.getFilhos().iterator();
            while (it.hasNext()) {
                ABNode tmp = it.next();
                if (tmp.getUtilidade() == min) {
                    res.add(tmp);
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