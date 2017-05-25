/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToe;

import iasandbox.TicTacToe.MiniMaxTree.MMNode;
import iasandbox.TicTacToe.MiniMaxTree.TreeMiniMax;
import statistics.benchmark.Benchmark;
import statistics.benchmark.BenchmarkXML;
import statistics.IOStream.Writer;
import iasandbox.TicTacToeLogic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author noda2
 */
public class TicTacToeMiniMax implements ticTacToePlayer {

    private int player;
    private MMNode atual;
    private Random rand = new Random();
    private double level = 1;

    public TicTacToeMiniMax(int number, double level) {
        Writer.getInstance().initWriter("Log_MinMax.txt");
        player = number;
        atual = null;
        this.level = level;
    }

    @Override
    public int[] logic() {
        long ini = System.nanoTime();
        //pegar a situação atual do jogo se o player não a tiver, provavelmente apenas quando ele tiver que fazer o primeiro movimento
        if (atual == null) {
            atual = TreeMiniMax.getInstance().getNode(TreeMiniMax.getInstance().getArvore(), TicTacToeLogic.getInstance().getMap());
        } else {//se não for o primeiro movimento dele
//          //procura o proximo apenas nos filhos do estado anterios
            atual = TreeMiniMax.getInstance().getNode(atual, TicTacToeLogic.getInstance().getMap());

        }
        ArrayList<MMNode> options = getOptions();
        try {
            Writer.getInstance().printOptions(atual, options, player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int randomNum = (rand.nextInt(options.size()));
        int[] move = move(atual.getMap(), options.get(randomNum).getMap());
        if (move == null) {
            System.err.println("ERROOOOOOOOOO!");
        }
        long fim = System.nanoTime();
        Benchmark aux = new BenchmarkXML().xmltoBenchmark();
        aux.setMinMaxLogic(aux.getMinMaxLogic()+(fim-ini));
        aux.setMinMaxVezesLogic(aux.getMinMaxVezesLogic()+1);
        new BenchmarkXML().geraXMLfile(aux);
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
    private ArrayList<MMNode> getOptions() {
        ArrayList<MMNode> res = new ArrayList<>();
        double dif = (rand.nextDouble() * (1 - 0.1) + 0.1);
        if (player == 1) {
            //jogador MAX
            //achar o maior valor entre os filhos
            Iterator<MMNode> it = atual.getFilhos().iterator();

            if (dif < level) {
                int max = Integer.MIN_VALUE;
                while (it.hasNext()) {
                    MMNode tmp = it.next();
                    if (max < tmp.getUtilidade()) {
                        max = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade máxima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    MMNode tmp = it.next();
                    if (tmp.getUtilidade() == max) {
                        res.add(tmp);
                    }
                }
            } else {
                if (dif < (level / 2)) {
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (0 == tmp.getUtilidade()) {
                            res.add(tmp);
                        }
                    }
                    if (res.isEmpty()) {
                        it = atual.getFilhos().iterator();
                        int min = Integer.MAX_VALUE;
                        while (it.hasNext()) {
                            MMNode tmp = it.next();
                            if (min > tmp.getUtilidade()) {
                                min = tmp.getUtilidade();
                            }
                        }
                        //pegar todos os nós com a utilidade mínima
                        it = atual.getFilhos().iterator();
                        while (it.hasNext()) {
                            MMNode tmp = it.next();
                            if (tmp.getUtilidade() == min) {
                                res.add(tmp);
                            }
                        }
                    }
                } else {
                    it = atual.getFilhos().iterator();
                    int min = Integer.MAX_VALUE;
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (min > tmp.getUtilidade()) {
                            min = tmp.getUtilidade();
                        }
                    }
                    //pegar todos os nós com a utilidade mínima
                    it = atual.getFilhos().iterator();
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (tmp.getUtilidade() == min) {
                            res.add(tmp);
                        }
                    }
                }
            }
        } else {
            //jogador MIN
            //achar o maior valor entre os filhos
            Iterator<MMNode> it = atual.getFilhos().iterator();
            if (dif < level) {
                int min = Integer.MAX_VALUE;
                while (it.hasNext()) {
                    MMNode tmp = it.next();
                    if (min > tmp.getUtilidade()) {
                        min = tmp.getUtilidade();
                    }
                }
                //pegar todos os nós com a utilidade mínima
                it = atual.getFilhos().iterator();
                while (it.hasNext()) {
                    MMNode tmp = it.next();
                    if (tmp.getUtilidade() == min) {
                        res.add(tmp);
                    }
                }
            } else {
                if (dif < (level / 2)) {
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (0 == tmp.getUtilidade()) {
                            res.add(tmp);
                        }
                    }
                    if (res.isEmpty()) {
                        it = atual.getFilhos().iterator();
                        int max = Integer.MIN_VALUE;
                        while (it.hasNext()) {
                            MMNode tmp = it.next();
                            if (max < tmp.getUtilidade()) {
                                max = tmp.getUtilidade();
                            }
                        }
                        //pegar todos os nós com a utilidade máxima
                        it = atual.getFilhos().iterator();
                        while (it.hasNext()) {
                            MMNode tmp = it.next();
                            if (tmp.getUtilidade() == max) {
                                res.add(tmp);
                            }
                        }
                    }
                } else {
                    it = atual.getFilhos().iterator();
                    int max = Integer.MIN_VALUE;
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (max < tmp.getUtilidade()) {
                            max = tmp.getUtilidade();
                        }
                    }
                    //pegar todos os nós com a utilidade máxima
                    it = atual.getFilhos().iterator();
                    while (it.hasNext()) {
                        MMNode tmp = it.next();
                        if (tmp.getUtilidade() == max) {
                            res.add(tmp);
                        }
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
