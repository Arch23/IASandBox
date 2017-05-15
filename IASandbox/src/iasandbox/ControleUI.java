/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import iasandbox.FXML.MainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.sound.midi.ControllerEventListener;


/**
 *
 * @author noda2
 */
public final class ControleUI{

    private static ControleUI instance = null;
    private Stage select;
    private Stage mainStage;
    private Stage playersStage;
    
    private Parent selectParent;
    private Parent mainStageParent;
    private Parent playersParent;
    
    private Scene selectScene;
    private Scene mainStageScene;
    private Scene playersScene;
    
    private MainController mainController;
    private FXMLLoader mainLoader;
    
    private ResizableCanvas canvas;
    
    private int game=0;
    private int metod=0;
    
    private int player1=0;
    private int player2=0;
    
    private ControleUI() {}
    
    public static ControleUI getInstance(){return((instance==null)?instance=new ControleUI():instance);}
    
    public void start(Stage primaryStage){
        //init stages
        select = new Stage();
        mainStage = new Stage();
        playersStage = new Stage();
        
        mostraSelect();
    }

    public void mostraSelect() {
        try {
            selectParent = FXMLLoader.load(getClass().getResource("FXML/Select.fxml"));
            selectScene = new Scene(selectParent);
            select.setScene(selectScene);
            select.setTitle("AI SandBox");
            select.setOnCloseRequest((WindowEvent t) -> {
                if (!mainStage.isShowing()) {
                    Platform.exit();
                    System.exit(0);
                }
            } //Metodo para finalizar tudo ao apertar o "x"
            );
            select.show();
        } catch (IOException ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraMain(){
        try{
            mainLoader = new FXMLLoader(getClass().getResource("FXML/Main.fxml"));
            mainStageParent = mainLoader.load();
            mainStageScene = new Scene(mainStageParent);
            mainStage.setScene(mainStageScene);
            mainStage.setTitle("AI SandBox");
            mainController = mainLoader.getController();
            mainStage.setOnCloseRequest((WindowEvent t) -> {
                Platform.exit();
                System.exit(0);
            } //Metodo para finalizar tudo ao apertar o "x"
            );
            mainStage.show();
            mainController.newGame();
        }catch(Exception ex){
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraPlayers(){
        try{
            playersParent = FXMLLoader.load(getClass().getResource("FXML/Players.fxml"));
            playersScene = new Scene(playersParent);
            playersStage.setScene(playersScene);
            playersStage.setTitle("AI SandBox");
            playersStage.show();
        }catch(Exception ex){
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MainController getMainController() {
        return mainController;
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(ResizableCanvas canvas) {
        this.canvas = canvas;
    }

    public Stage getSelect() {
        return select;
    }

    public Stage getPlayersStage() {
        return playersStage;
    }
    
    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }
    
    public Stage getMainStage() {
        return mainStage;
    }
    
    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getMetod() {
        return metod;
    }

    public void setMetod(int metod) {
        this.metod = metod;
    }
}
