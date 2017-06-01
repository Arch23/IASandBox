/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.benchmark;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import iasandbox.ControleUI;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Gabriel
 */
public class BenchmarkXML {

    private final XStream xstream;

    public BenchmarkXML() {
        xstream = new XStream(new DomDriver());
        xstream.alias("Benchmark", Benchmark.class);
    }
    
    private void verificaeCriaPasta(){
        if(new File("TestesTicTacToe").exists()){
            //
        }else{
            new File("TestesTicTacToe").mkdir();
        }
    }
    public void geraXMLfile(Benchmark bench) {
        verificaeCriaPasta();
        String textoxml = xstream.toXML(bench);
        try {
            PrintWriter writer = new PrintWriter("TestesTicTacToe/Benchmark.xml", "UTF-8");
            writer.print(textoxml);
            writer.flush();
            writer.close();
            ControleUI.getInstance().getStatsController().atualizaAll();
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }

    public Benchmark xmltoBenchmark() {
        try {
            Benchmark aux;
            File xml = new File("TestesTicTacToe/Benchmark.xml");
            aux = (Benchmark) xstream.fromXML(xml);
            return aux;
        } catch (Exception e) {
            //System.err.println("erro");
            Benchmark aux = new Benchmark();
            geraXMLfile(aux);
            return aux;
        }
    }
    
 /*   public static void main(String[] args) {
        BenchmarkXML rank = new BenchmarkXML();
        rank.xmltoBenchmark();
    }*/
}
