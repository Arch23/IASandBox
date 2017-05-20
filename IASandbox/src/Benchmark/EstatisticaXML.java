/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Benchmark;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import iasandbox.ControleUI;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Gabriel
 */
public class EstatisticaXML {

    private final XStream xstream;

    public EstatisticaXML() {
        xstream = new XStream(new DomDriver());
        xstream.alias("Ranking", Stats.class);
    }

    private String verificaPlayer1(int player) {
        String aux = null;
        switch (player) {
            case 0:
                aux = "Human1";
                break;
            case 1:
                aux = "Max";
                break;
            case 2:
                aux = "PodaMax";
                break;
            case 3:
                aux = "SBR1";
                break;
            default:
                break;
        }
        return aux;
    }
     private String verificaPlayer2(int player) {
        String aux = null;
        switch (player) {
            case 0:
                aux = "Human2";
                break;
            case 1:
                aux = "Min";
                break;
            case 2:
                aux = "PodaMin";
                break;
            case 3:
                aux = "SBR2";
                break;
            default:
                break;
        }
        return aux;
    }

    private void geraXMLfile(Stats rank, int Player1, int Player2) {
        String textoxml = xstream.toXML(rank);
        try {
            PrintWriter writer = new PrintWriter(verificaPlayer1(Player1) + "Vs"
                    + verificaPlayer2(Player2) + ".xml", "UTF-8");
            writer.print(textoxml);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stats xmltoRank(int Player1, int Player2) {
        try {
            Stats aux;
            File xml = new File(verificaPlayer1(Player1) + "Vs" + verificaPlayer2(Player2) + ".xml");
            aux = (Stats) xstream.fromXML(xml);
            return aux;
        } catch (Exception e) {
            //System.err.println("erro");
            Stats aux = new Stats();
            geraXMLfile(aux, Player1, Player2);
            return aux;
        }
    }

    public void incrementEmpate() {
        try {
            EstatisticaXML xmlparser = new EstatisticaXML();
            Stats staux = xmlparser.xmltoRank(
                    ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
            staux.setEmpates(staux.getEmpates() + 1);
            xmlparser.geraXMLfile(staux, ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incrementVitoria() {
        try {
            EstatisticaXML xmlparser = new EstatisticaXML();
            Stats staux = xmlparser.xmltoRank(
                    ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
            staux.setVitorias(staux.getVitorias() + 1);
            xmlparser.geraXMLfile(staux, ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void incrementDerrota() {
        try {
            EstatisticaXML xmlparser = new EstatisticaXML();
            Stats staux = xmlparser.xmltoRank(
                    ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
            staux.setDerrotas(staux.getDerrotas() + 1);
            xmlparser.geraXMLfile(staux, ControleUI.getInstance().getPlayer1(),
                    ControleUI.getInstance().getPlayer2());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args) {
        EstatisticaXML rank = new EstatisticaXML();
        rank.xmltoRank(1, 2);
    }*/
}
