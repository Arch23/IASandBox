/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;

import iasandbox.ControleUI;
import iasandbox.TicTacToe;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author noda2
 */
public class TicTacToeHuman implements ticTacToePlayer{
    
    private int[] cord = new int[2];
    private int playerNumber=0;
    
    public TicTacToeHuman(int number){
        playerNumber=number;
        setUpPlayer();
    }
    
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
        double hMap = ((ControleUI.getInstance().getMainStage().getHeight()*0.6)-22);
        double wMap = hMap;
        
        double hCel = (hMap/3);
        double wCel = (wMap/3);
        
        double h1 = ((ControleUI.getInstance().getMainStage().getHeight()-hMap)/2);
        double w1 = ((ControleUI.getInstance().getMainStage().getWidth()-wMap)/2);
        double h2=h1;
        double w2=w1;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(ControleUI.getInstance().getMainController().getCels()[i][j].contains(click.getSceneX(),click.getSceneY())){
                    cord[0]=i;
                    cord[1]=j;
                }
            }
        }

        if(cord[0]!=-1 && cord[1]!=-1){
            TicTacToe.getInstance().makeMove(logic(),playerNumber);
            ControleUI.getInstance().getMainController().setMoveMade(true);
            if(playerNumber==1){
                ControleUI.getInstance().getMainController().setPlayer1Turn(false);
                ControleUI.getInstance().getMainController().setPlayer2Turn(true);
            }else{
                ControleUI.getInstance().getMainController().setPlayer2Turn(false);
                ControleUI.getInstance().getMainController().setPlayer1Turn(true);
            }
            ControleUI.getInstance().getMainController().ticTacToe();
        }
    }
}
