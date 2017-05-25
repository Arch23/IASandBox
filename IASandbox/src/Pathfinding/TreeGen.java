/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import iasandbox.Pathfinding;
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

    private void printTree(TreeNode node,TreeNode father,int[] end) {
        if(father==null){
            System.out.println("RAIZ node: " + node.getPos()[0] + " " + node.getPos()[1]);
        }else{
            if((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])){
                System.out.println("ITS ALIVEEEEE! Father: "+ father.getPos()[0] + " " + father.getPos()[1]+" node: " + node.getPos()[0] + " " + node.getPos()[1]);
            }else{
                System.out.println("Father: "+ father.getPos()[0] + " " + father.getPos()[1]+" node: " + node.getPos()[0] + " " + node.getPos()[1]);
            }
        }
        for (TreeNode son : node.getSons()) {
            printTree(son,node,end);
        }
    }
    
    public ArrayDeque<TreeNode> getPathDFS(int[] origin, int[] end){
        pathFound=false;
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(Pathfinding.getInstance().getMap()[origin[0]][origin[1]]);
        genTree(root, end, path);
        path.clear();
        dFS(root,path,end);
        return(path);
    }
    
    private void dFS(TreeNode node, ArrayDeque<TreeNode> path,int[] end){
        path.push(node);
        if((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])){
            pathFound=true;
        }else{
            for(TreeNode son : node.getSons()){
                dFS(son,path,end);
                if(pathFound){
                    break;
                }
            }
            if(!pathFound){
                path.pop();
            }
        }
    }
    

    public static void main(String[] Args) {
//        int[][] layout = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int tam=2;
        int[] origin = new int[]{0,0};
        int[] end = new int[]{1,1};
        int[][] layout = new int[tam][tam];
        GenMap objGM = new GenMap();
        MapNode[][] map = objGM.init(layout);
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        TreeNode root = new TreeNode(map[origin[0]][origin[1]]);
        TreeGen objTG = new TreeGen();
        objTG.genTree(root,end, path);
        objTG.printTree(root,null,end);
    }
}
