/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOStream;

import ArvoreMinimax.Node;
import ArvoreMinimax.TreeMiniMax;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noda2
 */
public class Writer {
    private BufferedWriter writer;
    String newline = System.getProperty("line.separator");
    
    private static Writer instance;
    
    private Writer(){}
    
    public static Writer getInstance(){
        return((instance==null)?instance = new Writer():instance);
    }
    
    public void initWriter(){
        File terminalNodes = new File("TerminalNodes.txt");
        try {
            terminalNodes.createNewFile();
            System.out.println(terminalNodes.canWrite());
            System.out.println(terminalNodes.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(terminalNodes));

        } catch (IOException ex) {
            Logger.getLogger(TreeMiniMax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToWriter(String text){
        try {
            writer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeNode(Node node,int h){
        try{
            writer.write(newline+""+newline+"<node>");
            if(node.getFilhos().isEmpty()){
                writer.write(newline+"\t<leaf>");
            }else{
                writer.write(newline+"\t<branch>");
            }
            writeMap(node.getMap());
            writer.write(newline+"\t\t<utilidade>"+node.getUtilidade()+"<utilidade/>");
            writer.write(newline+"\t\t<height>"+h+"<height/>");
            if(node.getFilhos().isEmpty()){
                writer.write(newline+"\t<leaf/>");
            }else{
                writer.write(newline+"\t<branc/h>");
            }
            writer.write(newline+"<node/>");
        }catch(Exception ex){}
    }
    
    private void writeMap(int[][] map){
        try{
            writer.write(newline+"\t\t<map>");
            for(int i=0;i<map.length;i++){
                writer.write(newline+"\t\t\t");
                for(int j=0;j<map[i].length;j++){
                    writer.write(map[i][j]+" ");
                }
            }
            writer.write(newline+"\t\t<map/>");
        }catch(Exception ex){}
    }
    
    public void closeWriter(){
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TreeMiniMax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
