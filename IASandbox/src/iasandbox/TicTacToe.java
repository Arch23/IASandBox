/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import iasandbox.TicTacToePackage.TicTacToeHuman;
import iasandbox.TicTacToePackage.TicTacToeMiniMax;
import iasandbox.TicTacToePackage.TicTacToePoda;
import iasandbox.TicTacToePackage.ticTacToePlayer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author noda2
 */
public class TicTacToe {
    
    private static TicTacToe instance = null;
    private int[][] map;
    private int n = 3;
    private int movesMade=0;
    
    private ticTacToePlayer player1=null;
    private ticTacToePlayer player2=null;
    
    private EventHandler<MouseEvent> playerClicked1=null;
    private EventHandler<MouseEvent> playerClicked2=null;
    
    
    
    private TicTacToe(){}
    
    public static TicTacToe getInstance(){return(instance==null?instance=new TicTacToe():instance);}
    
    public void startGame(){
        movesMade=0;
        player1=null;
        player2=null;
        playerClicked1=null;
        playerClicked2=null;
        clearMap();
        setPlayers();
        ControleUI.getInstance().getMainController().setVarTicTacToe();
        System.out.println("NEW GAME");
    }
    
    public boolean player1Move(){
        if(!player1.getClass().equals(TicTacToeHuman.class)){
            int[] mov = player1.logic();
            return(makeMove(mov,1));
        }else{
            playerClicked1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((TicTacToeHuman)player1).translateClick(event);
                }
            };
            if(playerClicked2!=null){
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked2);
            }
            ControleUI.getInstance().getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked1);
            return(true);
        }
    }
    
    public boolean player2Move(){
        if(!player2.getClass().equals(TicTacToeHuman.class)){
            int[] mov = player2.logic();
            return(makeMove(mov,2));
        }else{
            playerClicked2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((TicTacToeHuman)player2).translateClick(event);
                }
            };
            if(playerClicked1!=null){
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked1);
            }
            ControleUI.getInstance().getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked2);
            return(true);
        }
    }
    
    public boolean makeMove(int[] cord,int player){
        if(map[cord[0]][cord[1]]==0){
            map[cord[0]][cord[1]] = player;
            movesMade++;
            return (false);
        } else {
            return (true);
        }
    }
    
    public int checkBoard(){
        if((map[0][0]==1) && (map[0][1]==1) && (map[0][2]==1)){
            map[0][0]=3;map[0][1]=3;map[0][2]=3;
            return(1);
        }else if((map[1][0]==1) && (map[1][1]==1) && (map[1][2]==1)){
            map[1][0]=3;map[1][1]=3;map[1][2]=3;
            return(1);
        }else if((map[2][0]==1) && (map[2][1]==1) && (map[2][2]==1)){
            map[2][0]=3;map[2][1]=3;map[2][2]=3;
            return(1);
        }else if((map[0][0]==1) && (map[1][0]==1) && (map[2][0]==1)){
            map[0][0]=3;map[1][0]=3;map[2][0]=3;
            return(1);
        }else if((map[0][1]==1) && (map[1][1]==1) && (map[2][1]==1)){
            map[0][1]=3;map[1][1]=3;map[2][1]=3;
            return(1);
        }else if((map[0][2]==1) && (map[1][2]==1) && (map[2][2]==1)){
            map[0][2]=3;map[1][2]=3;map[2][2]=3;
            return(1);
        }else if((map[0][0]==1) && (map[1][1]==1) && (map[2][2]==1)){
            map[0][0]=3;map[1][1]=3;map[2][2]=3;
            return(1);
        }else if((map[0][2]==1) && (map[1][1]==1) && (map[2][0]==1)){
            map[0][2]=3;map[1][1]=3;map[2][0]=3;
            return(1);
        }else if((map[0][0]==2) && (map[0][1]==2) && (map[0][2]==2)){
            map[0][0]=4;map[0][1]=4;map[0][2]=4;
            return(2);
        }else if((map[1][0]==2) && (map[1][1]==2) && (map[1][2]==2)){
            map[1][0]=4;map[1][1]=4;map[1][2]=4;
            return(2);
        }else if((map[2][0]==2) && (map[2][1]==2) && (map[2][2]==2)){
            map[2][0]=4;map[2][1]=4;map[2][2]=4;
            return(2);
        }else if((map[0][0]==2) && (map[1][0]==2) && (map[2][0]==2)){
            map[0][0]=4;map[1][0]=4;map[2][0]=4;
            return(2);
        }else if((map[0][1]==2) && (map[1][1]==2) && (map[2][1]==2)){
            map[0][1]=4;map[1][1]=4;map[2][1]=4;
            return(2);
        }else if((map[0][2]==2) && (map[1][2]==2) && (map[2][2]==2)){
            map[0][2]=4;map[1][2]=4;map[2][2]=4;
            return(2);
        }else if((map[0][0]==2) && (map[1][1]==2) && (map[2][2]==2)){
            map[0][0]=4;map[1][1]=4;map[2][2]=4;
            return(2);
        }else if((map[0][2]==2) && (map[1][1]==2) && (map[2][0]==2)){
            map[0][2]=4;map[1][1]=4;map[2][0]=4;
            return(2);
        }
        if(movesMade==9){
            return(-1);
        }else{
            return(0);
        }
    }
    
    private int[][] clearMap(){
        map = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j] = 0;
            }
        }
        return(map);
    }

    private void setPlayers(){
        switch(ControleUI.getInstance().getPlayer1()){
            case(0):{
                player1 = new TicTacToeHuman(1);
                break;
            }
            case(1):{
                player1 = new TicTacToeMiniMax();
                break;
            }
            case(2):{
                player1 = new TicTacToePoda();
                break;
            }
        }
        switch(ControleUI.getInstance().getPlayer2()){
            case(0):{
                player2 = new TicTacToeHuman(2);
                break;
            }
            case(1):{
                player2 = new TicTacToeMiniMax();
                break;
            }
            case(2):{
                player2 = new TicTacToePoda();
                break;
            }
        }
    }
    
    public int[][] getMap() {
        return((map==null)?clearMap():map);
    }

    public EventHandler<MouseEvent> getPlayerClicked1() {
        return playerClicked1;
    }

    public EventHandler<MouseEvent> getPlayerClicked2() {
        return playerClicked2;
    }
    
    
    public void deleteInstance(){
        instance=null;
    }
}
