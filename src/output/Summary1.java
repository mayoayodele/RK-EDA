/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import static output.Summary.mean;
import static output.Summary.stdev;

/**
 *
 * @author mayowaayodele
 */
public class Summary1 {

    public static void main(String[] args) {
        String path = "./results";
        int numberofRuns = 20;

        String s = "run \t ProblemName \tFitness \tError \tFEs \n";
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

            if (f.isFile() && f.getName().contains(".txt") && f.getName().contains("V0")) {
                inst = f.getName();

                RunResult1 result = new RunResult1(f.getPath());
                System.out.println(inst + "\t" + result.getProbName() + "\t" + result.getFitness() + "\t" + result.getErr() + "\t" + result.getFEs());

                values.add(result.getFitness());

                string = inst.substring(0, inst.indexOf("run"));
            }

        }

    }

    public static double mean(final double[] vals) {
        double rval = 0;
        for (final double val : vals) {
            rval += val;
        }
        return rval / vals.length;
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

}
