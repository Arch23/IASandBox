/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage.DepthSearchTree;

import Pathfinding.GenMap;
import Pathfinding.MapNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import iasandbox.Pathfinding;

/**
 *
 * @author Gabriel
 */
public class DepthSearchTree {

    private boolean pathFound;
    
    public void getPath(NoDS node, int[] pos, ArrayDeque<NoDS> path) {
        path.push(node);
        if ((node.getPos()[0] == pos[0]) && (node.getPos()[1] == pos[1])) {
            pathFound = true;
        } else {
            node.setFilhos(getSons(node));
            for (NoDS son : node.getFilhos()) {
                if (!isInPath(son.getPos(), path)) {
                    getPath(son, pos, path);
                    if (pathFound) {
                        break;
                    }
                }
            }
            if (!pathFound) {
                path.pop();
            }
        }
    }
    
    private boolean isInPath(int[] pos,ArrayDeque<NoDS> path){
        ArrayDeque<NoDS> aux = new ArrayDeque<>();
        NoDS tmpNode = null;
        boolean isHere=false;
        while(!path.isEmpty()){
            tmpNode = path.pop();
            if((tmpNode.getPos()[0]==pos[0])&&tmpNode.getPos()[1]==pos[1]){
                isHere=true;
            }
            aux.push(tmpNode);
        }
        while(!aux.isEmpty()){
            path.push(aux.pop());
        }
        return(isHere);
    }

    private ArrayList<NoDS> getSons(NoDS node) {
        ArrayList<NoDS> sons = new ArrayList<>();

        if (node.getMapNode().getDown() != null) {
            sons.add(new NoDS(node.getMapNode().getDown()));
        }
        if (node.getMapNode().getRight() != null) {
            sons.add(new NoDS(node.getMapNode().getRight()));
        }
        if (node.getMapNode().getUp() != null) {
            sons.add(new NoDS(node.getMapNode().getUp()));
        }
        if (node.getMapNode().getLeft() != null) {
            sons.add(new NoDS(node.getMapNode().getLeft()));
        }
        return (sons);
    }

    public ArrayDeque<NoDS> init(int[] origin, int[] dest, ArrayDeque<NoDS> path){
        getPath(new NoDS(Pathfinding.getInstance().getMap()[origin[0]][origin[1]]), dest, path);
        return(path);
    }
    
//    public static void main(String[] args) {
//        GenMap obj = new GenMap();
//        int[][] layout = new int[3][3];
//        MapNode[][] map = obj.init(layout);
//        NoDS arv = new NoDS(map[0][0]);
//        ArrayDeque<NoDS> path = new ArrayDeque<>();
//        boolean pathFound = false;
//        DepthSearchTree obj2 = new DepthSearchTree();
//        obj2.getPath(arv,new int[]{1,1}, path);
//        System.out.println("caminho encontrado: ");
//        while(!path.isEmpty()){
//            System.out.println("Nó: "+path.peek().getPos()[0]+" "+path.peek().getPos()[1]);
//            path.pop();
//        }
//    }
}
