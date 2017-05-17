/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import iasandbox.TicTacToePackage.TicTacToeHuman;
import iasandbox.TicTacToePackage.TicTacToeMiniMax;
import iasandbox.TicTacToePackage.TicTacToePoda;
import iasandbox.TicTacToePackage.TicTacToeRules;
import iasandbox.TicTacToePackage.ticTacToePlayer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author noda2
 */
public class TicTacToe {
    
    private static TicTacToe instance = null;
    private int[][] mapGame;
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
        mapGame = clearMap(mapGame);
        setPlayers();
        ControleUI.getInstance().getMainController().setVarTicTacToe();
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
//        if(map[cord[0]][cord[1]]==0){
//            map[cord[0]][cord[1]] = player;
//            movesMade++;
//            return (false);
//        } else {
//            return (true);
//        }
        mapGame[cord[0]][cord[1]] = player;
        movesMade++;
        return(false);
    }
    
    public int checkBoard(int[][] map,int moves){
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
        if(moves==9){
            return(0);
        }else{
            return(-1);
        }
    }
    
    private int[][] clearMap(int[][] map){
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
                player1 = new TicTacToeMiniMax(1,ControleUI.getInstance().getPlayersController().getDificuldade()
                .getValue()/10);
                break;
            }
            case(2):{
                player1 = new TicTacToePoda();
                break;
            }
            case(3):{
                player1=new TicTacToeRules(1);
                break;
            }
        }
        switch(ControleUI.getInstance().getPlayer2()){
            case(0):{
                player2 = new TicTacToeHuman(2);
                break;
            }
            case(1):{
                player2 = new TicTacToeMiniMax(2,ControleUI.getInstance().getPlayersController().getDificuldade2()
                .getValue()/10);
                break;
            }
            case(2):{
                player2 = new TicTacToePoda();
                break;
            }
            case(3):{
                player2= new TicTacToeRules(2);
                break;
            }
        }
    }

    public int getMovesMade() {
        return movesMade;
    }
    
    public int[][] getMap() {
        return((mapGame==null)?clearMap(mapGame):mapGame);
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
