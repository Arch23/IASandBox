/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import statistics.PFBenchmark.PFBenchXML;
import statistics.PFBenchmark.PFStats;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class StatsPathfindingController implements Initializable {

    @FXML
    private TableView<PFStats> AstarStats;
    @FXML
    private TableColumn<PFStats, Integer> AstarNosVisitados;
    @FXML
    private TableColumn<PFStats, Integer> AStarNosSolucao;
    @FXML
    private TableColumn<PFStats, Integer> AStarTamMatriz;
    @FXML
    private TableColumn<PFStats, Long> AstarTempoDecisao;

    @FXML
    private TableView<PFStats> LarguraStats;
    @FXML
    private TableColumn<PFStats, Integer> LarguraNosVisitados;
    @FXML
    private TableColumn<PFStats, Integer> LarguraNosSolucao;
    @FXML
    private TableColumn<PFStats, Integer> LarguraTamMatriz;
    @FXML
    private TableColumn<PFStats, Long> LarguraTempoDecisao;

    @FXML
    private TableView<PFStats> ProfundidadeStats;
    @FXML
    private TableColumn<PFStats, Integer> ProfundidadeNosVisitados;
    @FXML
    private TableColumn<PFStats, Integer> ProfundidadeNosSolucao;
    @FXML
    private TableColumn<PFStats, Integer> ProfundidadeTamMatriz;
    @FXML
    private TableColumn<PFStats, Long> ProfundidadeTempoDecisao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        atualizaAll();
    }

    public void atualizaAll() {
        carregaTabelaAstar();
        carregaTabelaProfundidade();
        carregaTabelaLargura();
    }

    private void carregaTabelaAstar() {
        AStarNosSolucao.setCellValueFactory(new PropertyValueFactory<>("NosSolution"));
        AStarTamMatriz.setCellValueFactory(new PropertyValueFactory<>("TamMatriz"));
        AstarNosVisitados.setCellValueFactory(new PropertyValueFactory<>("NosVisitados"));
        AstarTempoDecisao.setCellValueFactory(new PropertyValueFactory<>("TempoDecisao"));
        AstarStats.setItems(new PFBenchXML().xmltoBenchmark(0));
    }

    private void carregaTabelaProfundidade() {
        ProfundidadeNosSolucao.setCellValueFactory(new PropertyValueFactory<>("NosSolution"));
        ProfundidadeTamMatriz.setCellValueFactory(new PropertyValueFactory<>("TamMatriz"));
        ProfundidadeNosVisitados.setCellValueFactory(new PropertyValueFactory<>("NosVisitados"));
        ProfundidadeTempoDecisao.setCellValueFactory(new PropertyValueFactory<>("TempoDecisao"));
        ProfundidadeStats.setItems(new PFBenchXML().xmltoBenchmark(2));
    }

    private void carregaTabelaLargura() {
        LarguraNosSolucao.setCellValueFactory(new PropertyValueFactory<>("NosSolution"));
        LarguraTamMatriz.setCellValueFactory(new PropertyValueFactory<>("TamMatriz"));
        LarguraNosVisitados.setCellValueFactory(new PropertyValueFactory<>("NosVisitados"));
        LarguraTempoDecisao.setCellValueFactory(new PropertyValueFactory<>("TempoDecisao"));
        LarguraStats.setItems(new PFBenchXML().xmltoBenchmark(1));
    }

}
