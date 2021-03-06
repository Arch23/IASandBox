/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import iasandbox.TicTacToe.AlfaBetaTree.TreeAlfaBeta;
import iasandbox.TicTacToe.MiniMaxTree.TreeMiniMax;
import iasandbox.FXML.GameStatsController;
import iasandbox.FXML.MainController;
import iasandbox.FXML.PathfindingConfController;
import iasandbox.FXML.PlayersController;
import iasandbox.FXML.StatsPathfindingController;
import iasandbox.FXML.StatsTicTacToeController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author noda2
 */
public final class ControleUI {

    private static ControleUI instance = null;
    private Stage select;
    private Stage mainStage;
    private Stage playersStage;
    private Stage statsStage;
    private Stage methodStage;
    private Stage statsTStage;

    private Parent selectParent;
    private Parent mainStageParent;
    private Parent playersParent;
    private Parent statsParent;
    private Parent gameStatsParent;
    private Parent methodParent;
    private Parent pathStatsParent;
    private Parent statsTParent;

    private Scene selectScene;
    private Scene mainStageScene;
    private Scene playersScene;
    private Scene statsScene;
    private Scene gameStatsScene;
    private Scene methodScene;
    private Scene pathStatsScene;
    private Scene statsTScene;

    private MainController mainController;
    private PlayersController playersController;
    private StatsTicTacToeController statsController;
    private GameStatsController gameStatsController;
    private PathfindingConfController methodController;
    private StatsPathfindingController pathStatsController;
    private StatsTicTacToeController statsTController;

    private FXMLLoader mainLoader;
    private FXMLLoader playersLoader;
    private FXMLLoader statsLoader;
    private FXMLLoader gameStatsLoader;
    private FXMLLoader pathStatsLoader;
    private FXMLLoader methodLoader;
    private FXMLLoader statsTLoader;

    private ResizableCanvas canvas;

    private int game = 0;
    private int metod = 0;

    private int player1 = 0;
    private int player2 = 0;

    //variáveis para o benchmark
    private int numberOfSteps = 0;
    private int numberOfNodesVisited = 0;
    private long tempoDecisao = 0;

    private ControleUI() {
    }

    public static ControleUI getInstance() {
        return ((instance == null) ? instance = new ControleUI() : instance);
    }

    public void start(Stage primaryStage) {
        //init stages
        select = new Stage();
        mainStage = new Stage();
        playersStage = new Stage();
        statsStage = new Stage();
        methodStage = new Stage();
        statsTStage = new Stage();
        
        select.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        mainStage.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        playersStage.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        methodStage.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        statsTStage.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        statsStage.getIcons().add((new Image(getClass().getResourceAsStream("/iasandbox/img/logo.png"))));
        
        select.setResizable(false);
        playersStage.setResizable(false);
        methodStage.setResizable(false);
        statsTStage.setResizable(false);
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

    public void mostraMain() {
        try {
            if (game == 0 && ((player1 == 1) || (player2 == 1))) {
                //gera a árvore
                TreeMiniMax.getInstance();
            }
            if ((game == 0) && ((player1 == 2) || (player2 == 2))) {
                TreeAlfaBeta.getInstance();
            }
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
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostraPlayers() {
        try {
            playersLoader = new FXMLLoader(getClass().getResource("FXML/Players.fxml"));
            playersParent = playersLoader.load();
            playersScene = new Scene(playersParent);
            playersController = playersLoader.getController();
            playersStage.setScene(playersScene);
            playersStage.setTitle("AI SandBox");
            playersStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostraMethods() {
        try {
            methodLoader = new FXMLLoader(getClass().getResource("FXML/PathfindingConf.fxml"));
            methodParent = methodLoader.load();
            methodScene = new Scene(methodParent);
            methodController = methodLoader.getController();
            methodStage.setScene(methodScene);
            methodStage.setTitle("AI SandBox");
            methodStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostraStats() {
        try {
            statsTLoader = new FXMLLoader(getClass().getResource("FXML/StatsTicTacToe.fxml"));
            statsTParent = statsTLoader.load();
            statsTScene = new Scene(statsTParent);
            statsTController = statsTLoader.getController();
            statsTStage.setScene(statsTScene);
            statsTStage.setTitle("AI SandBox");
            statsTStage.setMinHeight(525);
            statsTStage.setMinWidth(600);
            statsTStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostraGameStats() {
        try {
            gameStatsLoader = new FXMLLoader(getClass().getResource("FXML/GameStats.fxml"));
            gameStatsParent = gameStatsLoader.load();
            gameStatsScene = new Scene(gameStatsParent);
            gameStatsController = gameStatsLoader.getController();
            statsStage.setScene(gameStatsScene);
            statsStage.setTitle("AI SandBox");
            statsStage.show();
            statsStage.setMinHeight(650);
            statsStage.setMinWidth(1250);
            statsStage.setResizable(true);
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostraPathfindingStats() {
        try {
            pathStatsLoader = new FXMLLoader(getClass().getResource("FXML/StatsPathfinding.fxml"));
            pathStatsParent = pathStatsLoader.load();
            pathStatsScene = new Scene(pathStatsParent);
            pathStatsController = pathStatsLoader.getController();
            statsStage.setScene(pathStatsScene);
            statsStage.setTitle("AI SandBox");
            statsStage.show();
            statsStage.setMinHeight(550);
            statsStage.setMinWidth(1200);
            statsStage.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(ControleUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MainController getMainController() {
        return mainController;
    }

    public PlayersController getPlayersController() {
        return playersController;
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

    public StatsTicTacToeController getStatsController() {
        return statsController;
    }

    public GameStatsController getGameStatsController() {
        return gameStatsController;
    }

    public void setGameStatsController(GameStatsController gameStatsController) {
        this.gameStatsController = gameStatsController;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getNumberOfNodesVisited() {
        return numberOfNodesVisited;
    }

    public void setNumberOfNodesVisited(int numberOfNodesVisited) {
        this.numberOfNodesVisited = numberOfNodesVisited;
    }

    public long getTempoDecisao() {
        return tempoDecisao;
    }

    public void setTempoDecisao(long tempoDecisao) {
        this.tempoDecisao = tempoDecisao;
    }

    public StatsPathfindingController getPathStatsController() {
        return pathStatsController;
    }

    public Stage getMethodStage() {
        return methodStage;
    }

    public Stage getStatsStage() {
        return statsStage;
    }

    public Stage getStatsTStage() {
        return statsTStage;
    }
    

}
