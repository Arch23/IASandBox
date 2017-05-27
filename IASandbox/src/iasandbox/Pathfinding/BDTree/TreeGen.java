/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.BDTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import iasandbox.PathfindingLogic;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author noda2
 */
public class TreeGen {

    private boolean pathFound;
    private TreeNode objective;

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

    //gera a árvore de possibilidades de todos os caminhos
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
    
    //gera a árvore até encontrar o nó objetivo apenas
    public void genTreeDFS(TreeNode node, int[] end, ArrayDeque<TreeNode> path) {
        path.push(node);
        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {         
            node.setSons(new ArrayList<>());
            pathFound=true;
        } else {
            node.setSons(genSons(node, path));
            for (TreeNode son : node.getSons()) {
                genTreeDFS(son, end, path);
                if(pathFound){
                    break;
                }
            }
        }
        if(!pathFound){
            path.pop();
        }
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

    public ArrayDeque<TreeNode> getPathDFS(int[] origin, int[] end) {
        pathFound = false;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(PathfindingLogic.getInstance().getMap()[origin[0]][origin[1]]);
        genTreeDFS(root, end, path);
        return (path);
    }

    public ArrayDeque<TreeNode> getPathBFSR(int[] origin, int[] end) {
        pathFound = false;
        objective=null;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(PathfindingLogic.getInstance().getMap()[origin[0]][origin[1]]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
//        genTreeBFSR(end, path, queue);
        genTreeBFS(root, end);
        path.clear();
        if(objective!=null){
            do{
                path.add(objective);
                objective = objective.getFather();
            }while(!((objective.getPos()[0]==origin[0])&&(objective.getPos()[1]==origin[1])));
            path.add(objective);
        }
        return (path);
    }
    
    private void genTreeBFS(TreeNode node,int[] end){
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> closed = new ArrayList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            TreeNode current = queue.remove();
            if((current.getPos()[0]==end[0])&&(current.getPos()[1]==end[1])){
                current.setSons(new ArrayList<>());
                objective = current;
                break;
            }
            current.setSons(genSonsBFS(current, closed));
            for(TreeNode son : current.getSons()){
                if(!isInList(son, closed)){
                    son.setFather(current);
                    queue.add(son);
                }
            }
            //nó totalmente expandido
            closed.add(current);
        }
    }
    
    private ArrayList<TreeNode> genSonsBFS(TreeNode node, ArrayList<TreeNode> closed) {
        ArrayList<TreeNode> sons = new ArrayList<>();

        if (node.getMapNode().getDown() != null) {
            //não cria um filho que já esta no caminho atual
            if (!isInList(node.getMapNode().getDown(),closed)) {
                sons.add(new TreeNode(node.getMapNode().getDown()));
            }
        }
        if (node.getMapNode().getRight() != null) {
            if (!isInList(node.getMapNode().getRight(),closed)) {
                sons.add(new TreeNode(node.getMapNode().getRight()));
            }
        }
        if (node.getMapNode().getUp() != null) {
            if (!isInList(node.getMapNode().getUp(),closed)) {
                sons.add(new TreeNode(node.getMapNode().getUp()));
            }

        }
        if (node.getMapNode().getLeft() != null) {
            if (!isInList(node.getMapNode().getLeft(),closed)) {
                sons.add(new TreeNode(node.getMapNode().getLeft()));
            }

        }
        return (sons);
    }
    
    private boolean isInList(MapNode node,  ArrayList<TreeNode> closed){
        for(TreeNode n : closed){
            if((n.getPos()[0]==node.getPos()[0])&&(n.getPos()[1]==node.getPos()[1])){
                return(true);
            }
        }
        return(false);
    }
    
    private boolean isInList(TreeNode node,  ArrayList<TreeNode> closed){
        for(TreeNode n : closed){
            if((n.getPos()[0]==node.getPos()[0])&&(n.getPos()[1]==node.getPos()[1])){
                return(true);
            }
        }
        return(false);
    }
    
    private void genTreeBFSR(int[] end,ArrayDeque<TreeNode> path,Queue<TreeNode> queue){
        TreeNode current = queue.remove();
        path.add(current);
        
        if((current.getPos()[0]==end[0])&&(current.getPos()[1]==end[1])){
            current.setSons(new ArrayList<>());
            objective = current;
            pathFound=true;
        }else{
            current.setSons(genSons(current, path));
            for(TreeNode son:current.getSons()){
                son.setFather(current);
                queue.add(son);
            }
            if(!queue.isEmpty()){
                if(!pathFound){
                    genTreeBFSR(end,path.clone(), queue);
                }
            }
        }
        path.pop();
    }
    
    public ArrayList<TreeNode> getPathBFS(int[] ini, int[] end) {
        TreeNode node = new TreeNode(PathfindingLogic.getInstance().getMap()[ini[0]][ini[1]]);//Inicia a arvore
        ArrayDeque<TreeNode> pathad = new ArrayDeque<>();//Path para montagem da arvore
        genTree(node, null, end, pathad); //Geração da  arvore
        ArrayList<TreeNode> path = new ArrayList<>();//Array para guardar o caminho até o nó econtrado pela busca
        Queue<TreeNode> queue = new LinkedList<>();//fila para iterar sobre a arvore
        queue.add(node); //Adicionando o primeiro no na fila
        TreeNode aux = null;
        while (!queue.isEmpty()) { //Enquanto a fila não for vazia.....
            //System.out.println(Arrays.toString(queue.peek().getPos()));
            //Verifica se o primeiro elemento é a solução
            if (queue.peek().getPos()[0] == end[0] && queue.peek().getPos()[1] == end[1]) {
                aux = queue.peek(); //Se for guarda na aux
                break;//.. e quebra o laço
            }
            for (TreeNode tn : queue.peek().getSons()) { //Itera sobre os filhos e coloca eles na fila
                queue.add(tn);
            }
            queue.remove(); //Remove o primeiro elemento da fila
        }
        if (queue.isEmpty()) { //Se a fila ficou vazia não tem solução
            return path;
        }
        do {
            path.add(aux); //Adiciona o nó final do caminho
            aux = aux.getFather(); //Pega o pai
        } while (!(aux.getPos()[0] == ini[0] && aux.getPos()[1] == ini[1])); //Até chegar na posição inicial
        path.add(aux); //Adiciona o nó inicial
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

//    public static void main(String[] Args) {
//        int tam = 5;
//        int[] origin = new int[]{0, 0};
//        int[] end = new int[]{4, 3};
//        int[][] layout = new int[tam][tam];
//        GenMap objGM = new GenMap();
//        MapNode[][] map = objGM.init(layout);
//        TreeNode root = new TreeNode(map[origin[0]][origin[1]]);
//        TreeGen objTG = new TreeGen();
////        objTG.genTree(root, null, end, path);
////        objTG.printTree(root, end);
//        
//        ArrayDeque<TreeNode> path = new ArrayDeque<>();
//        path = objTG.getPathBFSN(origin, end);
//        for(TreeNode node : path){
//            System.out.println("nó: "+node.getPos()[0]+" "+node.getPos()[1]);
//        }
//    }
    
//    private void printTree(TreeNode node, int[] end) {
//    TreeNode father = node.getFather();
//    if (father == null) {
//        System.out.println("RAIZ node: " + node.getPos()[0] + " " + node.getPos()[1]);
//    } else {
//        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
//            System.out.println("ITS ALIVEEEEE! Father: " + father.getPos()[0] + " " + father.getPos()[1] + " node: " + node.getPos()[0] + " " + node.getPos()[1]);
//        } else {
//            System.out.println("Father: " + father.getPos()[0] + " " + father.getPos()[1] + " node: " + node.getPos()[0] + " " + node.getPos()[1]);
//        }
//    }
//        for (TreeNode son : node.getSons()) {
//            printTree(son, end);
//        }
//    }
    
//    private void printTreeBFS(TreeNode node,int[] end){
//        if ((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) {
//            System.out.println("ITS ALIVEEEEE! node: " + node.getPos()[0] + " " + node.getPos()[1]);
//        } else {
//            System.out.println("node: " + node.getPos()[0] + " " + node.getPos()[1]);
//        }
//        for (TreeNode son : node.getSons()) {
//            printTreeBFS(son, end);
//        }
//    }
}
