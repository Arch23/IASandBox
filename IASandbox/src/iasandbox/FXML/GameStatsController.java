/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import statistics.benchmark.EstatisticaXML;
import statistics.benchmark.Stats;
import iasandbox.ControleUI;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import misc.FormatedGameStats;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class GameStatsController implements Initializable {

    @FXML
    private TableView<FormatedGameStats> Stats;
    @FXML
    private TableColumn<FormatedGameStats, String> Player1;
    @FXML
    private TableColumn<FormatedGameStats, String> Player2;
    @FXML
    private TableColumn<FormatedGameStats, Integer> Vitorias;
    @FXML
    private TableColumn<FormatedGameStats, Integer> Derrotas;
    @FXML
    private TableColumn<FormatedGameStats, Integer> Empates;
    @FXML
    private TableColumn<FormatedGameStats, Double> porcentV;
    @FXML
    private TableColumn<FormatedGameStats, Double> porcentD;
    @FXML
    private TableColumn<FormatedGameStats, Double> porcentE;
    @FXML
    private StackedBarChart<String,Number> Grafico;
    @FXML
    private Region left;
    @FXML
    private Region center;
    @FXML
    private Region right;
    @FXML
    private AnchorPane root;
    @FXML
    private MenuBar menu;
    @FXML
    private HBox box;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTabela();
        carregaGrafico();
        setUp();
    }

    private void setUp(){
        box.setAlignment(Pos.CENTER);
        box.prefWidthProperty().bind(root.widthProperty());
        
        root.prefWidthProperty().bind(ControleUI.getInstance().getStatsStage().widthProperty());
        root.prefHeightProperty().bind(ControleUI.getInstance().getStatsStage().heightProperty());
        
        menu.prefWidthProperty().bind(root.widthProperty());
        
        Grafico.setAnimated(false);
        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(center, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);
    }
    
    public void atualizaAll() {
        carregaTabela();
        carregaGrafico();
    }

    @FXML
    private void mostraStatsTempo() {
        ControleUI.getInstance().mostraStats();
        ControleUI.getInstance().getStatsStage().hide();
    }

    private void carregaTabela() {
        Player1.setCellValueFactory(new PropertyValueFactory<>("Player1"));
        Player2.setCellValueFactory(new PropertyValueFactory<>("Player2"));
        Vitorias.setCellValueFactory(new PropertyValueFactory<>("Vitorias"));
        Derrotas.setCellValueFactory(new PropertyValueFactory<>("Derrotas"));
        Empates.setCellValueFactory(new PropertyValueFactory<>("Empate"));
        porcentV.setCellValueFactory(new PropertyValueFactory<>("porcentV"));
        porcentE.setCellValueFactory(new PropertyValueFactory<>("porcentE"));
        porcentD.setCellValueFactory(new PropertyValueFactory<>("porcentD"));
        try {
            Stats.setItems(encontraArquivosAndLoadList());
        } catch (Exception ex) {
        }
    }
    
    private void carregaGrafico(){
        try{
        Grafico.getData().clear();
        }
        catch(Exception e){
            //
        }
        ObservableList<FormatedGameStats> ListaAux;
        try {
            ListaAux = encontraArquivosAndLoadList();
        } catch (Exception ex) {
            ListaAux = FXCollections.observableArrayList();
        }
        XYChart.Series<String,Number> empate = new XYChart.Series<>();
        empate.setName("Empate");
        empate.getData().clear();
        for(int i = 0; i<ListaAux.size();i++){
            empate.getData().add(new Data<>(ListaAux.get(i).getPlayer1()+"Vs"+ListaAux.get(i).getPlayer2()
            ,ListaAux.get(i).getEmpate()));
        }
        Grafico.getData().add(empate);
        
        XYChart.Series<String,Number> derrotas = new XYChart.Series<>();
        derrotas.getData().clear();
        derrotas.setName("Derrotas");
        for(int i = 0; i<ListaAux.size();i++){
            derrotas.getData().add(new Data<>(ListaAux.get(i).getPlayer1()+"Vs"+ListaAux.get(i).getPlayer2()
            ,ListaAux.get(i).getDerrotas()));
        }
        Grafico.getData().add(derrotas);
        
        XYChart.Series<String,Number> vitorias = new XYChart.Series<>();
        vitorias.setName("Vitórias");
        vitorias.getData().clear();
        for(int i = 0; i<ListaAux.size();i++){
            vitorias.getData().add(new Data<>(ListaAux.get(i).getPlayer1()+"Vs"+ListaAux.get(i).getPlayer2()
            ,ListaAux.get(i).getVitorias()));
        }
        
        Grafico.getData().add(vitorias);
    }

    private ObservableList<FormatedGameStats> encontraArquivosAndLoadList() throws Exception {
        ObservableList<FormatedGameStats> Lista = FXCollections.observableArrayList();
        EstatisticaXML rank = new EstatisticaXML();
        File aux = new File("TestesTicTacToe");
        File auxvet[] = aux.listFiles((File file) -> {
            return file.getName().contains("Vs");
        });
        for (File ittemp : auxvet) {
            //Pegar o nome do arquivo
            String noxml = ittemp.getName().split(".xml")[0];
            String tempPlayers[] = noxml.split("Vs");
            Stats tempStats = rank.xmltoRank(ittemp);
            Lista.add(new FormatedGameStats(tempPlayers[0], tempPlayers[1],
                    tempStats.getVitorias(), tempStats.getDerrotas(), tempStats.getEmpates()));
        }
        return Lista;
    }

}
