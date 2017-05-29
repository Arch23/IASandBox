/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.PFBenchmark;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import iasandbox.ControleUI;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gabriel
 */
public class PFBenchXML {

    private final XStream xstream;
    private int pos = 0;

    public PFBenchXML() {
        xstream = new XStream(new DomDriver());
        xstream.alias("Benchmark", PFStats.class);
    }

    public void encontraArquivos() {
        File vetfile[] = new File(".").listFiles((File file) -> {
            if (ControleUI.getInstance().getMetod() == 0) {
                return file.getName().contains("AStar");
            }
            if (ControleUI.getInstance().getMetod() == 1) {
                return file.getName().contains("Largura");
            } else {
                return file.getName().contains("Profundidade");
            }
        });
        for (File vetfile1 : vetfile) {
            pos++;
        }
    }

    public void geraXMLfile(PFStats bench) {
        String textoxml = xstream.toXML(bench);
        encontraArquivos();
        try {
            PrintWriter writer = new PrintWriter("lixo.txt");
            if (ControleUI.getInstance().getMetod() == 0) {
                writer = new PrintWriter("AStarTeste" + (pos + 1) + ".xml", "UTF-8");
            }
            if (ControleUI.getInstance().getMetod() == 1) {
                writer = new PrintWriter("LarguraTeste" + (pos + 1) + ".xml", "UTF-8");
            }
            if (ControleUI.getInstance().getMetod() == 2) {
                writer = new PrintWriter("ProfundidadeTeste" + (pos + 1) + ".xml", "UTF-8");
            }
            writer.print(textoxml);
            writer.flush();
            writer.close();
            ControleUI.getInstance().getStatsController().atualizaAll();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public ObservableList<PFStats> xmltoBenchmark(int tipoBusca) {
        ObservableList<PFStats> Lista = FXCollections.observableArrayList();
        try {
            PFStats aux;
            File vetfile[] = new File(".").listFiles((File file) -> {
                if (tipoBusca == 0) {
                    return file.getName().contains("AStar");
                }
                if (tipoBusca == 1) {
                    return file.getName().contains("Largura");
                } else {
                    return file.getName().contains("Profundidade");
                }
            });
            for (File temp : vetfile) {
                aux = (PFStats) xstream.fromXML(temp);
                Lista.add(aux);
            }
            return Lista;
        } catch (Exception e) {
            //System.err.println("erro");
        }
        return null;
    }
}
