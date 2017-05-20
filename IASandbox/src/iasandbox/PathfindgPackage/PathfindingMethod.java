/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.PathfindgPackage;

import java.util.ArrayList;

/**
 *
 * @author noda2
 */
public interface PathfindingMethod {
    
    public ArrayList<Dots> getPath(int[] origin,int[] dest);
}
