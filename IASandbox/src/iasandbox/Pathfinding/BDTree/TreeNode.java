/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.BDTree;

import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public class TreeNode {
    private MapNode mapNode;
    private TreeNode father;
    private ArrayList<TreeNode> sons;
    private boolean visited;

    public MapNode getMapNode() {
        return mapNode;
    }

    public void setMapNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }
    
    public int[] getPos() {
        return mapNode.getPos();
    }

    public ArrayList<TreeNode> getSons() {
        return sons;
    }

    public void setSons(ArrayList<TreeNode> sons) {
        this.sons = sons;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public TreeNode(MapNode node) {
        mapNode = node;
        visited=false;
    }

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }
    
    
    
}
