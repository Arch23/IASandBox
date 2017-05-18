/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;
import iasandbox.TicTacToe;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author noda2
 */
public class TicTacToeHuman implements ticTacToePlayer{
    
    /*
        Var
    */
    private int[] cord = new int[2];
    private int playerNumber=0;
    /*
        End var
    */
    
    /*
        constructor
    */
    public TicTacToeHuman(int number){
        playerNumber=number;
        setUpPlayer();
    }
    /*
        End constructor
    */
    
    /*
        Methods
    */
    
    //Método para mandar a resposta de volta
    @Override
    public int[] logic() {
        return(cord);
    }
    
    public void setUpPlayer(){
        cord[0] = -1;
        cord[1] = -1;
    }

    public void translateClick(MouseEvent click) {
        cord[0]=-1;
        cord[1]=-1;
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(TicTacToe.getInstance().getCels()[i][j].contains(click.getSceneX(),click.getSceneY())){
                    cord[0]=i;
                    cord[1]=j;
                }
            }
        }
        
        if(cord[0]!=-1 && cord[1]!=-1){
            if(TicTacToe.getInstance().getMap()[cord[0]][cord[1]]==0){
                TicTacToe.getInstance().makeMove(logic(),playerNumber);
                TicTacToe.getInstance().setMoveMade(true);
                if(playerNumber==1){
                    TicTacToe.getInstance().setPlayer1Turn(false);
                    TicTacToe.getInstance().setPlayer2Turn(true);
                }else{
                    TicTacToe.getInstance().setPlayer2Turn(false);
                    TicTacToe.getInstance().setPlayer1Turn(true);
                }
                TicTacToe.getInstance().ticTacToe();
            }
        }
    }
    
    /*
        End Methods
    */
}
