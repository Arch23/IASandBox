/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author noda2
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) {
        ControleUI.getInstance().start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
