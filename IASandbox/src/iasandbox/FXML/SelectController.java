/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import iasandbox.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private MenuBar menu;
    @FXML
    private ImageView icon;
    
    private String[] game={"Tic Tac Toe","Pathfinding"};
    
    private ObservableList<String> gameItems;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menu.prefWidthProperty().bind(ControleUI.getInstance().getSelect().widthProperty());
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
    @FXML
    private void statsPathfinding(){
        ControleUI.getInstance().mostraPathfindingStats();
    }
    @FXML
    private void statsTicTacToe(){
        ControleUI.getInstance().mostraGameStats();
    }
    private void initChoiceBox(){
        //inicializando os observablos list
        gameItems = FXCollections.observableArrayList(game);
        
        //inicializando choicebox
        choiceGame.setItems(gameItems);
        choiceGame.setValue(gameItems.get(0));
        icon.setImage(new Image(getClass().getResourceAsStream("/iasandbox/img/tic.png")));
        choiceGame.valueProperty().addListener(listener->changeIcon());
    }

    private void changeIcon() {
        switch(choiceGame.getValue()){
            case("Tic Tac Toe"):{
                icon.setImage(new Image(getClass()
        .getResourceAsStream("/iasandbox/img/tic.png")));
                break;
            }
            case("Pathfinding"):{
                icon.setImage(new Image(getClass()
        .getResourceAsStream("/iasandbox/img/map2.png")));
                break;
            }
        }
    }
}
