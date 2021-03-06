/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import statistics.IOStream.Writer;
import iasandbox.ControleUI;
import iasandbox.PathfindingLogic;
import iasandbox.ResizableCanvas;
import iasandbox.TicTacToeLogic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author noda2
 */
public class MainController implements Initializable {

    /*
        Var FXML
     */
    @FXML
    private MenuBar menuBar;
    @FXML
    private Pane canvasPane;
    @FXML
    private MenuItem novoGame;
    @FXML
    private MenuItem changeGame;
    @FXML
    private MenuItem mudarPlayers;
    @FXML
    private StackPane stack;

    private ResizableCanvas canvas;
    private GraphicsContext GC;

    /*
        End var FXML
     */
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCanvas();
        bindingElements();
        if (ControleUI.getInstance().getGame()==1) {
            mudarPlayers.setText("Mudar Métodos");
        } else {
            mudarPlayers.setText("Mudar Player");
        }
    }

    /*
        FXML Methods
     */
    @FXML
    private void mudarPlayers() {
        ControleUI.getInstance().getMainStage().hide();
        if (ControleUI.getInstance().getGame() == 1) {
            ControleUI.getInstance().mostraMethods();
        } else {
            ControleUI.getInstance().mostraPlayers();
        }
    }

    @FXML
    public void newGame() {
        switch (ControleUI.getInstance().getGame()) {
            case (0): {
                TicTacToeLogic.getInstance().endGame();
                TicTacToeLogic.getInstance().deleteInstance();
                TicTacToeLogic.getInstance().setVarTicTacToe();
                TicTacToeLogic.getInstance().startGame();
                TicTacToeLogic.getInstance().ticTacToe();
                break;
            }
            case (1): {
                PathfindingLogic.getInstance().endGame();
                PathfindingLogic.getInstance().pathFinding();
                break;
            }
        }
    }

    @FXML
    private void changeGame() {
        if (ControleUI.getInstance().getGame() == 0) {
            TicTacToeLogic.getInstance().endGame();
        } else {
            PathfindingLogic.getInstance().endGame();
        }
        ControleUI.getInstance().getMainStage().hide();
        ControleUI.getInstance().mostraSelect();
    }

    @FXML
    private void spaceRestart(KeyEvent key) {
        if (key.getCode() == KeyCode.SPACE) {
            newGame();
        }
    }
    
    @FXML
    private void statsPathfinding(){
        ControleUI.getInstance().mostraPathfindingStats();
    }
    @FXML
    private void statsTicTacToe(){
        ControleUI.getInstance().mostraGameStats();
    }

    /*
        End FXML Methods
     */
 /*
        Methods
     */
    //Liga os tamanhos dos nós de dentro com os pais
    private void bindingElements() {
        canvasPane.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());
        canvasPane.prefHeightProperty().bind(ControleUI.getInstance().getMainStage().heightProperty());

        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());

        stack.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());
        stack.prefHeightProperty().bind(ControleUI.getInstance().getMainStage().heightProperty());

        stack.addEventHandler(KeyEvent.KEY_PRESSED, (key -> spaceRestart(key)));

        menuBar.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());

        canvasPane.widthProperty().addListener(e -> reDraw());
        canvasPane.heightProperty().addListener(e -> reDraw());
    }

    //Inicializa o ResizableCanvas
    private void initCanvas() {
        canvas = null;
        canvas = new ResizableCanvas();
        canvasPane.getChildren().add(canvas);
        ControleUI.getInstance().setCanvas(canvas);

        GC = canvas.getGraphicsContext2D();
        GC.setFill(Color.ROYALBLUE);
        GC.setStroke(Color.WHITE);
        GC.setLineWidth(2);
    }

    //Redesenha a situação atual
    public void reDraw() {
        switch (ControleUI.getInstance().getGame()) {
            case (0): {
                TicTacToeLogic.getInstance().calcTicTacToe();
                try {
                    TicTacToeLogic.getInstance().drawStaticTicTacToe();
                } catch (Exception e) {
                }
                TicTacToeLogic.getInstance().drawPlayerMovesTicTacToe();
                break;
            }
            case (1): {
                try{
                PathfindingLogic.getInstance().calcPathFinding();
                PathfindingLogic.getInstance().drawStaticPathFinding();
                }
                catch(Exception e){
                    
                }
                PathfindingLogic.getInstance().drawPlayerMovesPathFinding();
                break;
            }
        }
    }

    /*
        End methods
     */
 /*
        Getter e Setter
     */
    public Pane getCanvasPane() {
        return canvasPane;
    }

    public GraphicsContext getGC() {
        return GC;
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }

    /*
        End Getter e Setter
     */
}
