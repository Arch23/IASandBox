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
    }

}
