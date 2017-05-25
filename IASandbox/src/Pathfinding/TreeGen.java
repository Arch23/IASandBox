/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import iasandbox.Pathfinding;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author noda2
 */
public class TreeGen {

    private boolean pathFound;

    public void genTree(TreeNode node, int[] end, ArrayDeque<TreeNode> path) {
        path.push(node);
        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
            //se chegar no nó final não o expande
            node.setSons(new ArrayList<>());
        } else {
            //pega os filhos do nó
            node.setSons(genSons(node, path));
            //expande os filhos
            node.getSons().forEach((son) -> {
                genTree(son, end, path);
            }); //nó com todos os filhos expandidos
        }
        path.pop();
    }

    public void genTree(TreeNode node, TreeNode pai, int[] end, ArrayDeque<TreeNode> path) {
        path.push(node);
        node.setFather(pai); //Marcar quem é o pai no nó , duplo encadeamento
        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
            //se chegar no nó final não o expande           
            node.setSons(new ArrayList<>());
        } else {
            //pega os filhos do nó
            node.setSons(genSons(node, path));
            //expande os filhos
            for (TreeNode son : node.getSons()) {
                genTree(son, node, end, path);
            } //nó com todos os filhos expandidos
        }
        path.pop();
    }

    private boolean isInPath(int[] pos, ArrayDeque<TreeNode> path) {
        ArrayDeque<TreeNode> aux = new ArrayDeque<>();
        TreeNode tmpNode = null;
        boolean isHere = false;
        while (!path.isEmpty()) {
            tmpNode = path.pop();
            if ((tmpNode.getPos()[0] == pos[0]) && tmpNode.getPos()[1] == pos[1]) {
                isHere = true;
            }
            aux.push(tmpNode);
        }
        while (!aux.isEmpty()) {
            path.push(aux.pop());
        }
        return (isHere);
    }

    private ArrayList<TreeNode> genSons(TreeNode node, ArrayDeque<TreeNode> path) {
        ArrayList<TreeNode> sons = new ArrayList<>();

        if (node.getMapNode().getDown() != null) {
            //não cria um filho que já esta no caminho atual
            if (!isInPath(node.getMapNode().getDown().getPos(), path)) {
                sons.add(new TreeNode(node.getMapNode().getDown()));
            }
        }
        if (node.getMapNode().getRight() != null) {
            if (!isInPath(node.getMapNode().getRight().getPos(), path)) {
                sons.add(new TreeNode(node.getMapNode().getRight()));
            }
        }
        if (node.getMapNode().getUp() != null) {
            if (!isInPath(node.getMapNode().getUp().getPos(), path)) {
                sons.add(new TreeNode(node.getMapNode().getUp()));
            }

        }
        if (node.getMapNode().getLeft() != null) {
            if (!isInPath(node.getMapNode().getLeft().getPos(), path)) {
                sons.add(new TreeNode(node.getMapNode().getLeft()));
            }

        }
        return (sons);
    }

    private void printTree(TreeNode node, int[] end) {
        TreeNode father = node.getFather();
        if (father == null) {
            System.out.println("RAIZ node: " + node.getPos()[0] + " " + node.getPos()[1]);
        } else {
            if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
                System.out.println("ITS ALIVEEEEE! Father: " + father.getPos()[0] + " " + father.getPos()[1] + " node: " + node.getPos()[0] + " " + node.getPos()[1]);
            } else {
                System.out.println("Father: " + father.getPos()[0] + " " + father.getPos()[1] + " node: " + node.getPos()[0] + " " + node.getPos()[1]);
            }
        }
        for (TreeNode son : node.getSons()) {
            printTree(son, end);
        }
    }

    public ArrayDeque<TreeNode> getPathDFS(int[] origin, int[] end) {
        pathFound = false;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(Pathfinding.getInstance().getMap()[origin[0]][origin[1]]);
        genTree(root, end, path);
        path.clear();
        dFS(root, path, end);
        return (path);
    }

    public ArrayList<TreeNode> getPathBFS(int[] ini, int[] end) {
        TreeNode node = new TreeNode(Pathfinding.getInstance().getMap()[ini[0]][ini[1]]);//Inicia a arvore
        System.out.println(Arrays.toString(node.getPos()));
        System.out.println(Arrays.toString(end));
        ArrayDeque<TreeNode> pathad = new ArrayDeque<>();//Path para montagem da arvore
        genTree(node, null, end, pathad); //Geração da  arvore
        ArrayList<TreeNode> path = new ArrayList<>();//Array para guardar o caminho até o nó econtrado pela busca
        Queue<TreeNode> queue = new LinkedList<>();//fila para iterar sobre a arvore
        queue.add(node); //Adicionando o primeiro no na fila
        TreeNode aux = null;
        while (!queue.isEmpty()) { //Enquanto a fila não for vazia.....
            //  System.out.println(Arrays.toString(queue.peek().getPos()));
            //Verifica se o primeiro elemento é a solução
            if (queue.peek().getPos()[0] == end[0] && queue.peek().getPos()[1] == end[1]) {
                System.out.println("achoooo");
                aux = queue.peek(); //Se for guarda na aux
                break;//.. e quebra o laço
            }
            for (TreeNode tn : queue.peek().getSons()) { //Itera sobre os filhos e coloca eles na fila
                queue.add(tn);
            }
            queue.remove(); //Remove o primeiro elemento da fila
        }
        if (queue.isEmpty()) { //Se a fila ficou vazia não tem solução
            System.out.println("xablauuu");
            return path;
        }
        do {
            path.add(aux); //Adiciona o nó final do caminho
            System.out.println("Pai" + Arrays.toString(aux.getPos()));
            aux = aux.getFather(); //Pega o pai
        } while (!(aux.getPos()[0] == ini[0] && aux.getPos()[1] == ini[1])); //Até chegar na posição inicial
        System.out.println("Pai" + Arrays.toString(aux.getPos())); 
        path.add(aux); //Adiciona o nó inicial
        System.out.println("TamPath" + path.size());
        return path; //Retorna o array com os pontos
    }

    private void dFS(TreeNode node, ArrayDeque<TreeNode> path, int[] end) {
        path.push(node);
        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
            pathFound = true;
        } else {
            for (TreeNode son : node.getSons()) {
                dFS(son, path, end);
                if (pathFound) {
                    break;
                }
            }
            if (!pathFound) {
                path.pop();
            }
        }
    }

    /*   private void bFS(TreeNode node, ArrayDeque<TreeNode> path, Queue<TreeNode> queue, int[] end) {
        queue.add(node);
        node.setVisited(true);
        while () {

        }
    }*/
    public static void main(String[] Args) {
//        int[][] layout = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int tam = 3;
        int[] origin = new int[]{0, 0};
        int[] end = new int[]{2, 2};
        int[][] layout = new int[tam][tam];
        GenMap objGM = new GenMap();
        MapNode[][] map = objGM.init(layout);
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(map[origin[0]][origin[1]]);
        TreeGen objTG = new TreeGen();
        objTG.genTree(root, null, end, path);
        objTG.printTree(root, end);
        objTG.getPathBFS(origin, end);
    }
}
