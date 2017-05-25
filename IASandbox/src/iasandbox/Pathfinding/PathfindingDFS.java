/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding;

import iasandbox.Pathfinding.BDTree.TreeGen;
import iasandbox.Pathfinding.BDTree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public class PathfindingDFS implements PathfindingMethod{

    @Override
    public ArrayList<Dots> getPath(int[] origin, int[] dest) {
        ArrayDeque<TreeNode> path =null;
        TreeGen objTG = new TreeGen();
        path = objTG.getPathDFS(origin, dest);
        if(path.isEmpty()){
            return(null);
        }else{ 
            return(treeNodeToDots(path));
        }
    }
    
    private ArrayList<Dots> treeNodeToDots(ArrayDeque<TreeNode> path){
        ArrayList<Dots> tmp = new ArrayList<>();
        while(!path.isEmpty()){
            tmp.add(new Dots(path.peek().getPos()[0],path.peek().getPos()[1]));
            path.pop();
        }
        return(tmp);
    }
}
