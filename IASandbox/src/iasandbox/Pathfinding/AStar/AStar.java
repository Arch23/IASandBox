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

        found = false;
        ArrayDeque<AStarNode> path = new ArrayDeque<>();
        AStarNode objective = null;

        //criar listas
        //lista open guarda os possiveis nós para expanção
        ArrayList<AStarNode> open = new ArrayList<>();
        //lista closed guarda os nós já expandidos
        ArrayList<AStarNode> closed = new ArrayList<>();
        
        //cria e inicializa o nó origem
        AStarNode ori = new AStarNode(map[origin[0]][origin[1]]);
        ori.setG(0);
        ori.setH(0);
        ori.setF();
        open.add(ori);
        open.get(0).getNode().setTipo("open");
        
        //inicializa a search
        while (!open.isEmpty()) {
            //retira o nó com o menor f da lista open
            AStarNode q = findLowerF(open);
            removeFromList(open, q);
            
            //gera os filhos dele
            q.setSons(genSons(q));
            
            //para cada filho dele
            for (AStarNode son : q.getSons()) {
                
                //verifica se o filho é o destino, se for para a busca
                if (isEnd(son, end)) {
                    objective = son;
                    found = true;
                    break;
                }
                //Se o filho não for o destino calcula a heuristica dele
                //valor da origem até o nó, considerando todo nó com o mesmo passo
                son.setG(q.getG() + stepCost);
                
                //calcula aproximadamente o valor do nó ate o destino
                //foi utilizado o método de calculo manhattan
                son.setH(calcH(son, end));
                
                //soma g com h
                son.setF();
                
                
                if (!isInOpenWithLower(open, son)) {
                    //se na lista open tiver alguém na mesma posição com f menor que o atual, descarta o nó atual
                    if (!isInClosedWithLower(closed, son)) {
                        //se tiver alguém na lista closed na mesma posição com f menor que o atual, descarta o nó atual
                        open.add(son);
                        son.getNode().setTipo("open");
                    }
                }
            }
            //quebra a search se tiver sido encontrado o nó objetivo
            if (found) {
                break;
            }
            //nó totalmente explorado então vai para a lista closed
            closed.add(q);
            q.getNode().setTipo("closed");
        }
        if (objective != null) {
            do {
                path.add(objective);
                objective = objective.getFather();
            } while (!isOrigin(objective, origin));
            path.add(objective);
        }
        return (path);
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

        for (int i = open.size() - 1; i >= 0; i--) {
            if (open.get(i).getF() == lower) {
                return (open.get(i));
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
