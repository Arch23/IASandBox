/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.FXML;

import statistics.benchmark.Benchmark;
import statistics.benchmark.BenchmarkXML;
import iasandbox.ControleUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import misc.FormatedBenchMark;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class StatsTicTacToeController implements Initializable {
    //Primeira Tabela

    @FXML
    TableView<FormatedBenchMark> Stats;
    @FXML
    TableColumn<FormatedBenchMark, String> Jogador;
    @FXML
    TableColumn<FormatedBenchMark, Long> TempoTotal;
    @FXML
    TableColumn<FormatedBenchMark, Long> JogadasRealizadas;
    @FXML
    TableColumn<FormatedBenchMark, Long> MediaTempoJogada;

    //Fim da primeira tabela
    //Inicio da segunda tabela
    @FXML
    TableView<FormatedBenchMark> Stats2;
    @FXML
    TableColumn<FormatedBenchMark, String> Jogador2;
    @FXML
    TableColumn<FormatedBenchMark, Long> TempoTotalArvMinMax;
    @FXML
    TableColumn<FormatedBenchMark, Long> VezesArvMinMax;
    @FXML
    TableColumn<FormatedBenchMark, Long> MediaArvMinMax;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTabela();
        carregaTabela2();
    }
    
    @FXML
    private void mostraStatsJogo(){
        System.out.println("Stats de Jogo");
        ControleUI.getInstance().mostraGameStats();
    }
    public void atualizaAll() {
        carregaTabela();
        carregaTabela2();
    }
    
    private ObservableList<FormatedBenchMark> carregaLista() {
        ObservableList<FormatedBenchMark> Lista = FXCollections.observableArrayList();
        Benchmark bench = new BenchmarkXML().xmltoBenchmark();
        FormatedBenchMark aux;
        
        if (bench.getMinMaxVezesLogic() > 0) {
            aux = new FormatedBenchMark("MinMax", bench.getMinMaxLogic(),
                    bench.getMinMaxVezesLogic(), (bench.getMinMaxLogic() / bench.getMinMaxVezesLogic()));
            Lista.add(aux);
        }
        
        if (bench.getPodaVezesLogic() > 0) {
            aux = new FormatedBenchMark("Poda", bench.getPodaLogic(),
                    bench.getPodaVezesLogic(), (bench.getPodaLogic() / bench.getPodaVezesLogic()));
            Lista.add(aux);
        }
        
        if (bench.getSbrVezesLogic() > 0) {
            aux = new FormatedBenchMark("SBR", bench.getSbrLogic(),
                    bench.getSbrVezesLogic(), (bench.getSbrLogic() / bench.getSbrVezesLogic()));
            Lista.add(aux);
        }
        return Lista;
    }
    
    private void carregaTabela() {
        Jogador.setCellValueFactory(new PropertyValueFactory<>("Jogador"));
        TempoTotal.setCellValueFactory(new PropertyValueFactory<>("TempoTotal"));
        JogadasRealizadas.setCellValueFactory(new PropertyValueFactory<>("JogadasRealizadas"));
        MediaTempoJogada.setCellValueFactory(new PropertyValueFactory<>("MediaTempoJogada"));
        
        Stats.setItems(carregaLista());
    }
    
    private void carregaTabela2() {
        Benchmark bench = new BenchmarkXML().xmltoBenchmark();
        FormatedBenchMark ftb = new FormatedBenchMark("MinMax", bench.getMinMaxGeraArvore(),
                bench.getMinMaxVezesGerouArvore());
        ObservableList<FormatedBenchMark> Lista = FXCollections.observableArrayList();
        Lista.add(ftb);
        Jogador2.setCellValueFactory(new PropertyValueFactory<>("Jogador"));
        TempoTotalArvMinMax.setCellValueFactory(new PropertyValueFactory<>("TempoTotalArvMinMax"));
        VezesArvMinMax.setCellValueFactory(new PropertyValueFactory<>("VezesArvMinMax"));
        MediaArvMinMax.setCellValueFactory(new PropertyValueFactory<>("MediaArvMinMax"));
        Stats2.setItems(Lista);
    }
    
}
