/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import iasandbox.ControleUI;
import iasandbox.PathfindingLogic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author noda2
 */
public class PathfindingConfController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceMetod;
    @FXML
    private Slider size;
    
    private String[] methods = {"A*", "Breadth-First", "Depth-First Search"};

    private ObservableList<String> methodsC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initChoiceBox();
    }

    @FXML
    private void start() {
        if (choiceMetod.getValue().equals(methods[0])) {
            ControleUI.getInstance().setMetod(0);
        } else if (choiceMetod.getValue().equals(methods[1])) {
            ControleUI.getInstance().setMetod(1);
        } else if (choiceMetod.getValue().equals(methods[2])) {
            ControleUI.getInstance().setMetod(2);
        }
        PathfindingLogic.getInstance().setSize((int) Math.round(size.getValue()));
        ControleUI.getInstance().mostraMain();
        ControleUI.getInstance().getPlayersStage().hide();
    }

    private void initChoiceBox() {

        methodsC = FXCollections.observableArrayList(methods);
        choiceMetod.setItems(methodsC);
        choiceMetod.setValue(methodsC.get(0));
        size.valueProperty().addListener(listener->limiter());
        choiceMetod.valueProperty().addListener(listener->limiter());
    }
    
    private void limiter(){
        if(choiceMetod.getValue().equals("Breadth-First")){
            if(((int) Math.round(size.getValue()))>8){
                size.setValue(8);
            }
        }
    }
}
