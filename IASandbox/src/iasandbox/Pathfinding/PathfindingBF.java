/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding;

import iasandbox.ControleUI;
import iasandbox.Pathfinding.BDTree.TreeGen;
import iasandbox.Pathfinding.BDTree.TreeNode;
import iasandbox.PathfindingLogic;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public class PathfindingBF implements PathfindingMethod{

    @Override
public ArrayList<Dots> getPath(int[] origin, int[] end) {
        ArrayDeque<TreeNode> path =null;
        TreeGen objTG = new TreeGen();
        path = objTG.getPathBFS(origin, end);
        PathfindingLogic.getInstance().getMap()[origin[0]][origin[1]].setTipo("origin");
        PathfindingLogic.getInstance().getMap()[end[0]][end[1]].setTipo("end");
        if(path.isEmpty()){
            return(null);
        }else{ 
            return(treeNodeToDots(path));
        }
    }
    
    private ArrayList<Dots> treeNodeToDots(ArrayDeque<TreeNode> path){
        int count = 0;
        ArrayList<Dots> tmp = new ArrayList<>();
        while(!path.isEmpty()){
            tmp.add(new Dots(path.peek().getPos()[0],path.peek().getPos()[1]));
            path.pop();
            count++;
        }
        ControleUI.getInstance().setNumberOfSteps(count);
        return(tmp);
    }
}
