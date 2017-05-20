/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;

import AlfaBeta.ABNode;
import AlfaBeta.TreeAlfaBeta;
import Benchmark.Benchmark;
import Benchmark.BenchmarkXML;
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
        long ini=System.nanoTime();
        //sempre gera uma nova árvore para os movimentos
        atual = TreeAlfaBeta.getInstance().genNewTree(TicTacToe.getInstance().getMap());

        ArrayList<ABNode> options = getOptions();
        try {
//        Writer.getInstance().printOptions(atual,options,player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //pega o primeiro movimento ótimo
        int[] move = move(atual.getMap(), options.get(0).getMap());

        if (move == null) {
            System.err.println("Erro! sem movimento...");
        }
        long fim=System.nanoTime();
        Benchmark aux = new BenchmarkXML().xmltoBenchmark();
        aux.setPodaLogic(aux.getPodaLogic()+(fim - ini));
        aux.setPodaVezesLogic(aux.getPodaVezesLogic()+1);
        new BenchmarkXML().geraXMLfile(aux);
        return (move);
    }

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
