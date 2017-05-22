/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage.DepthSearchTree;

import Patifinding.GenMap;
import Patifinding.MapNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gabriel
 */
public class DepthSearchTree {

    private ArrayList<NoDS> geraFilhos(NoDS no) {
        ArrayList<NoDS> filhos = new ArrayList<>();

        if (no.getPos().getDown() != null) { //Verifica se o próximo nó é vazio
            //Verifica se é igual ao pai
            if (no.getPos().getDown().getPos()[0] != no.getPos().getPos()[0]
                    || no.getPos().getDown().getPos()[1] != no.getPos().getPos()[1]) {
                //Caso não seja igual ao pai, adiciona na lista de flhos do nó
                filhos.add(new NoDS(no.getPos().getDown()));
            }
        }
        if (no.getPos().getLeft() != null) {
            if (no.getPos().getLeft().getPos()[0] != no.getPos().getPos()[0]
                    || no.getPos().getLeft().getPos()[1] != no.getPos().getPos()[1]) {
                filhos.add(new NoDS(no.getPos().getLeft()));
            }
        }
        if (no.getPos().getRight() != null) {
            if (no.getPos().getRight().getPos()[0] != no.getPos().getPos()[0]
                    || no.getPos().getRight().getPos()[1] != no.getPos().getPos()[1]) {
                filhos.add(new NoDS(no.getPos().getRight()));
            }
        }
        if (no.getPos().getUp() != null) {
            if (no.getPos().getUp().getPos()[0] != no.getPos().getPos()[0]
                    || no.getPos().getUp().getPos()[1] != no.getPos().getPos()[1]) {
                filhos.add(new NoDS(no.getPos().getUp()));
            }
        }
        System.out.println("O no tem " + filhos.size() + "filhos");
        return filhos;// Retorna os filhos do nó
    }

    private void geraArvore(NoDS no, int[] fim) {
        if (!(no.getPos().getPos()[0] == fim[0] && no.getPos().getPos()[1] == fim[1])
                || geraFilhos(no).isEmpty()) {
            no.setFilhos(geraFilhos(no));
            System.out.println("X=" + no.getPos().getPos()[0] + " Y=" + no.getPos().getPos()[1]);
            Iterator<NoDS> it = no.getFilhos().iterator();
            while (it.hasNext()) {
                geraArvore(it.next(), fim);
            }
        }
    }

    public static void main(String[] args) {
        GenMap obj = new GenMap();
        int[][] layout = new int[2][2];
        MapNode[][] map = obj.init(layout);
        NoDS arv = new NoDS(map[0][0]);
        DepthSearchTree dst = new DepthSearchTree();
        dst.geraArvore(arv, new int[]{1, 0});
    }
}
