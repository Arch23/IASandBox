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
import javafx.scene.control.Slider;

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
    @FXML
    private Slider Dificuldade;
    @FXML
    private Slider Dificuldade2;

    private String[] playerOptions = {"Human", "MiniMax", "Poda Alfa-Beta", "SBR"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initChoiceBox();
    }

    @FXML
    private void saveOptions() {
        if (choicePlayer1.getValue().equals(playerOptions[0])) {
            ControleUI.getInstance().setPlayer1(0);
        } else if (choicePlayer1.getValue().equals(playerOptions[1])) {
            ControleUI.getInstance().setPlayer1(1);
        } else if (choicePlayer1.getValue().equals(playerOptions[3])) {
            ControleUI.getInstance().setPlayer1(3);
        } else {
            ControleUI.getInstance().setPlayer1(2);
        }
        if (choicePlayer2.getValue().equals(playerOptions[0])) {
            ControleUI.getInstance().setPlayer2(0);
        } else if (choicePlayer2.getValue().equals(playerOptions[1])) {
            ControleUI.getInstance().setPlayer2(1);
        } else if (choicePlayer2.getValue().equals(playerOptions[3])) {
            ControleUI.getInstance().setPlayer2(3);
        } else {
            ControleUI.getInstance().setPlayer2(2);
        }

        ControleUI.getInstance()
                .mostraMain();
        ControleUI.getInstance()
                .getPlayersStage().hide();
    }
    @FXML
    private void mostraStats(){        
        ControleUI.getInstance().mostraStats();
    }

    private void initChoiceBox() {
        choicePlayer1.setItems(FXCollections.observableArrayList(playerOptions));
        choicePlayer2.setItems(FXCollections.observableArrayList(playerOptions));
        choicePlayer1.setValue(playerOptions[0]);
        choicePlayer2.setValue(playerOptions[0]);
        choicePlayer1.valueProperty().addListener(listener -> adjustSlider(Dificuldade, choicePlayer1));
        choicePlayer2.valueProperty().addListener(listener -> adjustSlider(Dificuldade2, choicePlayer2));
        Dificuldade.setDisable(true);
        Dificuldade2.setDisable(true);
    }

    private void adjustSlider(Slider player, ChoiceBox playerbox) {
        if (playerbox.getValue().equals("MiniMax")) {
            player.setDisable(false);
        } else {
            player.setDisable(true);
        }
    }

    public Slider getDificuldade() {
        return Dificuldade;
    }

    public Slider getDificuldade2() {
        return Dificuldade2;
    }

}
