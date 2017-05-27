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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author noda2
 */
public class SelectController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ChoiceBox<String> choiceGame;
    
    private String[] game={"Tic Tac Toe","Pathfinding"};
    
    private ObservableList<String> gameItems;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initChoiceBox();
    }

    @FXML
    private void start(){
        if(choiceGame.getValue().equals(game[0])){
            ControleUI.getInstance().setGame(0);
            ControleUI.getInstance().mostraPlayers();
        }else{
            ControleUI.getInstance().setGame(1);
            
            ControleUI.getInstance().mostraMethods();
        }
        ControleUI.getInstance().getSelect().hide();
    }
    
    private void initChoiceBox(){
        //inicializando os observablos list
        gameItems = FXCollections.observableArrayList(game);
        
        //inicializando choicebox
        choiceGame.setItems(gameItems);
        choiceGame.setValue(gameItems.get(0));
    }
}
