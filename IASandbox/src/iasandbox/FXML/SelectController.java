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
    @FXML
    private ChoiceBox<String> choiceMetod;
    @FXML
    private Pane metodPane;
    
    private String[] game={"Tic Tac Toe","Pathfinding"},
            metodGame2={"A*","Breadth-First","Depth-First Search"};
    
    private ObservableList<String> gameItems;
    private ObservableList<String> game2Items;
    
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
            if(choiceMetod.getValue().equals(metodGame2[0])){
                ControleUI.getInstance().setMetod(0);
            }else if(choiceMetod.getValue().equals(metodGame2[1])){
                ControleUI.getInstance().setMetod(1);
            }else if(choiceMetod.getValue().equals(metodGame2[2])){
                ControleUI.getInstance().setMetod(2);
            }
            ControleUI.getInstance().mostraMain();
        }
        ControleUI.getInstance().getSelect().hide();
    }
    
    private void initChoiceBox(){
        //inicializando os observablos list
        gameItems = FXCollections.observableArrayList(game);
        game2Items = FXCollections.observableArrayList(metodGame2);
        
        //inicializando choicebox
        choiceGame.setItems(gameItems);
        choiceGame.setValue(gameItems.get(0));
        choiceGame.valueProperty().addListener(listener->changeMetod());
        metodPane.setDisable(true);
    }
    
    //método para setar os métodos disponíveis para cada game
    private void changeMetod(){
        if(choiceGame.getValue().equals(game[0])){
            metodPane.setDisable(true);
        }else{
            metodPane.setDisable(false);
            choiceMetod.setItems(game2Items);
            choiceMetod.setValue(game2Items.get(0));
        }
    }
}
