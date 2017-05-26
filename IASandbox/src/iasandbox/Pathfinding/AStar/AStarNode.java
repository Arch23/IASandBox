/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.AStar;

import iasandbox.Pathfinding.BDTree.MapNode;
import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public class AStarNode {
    private MapNode node;
    private ArrayList<AStarNode> sons;
    private AStarNode father;
    private int f; //heuristica do nó
    private int g; //custo da origem até o nó
    private int h; //custo do nó até o destino
    
    public AStarNode(MapNode node){
        this.node = node;
    }
    
    public int[] getPos(){
        return(node.getPos());
    }
    
    public void setPos(int[] pos){
        node.setPos(pos);
    }

    public AStarNode getFather() {
        return father;
    }

    public void setFather(AStarNode father) {
        this.father = father;
    }
    
    
    public MapNode getNode() {
        return node;
    }

    public void setNode(MapNode node) {
        this.node = node;
    }

    public int getF() {
        return f;
    }

    public void setF() {
        this.f = (g+h);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ArrayList<AStarNode> getSons() {
        return sons;
    }

    public void setSons(ArrayList<AStarNode> sons) {
        this.sons = sons;
    }
    
    
}
