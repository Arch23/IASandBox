/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage.DepthSearchTree;

import Pathfinding.MapNode;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class NoDS {
    private MapNode mapNode;
    private ArrayList<NoDS> Filhos;

    public MapNode getMapNode() {
        return mapNode;
    }

    public void setMapNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }
    
    public int[] getPos() {
        return mapNode.getPos();
    }

    public ArrayList<NoDS> getFilhos() {
        return Filhos;
    }

    public void setFilhos(ArrayList<NoDS> Filhos) {
        this.Filhos = Filhos;
    }

    public NoDS(MapNode node) {
        mapNode = node;
    }
    
    
}
