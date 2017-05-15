/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import iasandbox.ControleUI;
import iasandbox.ResizableCanvas;
import iasandbox.TicTacToe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author noda2
 */
public class MainController implements Initializable {
    
    @FXML
    private MenuBar menuBar;
    @FXML
    private Pane canvasPane;
    @FXML
    private MenuItem novoGame;
    @FXML
    private MenuItem changeGame;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem configurações;
    @FXML
    private StackPane stack;
    
    private ResizableCanvas canvas;
    private GraphicsContext GC;
    private boolean player1Turn;
    private boolean player2Turn;
    private boolean moveMade;
    private boolean gameRunning;
    private String text;
    
    private Rectangle[][] cels;
    
    private double h1,
            h2,
            w1,
            w2,
            wMap,
            hMap,
            wCel,
            hCel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCanvas();
        bindingElements();
        
        canvasPane.widthProperty().addListener(e->reDraw());
        canvasPane.heightProperty().addListener(e->reDraw());
    }
    
    @FXML
    public void newGame(){
        switch(ControleUI.getInstance().getGame()){
            case(0):{
                setVarTicTacToe();
                TicTacToe.getInstance().startGame();
                ticTacToe();
                break;
            }
            case(1):{
                pathFinding();
                break;
            }
        }
    }
    
    @FXML
    private void spaceRestart(KeyEvent key){
        if(key.getCode()==KeyCode.SPACE){
            newGame();
        }
    }
    
    private void bindingElements(){
        canvasPane.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());
        canvasPane.prefHeightProperty().bind(ControleUI.getInstance().getMainStage().heightProperty());
        
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());
        
        stack.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());
        stack.prefHeightProperty().bind(ControleUI.getInstance().getMainStage().heightProperty());
        
        stack.addEventHandler(KeyEvent.KEY_PRESSED, (key->spaceRestart(key)));
        
        menuBar.prefWidthProperty().bind(ControleUI.getInstance().getMainStage().widthProperty());
    }
    
    private void initCanvas(){
        canvas = new ResizableCanvas();
        canvasPane.getChildren().add(canvas);
        ControleUI.getInstance().setCanvas(canvas);
        
        GC = canvas.getGraphicsContext2D();
        GC.setFill(Color.ROYALBLUE);
        GC.setStroke(Color.WHITE);
        GC.setLineWidth(2);
    }
    
    private void reDraw(){
        switch(ControleUI.getInstance().getGame()){
            case(0):{
                calcTicTacToe();
                drawStaticTicTacToe();
                drawPlayerMovesTicTacToe();
                break;
            }
            case(1):{
                calcPathFinding();
                drawStaticPathFinding();
                drawPlayerMovesPathFinding();
                break;
            }
        }
    }
    
    private void pathFinding(){}
    
    private void calcPathFinding(){}
    
    private void drawStaticPathFinding(){}
    
    private void drawPlayerMovesPathFinding(){}
    
    public void ticTacToe(){
        if(gameRunning){
            if(!moveMade){
                if(player1Turn){
                    player1Turn=TicTacToe.getInstance().player1Move();
                    player2Turn=!player1Turn;
                    if(!player1Turn){
                        moveMade=true;
                    }
                }else if(player2Turn){
                    player2Turn=TicTacToe.getInstance().player2Move();
                    player1Turn=!player2Turn;
                    if(!player2Turn){
                        moveMade=true;
                    }
                }
            }
            switch(TicTacToe.getInstance().checkBoard()){
                case(-1):{
                    endGame();
                    text="draw!";
                    break;
                }
                case(1):{
                    endGame();
                    text="player 1 won!";
                    break;
                }
                case(2):{
                    endGame();
                    text="player 2 won!";
                    break;
                }
                case(0):{
                    if(player1Turn){
                        text="player 1";
                    }else if(player2Turn){
                        text="player 2";
                    }
                    break;
                }
            }
            reDraw();
            if(moveMade){
                moveMade=false;
                ticTacToe();
            }
        }
        canvasPane.requestFocus();
    }
    
    public void setVarTicTacToe(){
        gameRunning=true;
        player1Turn=true;
        player2Turn = false;
        moveMade = false;
        text="";
        cels= new Rectangle[3][3];
    }
    
    public void endGame(){
        gameRunning=false;
        if(TicTacToe.getInstance().getPlayerClicked1()!=null){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,TicTacToe.getInstance().getPlayerClicked1());
        }
        if(TicTacToe.getInstance().getPlayerClicked2()!=null){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,TicTacToe.getInstance().getPlayerClicked2());
        }
    }
    
    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public boolean isPlayer2Turn() {
        return player2Turn;
    }

    public void setPlayer2Turn(boolean player2Turn) {
        this.player2Turn = player2Turn;
    }
    
    public boolean isMoveMade() {
        return moveMade;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }    
    
    private void calcTicTacToe(){
        hMap = ((ControleUI.getInstance().getMainStage().getHeight()*0.6));
        wMap = hMap;
        
        hCel = (hMap/3);
        wCel = (wMap/3);
        
        h1 = h2 = ((ControleUI.getInstance().getMainStage().getHeight()-hMap)/2);
        w1 = w2 = ((ControleUI.getInstance().getMainStage().getWidth()-wMap)/2);
    }
    
    private void drawStaticTicTacToe(){
        GC.setFill(Color.BLACK);
        GC.fillRect(0, 0, ControleUI.getInstance().getMainStage().getWidth(), ControleUI.getInstance().getMainStage().getHeight());
        GC.setStroke(Color.WHITE);
        GC.setLineWidth(2);
        GC.setFill(Color.WHITE);
        GC.setFont(new Font(ControleUI.getInstance().getMainStage().getHeight()*0.05));
        GC.fillText(text,((ControleUI.getInstance().getMainStage().getWidth()/2)-(text.length()*ControleUI.getInstance().getMainStage().getHeight()*0.01)),(h1-30));
        
        GC.strokeLine(w1, (h1+hCel), (w1+wMap), (h1+hCel));
        GC.strokeLine(w1, (h1+(hCel*2)), (w1+wMap), (h1+(hCel*2)));
        
        GC.strokeLine((w1+wCel), (h1), (w1+wCel), (h1+hMap));
        GC.strokeLine((w1+(wCel*2)), (h1), (w1+(wCel*2)), (h1+hMap));
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                double x1=(w1+(wCel*i)),y1=(h1+(hCel*j)),x2=(w1+(wCel*(i+1))),y2=(h1+(hCel*(j+1)));
                cels[i][j]=new Rectangle(x1,y1,(x2-x1),(y2-y1));
            }
        }
    }
    
    private void drawPlayerMovesTicTacToe(){
        GC.setLineWidth(6);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                switch(TicTacToe.getInstance().getMap()[i][j]){
                    case(1):{
                        GC.setStroke(Color.LIGHTBLUE);
                        double x1=((wCel*i)+(wCel*0.2)+w1),
                                x2=((wCel*i)+(wCel*0.8)+w1),
                                y1=((hCel*j)+(hCel*0.2)+h1),
                                y2=((hCel*j)+(hCel*0.8)+h1);
                        GC.strokeLine(x1, y1, x2, y2);
                        GC.strokeLine(x1, y2, x2, y1);
                        break;
                    }
                    case(2):{
                        GC.setStroke(Color.LIGHTCORAL);
                        double r=(hCel*0.6),
                                oX=(((wCel*i)+(wCel/2))-(r/2)+w1),
                                oY=(((hCel*j)+(hCel/2))-(r/2)+h1);
                        GC.strokeOval(oX, oY, r, r);
                        break;
                    }
                    case(3):{
                        GC.setStroke(Color.GOLD);
                        double x1=((wCel*i)+(wCel*0.2)+w1),
                                x2=((wCel*i)+(wCel*0.8)+w1),
                                y1=((hCel*j)+(hCel*0.2)+h1),
                                y2=((hCel*j)+(hCel*0.8)+h1);
                        GC.strokeLine(x1, y1, x2, y2);
                        GC.strokeLine(x1, y2, x2, y1);
                        break;
                    }
                    case(4):{
                        GC.setStroke(Color.GOLD);
                        double r=(hCel*0.6),
                                oX=(((wCel*i)+(wCel/2))-(r/2)+w1),
                                oY=(((hCel*j)+(hCel/2))-(r/2)+h1);
                        GC.strokeOval(oX, oY, r, r);
                        break;
                    }
                }
            }
        }
    }

    public Rectangle[][] getCels() {
        return cels;
    }
}
