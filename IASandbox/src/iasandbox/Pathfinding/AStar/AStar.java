/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.AStar;

import iasandbox.ControleUI;
import iasandbox.Pathfinding.BDTree.GenMap;
import iasandbox.Pathfinding.BDTree.MapNode;
import iasandbox.PathfindingLogic;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author noda2
 */
public class AStar {

    private int stepCost = 1;
    private boolean found;
    
    
//    public static void main(String[] Args){
//        int tam = 4;
//        int[] origin = new int[]{0,0};
//        int[] end = new int[]{3,2};
//        int[][] layout = new int[tam][tam];
//        int[][] layout = new int[][]{{0,0,1,0},{0,0,1,0},{0,0,0,0},{0,0,0,0}};
//        GenMap objGM = new GenMap();
//        AStar objAS = new AStar();
//        objAS.map = objGM.init(layout);
//        ArrayDeque<AStarNode> path = objAS.start(origin, end);
//        for(AStarNode node : path){
//            System.out.println("node: "+node.getPos()[0]+" "+node.getPos()[1]);
//        }
//    }
    
    public ArrayDeque<AStarNode> start(int[] origin, int[] end) {
        //pegar o mapa
        MapNode[][] map = PathfindingLogic.getInstance().getMap();
        

        found=false;
        ArrayDeque<AStarNode> path = new ArrayDeque<>();
        AStarNode objective = null;
        
        //criar listas
        ArrayList<AStarNode> open = new ArrayList<>();
        ArrayList<AStarNode> closed = new ArrayList<>();
        AStarNode ori = new AStarNode(map[origin[0]][origin[1]]);
        ori.setG(0);
        ori.setH(0);
        ori.setF();
        open.add(ori);
        open.get(0).getNode().setTipo("open");
        int cont=0;
        while (!open.isEmpty()) {
            AStarNode q = findLowerF(open);
            cont++;
            
            System.out.println("node: "+q.getPos()[0]+" "+q.getPos()[1]);
            removeFromList(open, q);
            q.setSons(genSons(q));
            
//            int i=0;
//            for(AStarNode aux : q.getSons()){
//                System.out.println("filho "+i+"-> "+aux.getPos()[0]+" "+aux.getPos()[1]);
//                i++;
//            }
            
            for (AStarNode son : q.getSons()) {
                if (isEnd(son, end)) {
                    objective=son;
                    found=true;
                    break;
                }

                son.setG(q.getG() + stepCost);
                son.setH(calcH(son, end));
                son.setF();
//                System.out.println("son: "+son.getF());
                if(!isInOpenWithLower(open, son)){
                    if(!isInClosedWithLower(closed, son)){
                        open.add(son);
                        son.getNode().setTipo("open");
                    }
                }
            }
            if(found){
                break;
            }
            closed.add(q);
            q.getNode().setTipo("closed");
        }
        for(AStarNode n : closed){
            System.out.println("node: "+n.getPos()[0]+" "+n.getPos()[1]+" /f: "+n.getF()+" /g: "+n.getF()+" /h: "+n.getH());
        }
        do{
            path.add(objective);
            objective = objective.getFather();
        }while(!isOrigin(objective, origin));
        path.add(objective);
        System.out.println("visitou "+cont+" nós.");
        return(path);
    }

    private boolean isEnd(AStarNode node, int[] end) {
        return (((node.getPos()[0] == end[0]) && (node.getPos()[1] == end[1])) ? (true) : (false));
    }
    
    private boolean isOrigin(AStarNode node, int[] origin) {
        return (((node.getPos()[0] == origin[0]) && (node.getPos()[1] == origin[1])) ? (true) : (false));
    }

    private int calcH(AStarNode node, int[] end) {
        return ((Math.abs(node.getPos()[0] - end[0]) + (Math.abs(node.getPos()[1] - end[1]))));
    }

    private ArrayList<AStarNode> genSons(AStarNode node) {
        ArrayList<AStarNode> sons = new ArrayList<>();
        if (node.getNode().getDown() != null) {
            AStarNode son = new AStarNode(node.getNode().getDown());
            son.setFather(node);
            sons.add(son);
        }
        if (node.getNode().getRight() != null) {
            AStarNode son = new AStarNode(node.getNode().getRight());
            son.setFather(node);
            sons.add(son);
        }
        if (node.getNode().getUp() != null) {
            AStarNode son = new AStarNode(node.getNode().getUp());
            son.setFather(node);
            sons.add(son);
        }
        if (node.getNode().getLeft() != null) {
            AStarNode son = new AStarNode(node.getNode().getLeft());
            son.setFather(node);
            sons.add(son);
        }
        return (sons);
    }

    private AStarNode findLowerF(ArrayList<AStarNode> open) {
        int lower = Integer.MAX_VALUE;
        for (AStarNode node : open) {
            if (node.getF() < lower) {
                lower = node.getF();
            }
        }
        for (AStarNode node : open) {
            if (node.getF() == lower) {
                return (node);
            }
        }
        return (null);
    }

    private void removeFromList(ArrayList<AStarNode> list, AStarNode node) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals(node)) {
                it.remove();
                break;
            }
        }
    }

    private boolean isInOpenWithLower(ArrayList<AStarNode> open, AStarNode node) {
        for (AStarNode tmp : open) {
            if ((tmp.getPos()[0] == node.getPos()[0]) && (tmp.getPos()[1] == node.getPos()[1])) {
                if (tmp.getF() < node.getF()) {
                    //está na lista e o que está na lista possui um 'f' menor que o nó atual
                    return (true);
                }
            }
        }
        //não está na lista ou possui um 'f' maior que o nó atual
        return (false);
    }

    private boolean isInClosedWithLower(ArrayList<AStarNode> closed, AStarNode node) {
        for (AStarNode tmp : closed) {
            if ((tmp.getPos()[0] == node.getPos()[0]) && (tmp.getPos()[1] == node.getPos()[1])) {
                if (tmp.getF() < node.getF()) {
                    //está na lista e o que está na lista possui um 'f' menor que o nó atual
                    return (true);
                }
            }
        }
        //não está na lista ou possui um 'f' maior que o nó atual
        return (false);
    }
}
