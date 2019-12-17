/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author mayowaayodele
 */
public class Summary {

    public static void main(String[] args) {
        String path = "./resultsnoNnoCS";
        int numberofRuns = 10;
      String s = "ProblemName \tMinimum \tMaximum \tAvgFitness \tStdev  \t noOfProbs\n";
      System.out.println(s);
        String name;

        int i = 0;
        File directory = new File(path);
        File directories[] = directory.listFiles();
        Arrays.sort(directories);
        String string = "";
        String inst = "";
        ArrayList<Double> values = new ArrayList<Double>();
        for (File f : directories) {

            try{ 
                if (f.isFile() && f.getName().contains(".txt") && f.getName().contains("all")) {
                RunResult result = new RunResult(f.getPath());

               
                inst = f.getName();
                if (!inst.contains(string)) {
                     values = new ArrayList<Double>();
                    i=0; 
                }
                 values.add(result.getFitness());
                    i++;
                if (i== numberofRuns) {
                    System.out.println(string + "\t "+ Collections.min(values) +"\t "+ Collections.max(values)+"\t "+ mean(values) + "\t " + stdev(values) + "\t " + values.size());

                    values = new ArrayList<Double>();
                    i=0;
                }  
                
              
                string = inst.substring(0, inst.indexOf("run")) ;
            }
            }
            catch(Exception e)
            {
                
            }
        }
    }

    /**
     * Mean.
     *
     * @param vals the vals
     * @return the mean of an array of double values
     */
    public static double mean(final double[] vals) {
        double rval = 0;
        for (final double val : vals) {
            rval += val;
        }
        return rval / vals.length;
    }

    public static double mean(final ArrayList<Double> vals) {
        double rval = 0;
        for (final double val : vals) {
            rval += val;
        }

        return rval / vals.size();
    }

    public static double stdev(final ArrayList<Double> vals) {
        final double mean = mean(vals);
        double thevalue = 0;
        for (final double val : vals) {
            thevalue = thevalue + Math.pow(val - mean, 2);
        }
        thevalue = Math.sqrt(thevalue / vals.size());
        return thevalue;
    }

    /**
     * Stdev.
     *
     * @param vals the vals
     * @return the double
     */
    public static double stdev(final double[] vals) {
        final double mean = mean(vals);
        double thevalue = 0;
        for (final double val : vals) {
            thevalue = thevalue + Math.pow(val - mean, 2);
        }
        thevalue = Math.sqrt(thevalue / vals.length);
        return thevalue;
    }

}
