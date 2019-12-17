/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rkeda.RKEDA;

/**
 *
 * @author mayowaayodele
 */
public class RunRKEDA {

     public static void main(String[] args) {

        int populationSize;
        String path;
        String probName;
        int FEs;
        int truncSize;
        boolean elitism;
        double mutationRate;
        String resultsPath;
        String saveAs;
        Double initialStdev;
        
        populationSize = Integer.parseInt(args[0]);
        path = args[1];
        probName = args[2];
        FEs = Integer.parseInt(args[3]);
        truncSize = Integer.parseInt(args[4]);
        elitism = (Integer.parseInt(args[5]) != 0);
     
        initialStdev = Double.parseDouble(args[6]);
        resultsPath = args[7];
        saveAs = args[8];
        mutationRate = 0;
        RKEDA alg1 = new RKEDA(populationSize, path, probName, FEs, truncSize, elitism, mutationRate, resultsPath, saveAs);
        
        System.out.println(path + probName);

         try {
             alg1.runRKEDA(initialStdev);
         } catch (ParseException ex) {
             Logger.getLogger(RunRKEDA.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(RunRKEDA.class.getName()).log(Level.SEVERE, null, ex);
         }


    }


}
