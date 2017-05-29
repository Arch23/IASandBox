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
public class GenMap {
    /*
        Vars
    */
    /*
        layout com informações do mapa
        Se 0 campo
        Se 1 parede
        Se 2 obstrução
    */
    private int[][] layout;
    private double campoValor;
    private double obsValor;
    
    /*
        End vars
    */
    public MapNode[][] init(int[][] layout){
        campoValor=1;
        obsValor=2;
        this.layout = layout;
        return(genMap());
        
    }
    
    public void printMap(MapNode[][] map){
        for(int i=0;i<map.length;i++){
            System.out.println("");
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j].getTipo()+"\t");
            }
        }
    }
    
    public MapNode[][] genMap(){
        MapNode[][] mapT = new MapNode[layout.length][layout[0].length];
        for(int i=0;i<layout.length;i++){
            for(int j=0;j<layout[i].length;j++){
                switch(layout[i][j]){
                    case(0):{
                        int[] tmp = {i,j};
                        mapT[i][j] = new MapNode(tmp,"campo");
                        break;
                    }
                    case(1):{
                        int[] tmp = {i,j};
                        mapT[i][j] = new MapNode(tmp,"parede");
                        break;
                    }
                    case(2):{
                        int[] tmp = {i,j};
                        mapT[i][j] = new MapNode(tmp,"obs");
                        break;
                    }
                }
            }
        }
        for(int i=0;i<layout.length;i++){
            for(int j=0;j<layout[i].length;j++){
                int[] tmp = {i,j};
                setUpNode(mapT,mapT[i][j],tmp);
            }
        }
        return(mapT);
    }
    
    private void setUpNode(MapNode[][] map,MapNode node,int[] pos){
        int maxY=(map[0].length-1),maxX=(map.length-1);
        switch(node.getTipo()){
            case("obs"):{
            }
            case("campo"):{
                //vizinho de cima
                if(pos[0]>0){
                    if(!map[pos[0]-1][pos[1]].getTipo().equals("parede")){
                        node.setUp(map[pos[0]-1][pos[1]]);
                        if(map[pos[0]-1][pos[1]].getTipo().equals("campo")){
                            node.setCostUp(campoValor);
                        }else{
                            node.setCostUp(obsValor);
                        }
                    }else{
                        node.setUp(null);
                    }
                }else{
                    node.setUp(null);
                }
                //vizinho de baixo
                if(pos[0]<maxY){
                    if(!map[pos[0]+1][pos[1]].getTipo().equals("parede")){
                        node.setDown(map[pos[0]+1][pos[1]]);
                        if(map[pos[0]+1][pos[1]].getTipo().equals("campo")){
                            node.setCostDown(campoValor);
                        }else{
                            node.setCostDown(obsValor);
                        }
                    }else{
                        node.setDown(null);
                    }
                }else{
                    node.setDown(null);
                }
                //vizinho da esquerda
                if(pos[1]>0){
                    if(!map[pos[0]][pos[1]-1].getTipo().equals("parede")){
                        node.setLeft(map[pos[0]][pos[1]-1]);
                        if(map[pos[0]][pos[1]-1].getTipo().equals("campo")){
                            node.setCostLeft(campoValor);
                        }else{
                            node.setCostLeft(obsValor);
                        }
                    }else{
                        node.setLeft(null);
                    }
                }else{
                    node.setLeft(null);
                }
                //vizinho da direita
                if(pos[1]<maxX){
                    if(!map[pos[0]][pos[1]+1].getTipo().equals("parede")){
                        node.setRight(map[pos[0]][pos[1]+1]);
                        if(map[pos[0]][pos[1]+1].getTipo().equals("campo")){
                            node.setCostRight(campoValor);
                        }else{
                            node.setCostRight(obsValor);
                        }
                    }else{
                        node.setRight(null);
                    }
                }else{
                    node.setRight(null);
                }
                break;
            }
            case("parede"):{
                node.setUp(null);
                node.setDown(null);
                node.setLeft(null);
                node.setRight(null);
                break;
            }
        }
    }
    
    /*
        Getters Setters
    */
    public int[][] getLayout() {
        return layout;
    }

    public void setLayout(int[][] layout) {
        this.layout = layout;
    }
    /*
        End Getters Setters
    */
    
    public static void main(String[] args){
        GenMap obj = new GenMap();
        int[][] layout = new int[][]{{1,0,1,0,1,0,1,0},{0,2,0,2,0,2,0,2},{0,2,0,2,0,2,0,2},{0,2,0,2,0,2,0,2},{0,2,0,2,0,2,0,2},{0,2,0,2,0,2,0,2},{0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1}};
        obj.init(layout);
    }
}
