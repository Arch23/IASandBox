/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage.DepthSearchTree;

import Patifinding.MapNode;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class NoDS {
    private MapNode  Pos;
    private ArrayList<NoDS> Filhos;

    public MapNode getPos() {
        return Pos;
    }

    public void setPos(MapNode Pos) {
        this.Pos = Pos;
    }

    public ArrayList<NoDS> getFilhos() {
        return Filhos;
    }

    public void setFilhos(ArrayList<NoDS> Filhos) {
        this.Filhos = Filhos;
    }

    public NoDS(MapNode Pos) {
        this.Pos = Pos;
    }
    
    
}
