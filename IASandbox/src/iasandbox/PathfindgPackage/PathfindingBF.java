/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage;

import Pathfinding.TreeGen;
import Pathfinding.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author noda2
 */
public class PathfindingBF implements PathfindingMethod{

    @Override
public ArrayList<Dots> getPath(int[] origin, int[] dest) {
        ArrayList<TreeNode> path =null;
        TreeGen objTG = new TreeGen();
        path = objTG.getPathBFS(origin, dest);
        if(path.isEmpty()){
            return(null);
        }else{ 
            return(treeNodeToDots(path));
        }
    }
    
    private ArrayList<Dots> treeNodeToDots(ArrayList<TreeNode> path){
        ArrayList<Dots> tmp = new ArrayList<>();
        for(TreeNode it: path){
            tmp.add(new Dots(it.getPos()[0],it.getPos()[1]));
        }
        return(tmp);
    }
}
