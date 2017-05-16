/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOStream;

import ArvoreMinimax.TreeGenerator;
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
    
    public void initWriter(){
        File terminalNodes = new File("TerminalNodes.txt");
        try {
            terminalNodes.createNewFile();
            System.out.println(terminalNodes.canWrite());
            System.out.println(terminalNodes.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(terminalNodes));

        } catch (IOException ex) {
            Logger.getLogger(TreeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToWriter(String text){
        try {
            writer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWriter(){
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TreeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
