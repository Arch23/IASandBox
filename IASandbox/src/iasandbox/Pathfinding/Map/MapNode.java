/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.Pathfinding.Map;

/**
 *
 * @author noda2
 */
public class MapNode {
    /*
        Var
    */
    private int[] pos;
    private double costUp;
    private double costDown;
    private double costLeft;
    private double costRight;
    private String tipo;
    private MapNode Up;
    private MapNode Down;
    private MapNode Left;
    private MapNode Right;
    /*
        End Var
    */
    
    public MapNode(int[] pos,String tipo){
        this.pos = pos;
        this.tipo = tipo;
    }

    

    /*
    Getters Setters
     */
    public MapNode getUp() {
        return Up;
    }

    public void setUp(MapNode Up) {
        this.Up = Up;
    }

    public MapNode getDown() {
        return Down;
    }

    public void setDown(MapNode Down) {
        this.Down = Down;
    }

    public MapNode getLeft() {
        return Left;
    }

    public void setLeft(MapNode Left) {
        this.Left = Left;
    }

    public MapNode getRight() {
        return Right;
    }
    
    public void setRight(MapNode Right) {
        this.Right = Right;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public double getCostUp() {
        return costUp;
    }

    public void setCostUp(double costUp) {
        this.costUp = costUp;
    }

    public double getCostDown() {
        return costDown;
    }

    public void setCostDown(double costDown) {
        this.costDown = costDown;
    }

    public double getCostLeft() {
        return costLeft;
    }

    public void setCostLeft(double costLeft) {
        this.costLeft = costLeft;
    }

    public double getCostRight() {
        return costRight;
    }

    public void setCostRight(double costRight) {
        this.costRight = costRight;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /*
        End Getters Setters
    */
}
