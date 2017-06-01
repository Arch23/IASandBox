/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import iasandbox.Pathfinding.Map.GenMap;
import iasandbox.Pathfinding.Map.MapNode;
import iasandbox.Pathfinding.Dots;
import iasandbox.Pathfinding.PathfindingA;
import iasandbox.Pathfinding.PathfindingBF;
import iasandbox.Pathfinding.PathfindingDFS;
import iasandbox.Pathfinding.PathfindingMethod;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import statistics.PFBenchmark.PFBenchXML;
import statistics.PFBenchmark.PFStats;

/**
 *
 * @author noda2
 */
public class PathfindingLogic {

    /*
        Var
     */
    private static PathfindingLogic instance;
    private double hM,
            wM,
            hCel,
            wCel,
            h1,
            h2,
            w1,
            w2;
    private MapNode[][] map;
    private Rectangle[][] cels;
    private EventHandler<MouseEvent> user;
    private boolean firstClick;
    private int[] origin, dest;

    private int[][] layout;
    private int size;
    private PathfindingMethod method;
    private ArrayList<Dots> path;
    private String text;

    /*
        End var
     */

 /*
        Construct
     */
    private PathfindingLogic() {
    }

    /*
        End Construct
     */

 /*
        Methods
     */
    public static PathfindingLogic getInstance() {
        return ((instance == null) ? (instance = new PathfindingLogic()) : (instance));
    }

    public void pathFinding() {
        //inicializando as var
        firstClick = true;
        origin = new int[2];
        dest = new int[2];
        text = "";
        path = null;
        setMethod();
        layout = new int[size][size];

        GenMap obj = new GenMap();
        map = obj.init(layout);
        cels = new Rectangle[map.length][map[1].length];
        calcPathFinding();
        drawStaticPathFinding();
        user = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (firstClick) {
                    int[] tmp = getCord(event);
                    if (tmp[0] != -1 && tmp[1] != -1) {
                        origin = tmp;
                        firstClick = false;
//                        System.out.println("Pegou primeiro click: x "+origin[0]+" y "+origin[1]);
                    }
                } else {
                    int[] tmp = getCord(event);
                    if (tmp[0] != -1 && tmp[1] != -1) {
                        dest = tmp;
//                        System.out.println("Pegou segundo click: x "+dest[0]+" y "+dest[1]);
                        magic();
                    }
                }
            }
        };
        ControleUI.getInstance().getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, user);
    }

    public void calcPathFinding() {
        hM = (ControleUI.getInstance().getMainStage().getHeight() * (0.70));
        wM = hM;
        try{
        hCel = (hM / map[0].length);
        wCel = hCel;
        }
        catch(Exception e){
          //  
        }

        h1 = ((ControleUI.getInstance().getMainStage().getHeight() - hM) * 0.5);
        h2 = ((ControleUI.getInstance().getMainStage().getHeight() - hM) * 0.5);
        w1 = w2 = ((ControleUI.getInstance().getMainStage().getWidth() - wM) / 2);
    }

    public void drawStaticPathFinding() {
        GraphicsContext gc = ControleUI.getInstance().getMainController().getGC();
        gc.clearRect(0, 0, ControleUI.getInstance().getMainStage().getWidth(), ControleUI.getInstance().getMainStage().getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, ControleUI.getInstance().getMainStage().getWidth(), ControleUI.getInstance().getMainStage().getHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(ControleUI.getInstance().getMainStage().getHeight() * 0.05));
        gc.fillText(text, ((ControleUI.getInstance().getMainStage().getWidth() / 2) - (text.length() * ControleUI.getInstance().getMainStage().getHeight() * 0.012)), (h1 - 10));

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                double x1 = (w1 + (i * wCel)), y1 = (h1 + (j * hCel)), x2 = (w1 + ((i + 1) * wCel)), y2 = (h1 + ((j + 1) * hCel));
                cels[i][j] = new Rectangle(x1, y1, (x2 - x1), (y2 - y1));
                gc.beginPath();
                gc.rect(x1, y1, (x2 - x1), (y2 - y1));
                gc.stroke();
                switch (map[i][j].getTipo()) {
                    case ("campo"): {
                        gc.setFill(Color.GREEN);
                        gc.fill();
                        break;
                    }
                    case ("parede"): {
                        gc.setFill(Color.GREY);
                        gc.fill();
                        break;
                    }
                    case ("obs"): {
                        gc.setFill(Color.BURLYWOOD);
                        gc.fill();
                        break;
                    }
                    case ("open"): {
                        gc.setFill(Color.CORNFLOWERBLUE);
                        gc.fill();
                        break;
                    }
                    case ("closed"): {
                        gc.setFill(Color.CRIMSON);
                        gc.fill();
                        break;
                    }
                    case ("origin"): {
                        gc.setFill(Color.WHITE);
                        gc.fill();
                        break;
                    }
                    case ("end"): {
                        gc.setFill(Color.GOLD);
                        gc.fill();
                        break;
                    }
                }
                gc.closePath();
            }
        }
//        for(int i=0;i<map.length;i++){
//            for(int j=0;j<map[i].length;j++){
//                double x1 = (w1+(j*wCel)), y1 = (h1+(i*hCel));
//                gc.setLineWidth(4);
//                gc.setStroke(Color.ROYALBLUE);
//                if(map[i][j].getDown()!=null){
//                    gc.strokeLine((x1+(wCel/2)),(y1+(hCel/2)),(x1+(wCel/2)),(y1+(hCel/2)+((hCel/2)*0.8)));
//                }
//                if(map[i][j].getUp()!=null){
//                    gc.strokeLine((x1+(wCel/2)), (y1+(hCel*0.1)), (x1+(wCel/2)),(y1+(hCel/2)));
//                }
//                if(map[i][j].getLeft()!=null){
//                    gc.strokeLine((x1+(wCel*0.1)),(y1+(hCel/2)),(x1+(wCel/2)), (y1+(hCel/2)));
//                }
//                if(map[i][j].getRight()!=null){
//                    gc.strokeLine((x1+(wCel/2)),(y1+(hCel/2)),(x1+(wCel/2)+((wCel/2)*0.8)), (y1+(hCel/2)));
//                }
//            }
//        }
    }

    public void drawPlayerMovesPathFinding() {
        if (path != null) {
            GraphicsContext gc = ControleUI.getInstance().getMainController().getGC();
            double dotX1 = 0, dotY1 = 0, dotX2 = 0, dotY2 = 0;
            double diam = hCel * 0.5;
            boolean firstDot = true;
            for (Dots dot : path) {
                dotX1 = (w1 + (dot.getX() * wCel) + (wCel / 2));
                dotY1 = (h1 + (dot.getY() * hCel) + (hCel / 2));
                gc.setStroke(Color.RED);
                gc.setLineWidth(2);

                gc.strokeOval(dotX1 - (diam / 2), dotY1 - (diam / 2), diam, diam);

                if (!firstDot) {
                    gc.strokeLine(dotX1, dotY1, dotX2, dotY2);
                } else {
                    firstDot = false;
                }
                dotX2 = dotX1;
                dotY2 = dotY1;
            }
        }
    }

    private int[] getCord(MouseEvent e) {
        int[] cord = new int[]{-1, -1};
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (cels[i][j].contains(e.getSceneX(), e.getSceneY())) {
                    if (!map[i][j].getTipo().equals("parede")) {
                        cord[0] = i;
                        cord[1] = j;
                    }
                }
            }
        }
        return (cord);
    }

    private void magic() {
        path = null;
        path = method.getPath(origin, dest);
        PFStats temp = new PFStats(ControleUI.getInstance().getNumberOfNodesVisited(),
                ControleUI.getInstance().getNumberOfSteps(), ControleUI.getInstance().getTempoDecisao());
        new PFBenchXML().geraXMLfile(temp);
        if (ControleUI.getInstance().getPathStatsController() != null) {
            ControleUI.getInstance().getPathStatsController().atualizaAll();
        }
        if (path == null) {
            text = "Caminho não encontrado!";
        }
        ControleUI.getInstance().getMainController().reDraw();
        endGame();
        ControleUI.getInstance().getMainController().getCanvasPane().requestFocus();
    }

    private void setMethod() {
        switch (ControleUI.getInstance().getMetod()) {
            //A*
            case (0): {
                method = new PathfindingA();
                text = "A*";
                break;
            }
            //Gulosa
            case (1): {
                method = new PathfindingBF();
                text = "Breadth-First Search";
                break;
            }
            //depth first search
            case (2): {
                method = new PathfindingDFS();
                text = "Depth-First Search";
                break;
            }
        }
    }

    public void endGame() {
        if (user != null) {
            ControleUI.getInstance().getCanvas().removeEventHandler(MouseEvent.MOUSE_CLICKED, user);
        }
    }

    /*
        End methods
     */

 /*
        Getters Setters
     */
    public void setLayout(int[][] layout) {
        this.layout = layout;
    }

    public MapNode[][] getMap() {
        return map;
    }

    public void setMap(MapNode[][] map) {
        this.map = map;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    /*
        End Getters Setters
     */
}
