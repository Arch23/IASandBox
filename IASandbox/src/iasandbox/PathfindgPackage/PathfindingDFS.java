/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage;

import iasandbox.PathfindgPackage.DepthSearchTree.DepthSearchTree;
import iasandbox.PathfindgPackage.DepthSearchTree.NoDS;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public class PathfindingDFS implements PathfindingMethod{

    @Override
    public ArrayList<Dots> getPath(int[] origin, int[] dest) {
        ArrayDeque<NoDS> path = new ArrayDeque<>();
        DepthSearchTree obj = new DepthSearchTree();
        obj.init(origin, dest, path);
//        return(null);
        return(noDSToDots(path));
    }
    
    private ArrayList<Dots> noDSToDots(ArrayDeque<NoDS> path){
        ArrayList<Dots> tmp = new ArrayList<>();
        while(!path.isEmpty()){
            tmp.add(new Dots(path.peek().getPos()[0],path.peek().getPos()[1]));
            path.pop();
        }
        return(tmp);
    }
}
