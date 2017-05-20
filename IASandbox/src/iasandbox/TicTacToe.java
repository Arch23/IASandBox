/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import Benchmark.EstatisticaXML;
import iasandbox.TicTacToePackage.TicTacToeHuman;
import iasandbox.TicTacToePackage.TicTacToeMiniMax;
import iasandbox.TicTacToePackage.TicTacToeAlfaBeta;
import iasandbox.TicTacToePackage.TicTacToeRules;
import iasandbox.TicTacToePackage.ticTacToePlayer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author noda2
 */
public class TicTacToe {

    /*
        Var
     */
    private static TicTacToe instance = null;
    private int[][] mapGame;
    private int n = 3;
    private int movesMade = 0;

    private ticTacToePlayer player1 = null;
    private ticTacToePlayer player2 = null;

    private EventHandler<MouseEvent> playerClicked1 = null;
    private EventHandler<MouseEvent> playerClicked2 = null;

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

    /*
        End var
     */

 /*
        constructor
     */
    private TicTacToe() {
    }

    /*
        End constructor
     */

 /*
        Methods
     */
    public static TicTacToe getInstance() {
        return (instance == null ? instance = new TicTacToe() : instance);
    }

    /*
        Inicia o jogo e seta os parâmetros
     */
    public void startGame() {
        movesMade = 0;
        player1 = null;
        player2 = null;
        playerClicked1 = null;
        playerClicked2 = null;
        mapGame = clearMap(mapGame);
        setPlayers();
        setVarTicTacToe();
    }

    public void setVarTicTacToe() {
        gameRunning = true;
        player1Turn = true;
        player2Turn = !player1Turn;
        moveMade = false;
        text = "";
        cels = new Rectangle[3][3];
    }

    /*
        Laço principal do jogo
     */
    public void ticTacToe() {
        if (gameRunning) {
            if (!moveMade) {
                if (player1Turn) {
                    player1Turn = TicTacToe.getInstance().player1Move();
                    player2Turn = !player1Turn;
                    moveMade = !player1Turn;
                } else if (player2Turn) {
                    player2Turn = TicTacToe.getInstance().player2Move();
                    player1Turn = !player2Turn;
                    moveMade = !player2Turn;
                }
            }
            switch (TicTacToe.getInstance().checkBoard(TicTacToe.getInstance().getMap(), TicTacToe.getInstance().getMovesMade())) {
                case (0): {
                    endGame();
                    new EstatisticaXML().incrementEmpate();
                    text = "draw!";
                    break;
                }
                case (1): {
                    endGame();
                    new EstatisticaXML().incrementVitoria();
                    text = "player 1 won!";
                    break;
                }
                case (2): {
                    endGame();
                    new EstatisticaXML().incrementDerrota();
                    text = "player 2 won!";
                    break;
                }
                case (-1): {
                    if (player1Turn) {
                        text = "player 1";
                    } else if (player2Turn) {
                        text = "player 2";
                    }
                    break;
                }
            }
            ControleUI.getInstance().getMainController().reDraw();
            if (moveMade) {
                moveMade = false;
                ticTacToe();
            }
        }
        ControleUI.getInstance().getMainController().getCanvasPane().requestFocus();
    }

    //termina o jogo
    public void endGame() {
        gameRunning = false;
        if (TicTacToe.getInstance().getPlayerClicked1() != null) {
            ControleUI.getInstance().getMainController().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, TicTacToe.getInstance().getPlayerClicked1());
        }
        if (TicTacToe.getInstance().getPlayerClicked2() != null) {
            ControleUI.getInstance().getMainController().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, TicTacToe.getInstance().getPlayerClicked2());
        }
    }

    /*
        Métodos para a renderização
     */
    public void calcTicTacToe() {
        hMap = ((ControleUI.getInstance().getMainStage().getHeight() * 0.6));
        wMap = hMap;

        hCel = (hMap / 3);
        wCel = (wMap / 3);

        h1 = h2 = ((ControleUI.getInstance().getMainStage().getHeight() - hMap) / 2);
        w1 = w2 = ((ControleUI.getInstance().getMainStage().getWidth() - wMap) / 2);
    }

    public void drawStaticTicTacToe() throws Exception {
        ControleUI.getInstance().getMainController().getGC().setFill(Color.BLACK);
        ControleUI.getInstance().getMainController().getGC().fillRect(0, 0, ControleUI.getInstance().getMainStage().getWidth(), ControleUI.getInstance().getMainStage().getHeight());
        ControleUI.getInstance().getMainController().getGC().setStroke(Color.WHITE);
        ControleUI.getInstance().getMainController().getGC().setLineWidth(2);
        ControleUI.getInstance().getMainController().getGC().setFill(Color.WHITE);
        ControleUI.getInstance().getMainController().getGC().setFont(new Font(ControleUI.getInstance().getMainStage().getHeight() * 0.05));
        ControleUI.getInstance().getMainController().getGC().fillText(text, ((ControleUI.getInstance().getMainStage().getWidth() / 2) - (text.length() * ControleUI.getInstance().getMainStage().getHeight() * 0.01)), (h1 - 30));

        ControleUI.getInstance().getMainController().getGC().strokeLine(w1, (h1 + hCel), (w1 + wMap), (h1 + hCel));
        ControleUI.getInstance().getMainController().getGC().strokeLine(w1, (h1 + (hCel * 2)), (w1 + wMap), (h1 + (hCel * 2)));

        ControleUI.getInstance().getMainController().getGC().strokeLine((w1 + wCel), (h1), (w1 + wCel), (h1 + hMap));
        ControleUI.getInstance().getMainController().getGC().strokeLine((w1 + (wCel * 2)), (h1), (w1 + (wCel * 2)), (h1 + hMap));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double x1 = (w1 + (wCel * j)), y1 = (h1 + (hCel * i)), x2 = (w1 + (wCel * (j + 1))), y2 = (h1 + (hCel * (i + 1)));
                cels[i][j] = new Rectangle(x1, y1, (x2 - x1), (y2 - y1));
            }
        }
    }

    public void drawPlayerMovesTicTacToe() {
        ControleUI.getInstance().getMainController().getGC().setLineWidth(6);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (TicTacToe.getInstance().getMap()[j][i]) {
                    case (1): {
                        ControleUI.getInstance().getMainController().getGC().setStroke(Color.LIGHTBLUE);
                        double x1 = ((wCel * i) + (wCel * 0.2) + w1),
                                x2 = ((wCel * i) + (wCel * 0.8) + w1),
                                y1 = ((hCel * j) + (hCel * 0.2) + h1),
                                y2 = ((hCel * j) + (hCel * 0.8) + h1);
                        ControleUI.getInstance().getMainController().getGC().strokeLine(x1, y1, x2, y2);
                        ControleUI.getInstance().getMainController().getGC().strokeLine(x1, y2, x2, y1);
                        break;
                    }
                    case (2): {
                        ControleUI.getInstance().getMainController().getGC().setStroke(Color.LIGHTCORAL);
                        double r = (hCel * 0.6),
                                oX = (((wCel * i) + (wCel / 2)) - (r / 2) + w1),
                                oY = (((hCel * j) + (hCel / 2)) - (r / 2) + h1);
                        ControleUI.getInstance().getMainController().getGC().strokeOval(oX, oY, r, r);
                        break;
                    }
                    case (3): {
                        ControleUI.getInstance().getMainController().getGC().setStroke(Color.GOLD);
                        double x1 = ((wCel * i) + (wCel * 0.2) + w1),
                                x2 = ((wCel * i) + (wCel * 0.8) + w1),
                                y1 = ((hCel * j) + (hCel * 0.2) + h1),
                                y2 = ((hCel * j) + (hCel * 0.8) + h1);
                        ControleUI.getInstance().getMainController().getGC().strokeLine(x1, y1, x2, y2);
                        ControleUI.getInstance().getMainController().getGC().strokeLine(x1, y2, x2, y1);
                        break;
                    }
                    case (4): {
                        ControleUI.getInstance().getMainController().getGC().setStroke(Color.GOLD);
                        double r = (hCel * 0.6),
                                oX = (((wCel * i) + (wCel / 2)) - (r / 2) + w1),
                                oY = (((hCel * j) + (hCel / 2)) - (r / 2) + h1);
                        ControleUI.getInstance().getMainController().getGC().strokeOval(oX, oY, r, r);
                        break;
                    }
                }
            }
        }
    }

    /*
        Pega os movimentos dos players e aplica no mapa
     */
    public boolean player1Move() {
        if (!player1.getClass().equals(TicTacToeHuman.class)) {
            if (playerClicked2 != null) {
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked2);
            }
            int[] mov = player1.logic();
            return (makeMove(mov, 1));
        } else {
            playerClicked1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((TicTacToeHuman) player1).translateClick(event);
                }
            };
            if (playerClicked2 != null) {
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked2);
            }
            ControleUI.getInstance().getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked1);
            return (true);
        }

    }

    public boolean player2Move() {
        if (!player2.getClass().equals(TicTacToeHuman.class)) {
            if (playerClicked1 != null) {
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked1);
            }
            int[] mov = player2.logic();
            return (makeMove(mov, 2));
        } else {
            playerClicked2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((TicTacToeHuman) player2).translateClick(event);
                }
            };
            if (playerClicked1 != null) {
                ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked1);
            }
            ControleUI.getInstance().getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, playerClicked2);
            return (true);
        }
    }

    public boolean makeMove(int[] cord, int player) {
        mapGame[cord[0]][cord[1]] = player;
        movesMade++;
        return (false);
    }

    /*
        Verifica o estado do tabuleiro passado por parâmetro
     */
    public int checkBoard(int[][] map, int moves) {
        if ((map[0][0] == 1) && (map[0][1] == 1) && (map[0][2] == 1)) {
            map[0][0] = 3;
            map[0][1] = 3;
            map[0][2] = 3;
            return (1);
        } else if ((map[1][0] == 1) && (map[1][1] == 1) && (map[1][2] == 1)) {
            map[1][0] = 3;
            map[1][1] = 3;
            map[1][2] = 3;
            return (1);
        } else if ((map[2][0] == 1) && (map[2][1] == 1) && (map[2][2] == 1)) {
            map[2][0] = 3;
            map[2][1] = 3;
            map[2][2] = 3;
            return (1);
        } else if ((map[0][0] == 1) && (map[1][0] == 1) && (map[2][0] == 1)) {
            map[0][0] = 3;
            map[1][0] = 3;
            map[2][0] = 3;
            return (1);
        } else if ((map[0][1] == 1) && (map[1][1] == 1) && (map[2][1] == 1)) {
            map[0][1] = 3;
            map[1][1] = 3;
            map[2][1] = 3;
            return (1);
        } else if ((map[0][2] == 1) && (map[1][2] == 1) && (map[2][2] == 1)) {
            map[0][2] = 3;
            map[1][2] = 3;
            map[2][2] = 3;
            return (1);
        } else if ((map[0][0] == 1) && (map[1][1] == 1) && (map[2][2] == 1)) {
            map[0][0] = 3;
            map[1][1] = 3;
            map[2][2] = 3;
            return (1);
        } else if ((map[0][2] == 1) && (map[1][1] == 1) && (map[2][0] == 1)) {
            map[0][2] = 3;
            map[1][1] = 3;
            map[2][0] = 3;
            return (1);
        } else if ((map[0][0] == 2) && (map[0][1] == 2) && (map[0][2] == 2)) {
            map[0][0] = 4;
            map[0][1] = 4;
            map[0][2] = 4;
            return (2);
        } else if ((map[1][0] == 2) && (map[1][1] == 2) && (map[1][2] == 2)) {
            map[1][0] = 4;
            map[1][1] = 4;
            map[1][2] = 4;
            return (2);
        } else if ((map[2][0] == 2) && (map[2][1] == 2) && (map[2][2] == 2)) {
            map[2][0] = 4;
            map[2][1] = 4;
            map[2][2] = 4;
            return (2);
        } else if ((map[0][0] == 2) && (map[1][0] == 2) && (map[2][0] == 2)) {
            map[0][0] = 4;
            map[1][0] = 4;
            map[2][0] = 4;
            return (2);
        } else if ((map[0][1] == 2) && (map[1][1] == 2) && (map[2][1] == 2)) {
            map[0][1] = 4;
            map[1][1] = 4;
            map[2][1] = 4;
            return (2);
        } else if ((map[0][2] == 2) && (map[1][2] == 2) && (map[2][2] == 2)) {
            map[0][2] = 4;
            map[1][2] = 4;
            map[2][2] = 4;
            return (2);
        } else if ((map[0][0] == 2) && (map[1][1] == 2) && (map[2][2] == 2)) {
            map[0][0] = 4;
            map[1][1] = 4;
            map[2][2] = 4;
            return (2);
        } else if ((map[0][2] == 2) && (map[1][1] == 2) && (map[2][0] == 2)) {
            map[0][2] = 4;
            map[1][1] = 4;
            map[2][0] = 4;
            return (2);
        }
        if (moves == 9) {
            return (0);
        } else {
            return (-1);
        }
    }

    //método para limpar o tabuleiro
    private int[][] clearMap(int[][] map) {
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 0;
            }
        }
        return (map);
    }

    //Deleta a objeto atual TicTacToe
    public void deleteInstance() {
        instance = null;
    }

    //Seta os players para os objetos apropriados
    private void setPlayers() {
        switch (ControleUI.getInstance().getPlayer1()) {
            case (0): {
                player1 = new TicTacToeHuman(1);
                break;
            }
            case (1): {
                player1 = new TicTacToeMiniMax(1, ControleUI.getInstance().getPlayersController().getDificuldade()
                        .getValue() / 10);
                break;
            }
            case (2): {
                player1 = new TicTacToeAlfaBeta(1);
                break;
            }
            case (3): {
                player1 = new TicTacToeRules(1);
                break;
            }
        }
        switch (ControleUI.getInstance().getPlayer2()) {
            case (0): {
                player2 = new TicTacToeHuman(2);
                break;
            }
            case (1): {
                player2 = new TicTacToeMiniMax(2, ControleUI.getInstance().getPlayersController().getDificuldade2()
                        .getValue() / 10);
                break;
            }
            case (2): {
                player2 = new TicTacToeAlfaBeta(2);
                break;
            }
            case (3): {
                player2 = new TicTacToeRules(2);
                break;
            }
        }
    }

    /*
        End Methods
     */
 /*
        Getter Setter
     */
    public int getMovesMade() {
        return movesMade;
    }

    public int[][] getMap() {
        return ((mapGame == null) ? clearMap(mapGame) : mapGame);
    }

    public EventHandler<MouseEvent> getPlayerClicked1() {
        return playerClicked1;
    }

    public EventHandler<MouseEvent> getPlayerClicked2() {
        return playerClicked2;
    }

    public Rectangle[][] getCels() {
        return cels;
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
    /*
        End Getter Setter
     */
}
