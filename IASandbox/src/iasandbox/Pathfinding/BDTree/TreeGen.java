/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.BDTree;

import iasandbox.ControleUI;
import iasandbox.Pathfinding.Map.MapNode;
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
    
    /*
    var
    */
    private boolean pathFound;
    private TreeNode objective;
    private int count = 0;
    /*
    end var
    */
    
    /*
    methods
    */
    //método para requisitar o caminho da busca em profundidade
    public ArrayDeque<TreeNode> getPathDFS(int[] origin, int[] end) {
        pathFound = false;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(PathfindingLogic.getInstance().getMap()[origin[0]][origin[1]]);
        genTreeDFS(root, end, path);
        ControleUI.getInstance().setNumberOfNodesVisited(count);
        return (path);
    }
    
    //método para requisitar o caminho da busca em largura
    public ArrayDeque<TreeNode> getPathBFS(int[] origin, int[] end) {
        pathFound = false;
        objective=null;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(PathfindingLogic.getInstance().getMap()[origin[0]][origin[1]]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        genTreeBFS(root, end);
        ControleUI.getInstance().setNumberOfNodesVisited(count);
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
    
    //gera e percorre a árvore utilizando a busca em profundidade
    public void genTreeDFS(TreeNode node, int[] end, ArrayDeque<TreeNode> path) {
        count++;
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
    
    //gera e percorre a árvore utilizando a busca em largura
    private void genTreeBFS(TreeNode node,int[] end){
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> closed = new ArrayList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            count++;
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
    
    //gera os filhos do nó verificando se o filho não está no caminho já
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

    //função de gerar filhos da busca em largura
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
    
    //verifica se o nó desta posição já esta no caminho
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
    
    //verifica se o nó já esta na lista
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
    /*
    end methods
    */
}
