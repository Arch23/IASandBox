/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import iasandbox.ControleUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author noda2
 */
public class PlayersController implements Initializable {
    
    @FXML
    private ChoiceBox<String> choicePlayer1;
    @FXML
    private ChoiceBox<String> choicePlayer2;
    @FXML
    private Button start;
    
    private String[] playerOptions={"Human","MiniMax","Poda Alfa-Beta"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initChoiceBox();
    }    
    
    @FXML
    private void saveOptions(){
        if(choicePlayer1.getValue().equals(playerOptions[0])){
            ControleUI.getInstance().setPlayer1(0);
        }else if(choicePlayer1.getValue().equals(playerOptions[1])){
            ControleUI.getInstance().setPlayer1(1);
        }else{
            ControleUI.getInstance().setPlayer1(2);
        }
        if(choicePlayer2.getValue().equals(playerOptions[0])){
            ControleUI.getInstance().setPlayer2(0);
        }else if(choicePlayer2.getValue().equals(playerOptions[1])){
            ControleUI.getInstance().setPlayer2(1);
        }else{
            ControleUI.getInstance().setPlayer2(2);
        }
        ControleUI.getInstance().mostraMain();
        ControleUI.getInstance().getPlayersStage().hide();
    }
    
    private void initChoiceBox(){
        choicePlayer1.setItems(FXCollections.observableArrayList(playerOptions));
        choicePlayer2.setItems(FXCollections.observableArrayList(playerOptions));
        choicePlayer1.setValue(playerOptions[0]);
        choicePlayer2.setValue(playerOptions[0]);
    }
}
