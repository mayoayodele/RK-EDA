/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edaUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
//import org.apache.commons.math3.distribution.NormalDistribution;
//import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import representation.rk;
import utils.RKCompare;

/**
 *
 * @author 1013288
 */
public class EDAUtil {

    public static double[][] getPMTruncationSelection(ArrayList<rk> currentPopulation1, int truncSize) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[][] matrix = new double[numberOfActivities][2];

        Collections.sort(currentPopulation, new RKCompare());
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }
        double stdev = 0, mean;
        for (int i = 0; i < numberOfActivities; i++) {
            stdev = 0;
            mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);
            stdev = stdevFromDouble(S);

            matrix[i][0] = mean;
            matrix[i][1] = stdev;

        }

//         System.out.println("stdev: "+  stdev);  
        return matrix;
    }

      public static double[]getPMTruncationSelection1(ArrayList<rk> currentPopulation1, int truncSize) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[] matrix = new double[numberOfActivities];

        Collections.sort(currentPopulation, new RKCompare());
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }
        double  mean;
        for (int i = 0; i < numberOfActivities; i++) {
          
            mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);

            matrix[i] = mean;

        }

//         System.out.println("stdev: "+  stdev);  
        return matrix;
    }

    public static double[][] getPMTruncationSelectionMaxProb(ArrayList<rk> currentPopulation1, int truncSize) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[][] matrix = new double[numberOfActivities][2];

        Collections.sort(currentPopulation, new RKCompare());
        Collections.reverse(currentPopulation);
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }

        for (int i = 0; i < numberOfActivities; i++) {
            double stdev = 0, mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);
            stdev = stdevFromDouble(S);

            matrix[i][0] = mean;
            matrix[i][1] = stdev;
        }

        return matrix;
    }

        public static double[] getPMTruncationSelectionMaxProb1(ArrayList<rk> currentPopulation1, int truncSize) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[] matrix = new double[numberOfActivities];

        Collections.sort(currentPopulation, new RKCompare());
        Collections.reverse(currentPopulation);
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }

        for (int i = 0; i < numberOfActivities; i++) {
            double  mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);

            matrix[i] = mean;
        }

        return matrix;
    }

        public static double getMinimumVariance (double[] matrix1)
        {
            int length = matrix1.length;
            double[] matrix = Arrays.copyOf(matrix1,length);
            double m = matrix[0];
            double diff = Math.abs(m- matrix[1]);
            double diff1=0;
            for (int i = 0; i < length; i++) {
                for (int j = i+1; j < length; j++) {
                    diff1 = Math.abs(matrix[i] - matrix[j]);
                if (diff1<diff) {
                    diff = diff1;
                }
                }
                
            }
            return Math.abs(diff);
        }
// public static ArrayList<double[]> copyPopulation(ArrayList<double[]> currentPopulation1) {
//        ArrayList<double[]> currentPopulation = new ArrayList<double[]>();
//        for (int i = 0; i < currentPopulation1.size(); i++) {
//            currentPopulation.add(Arrays.copyOf(currentPopulation1.get(i), currentPopulation1.get(i).length));
//        }
//        return currentPopulation;
//    }
    public static ArrayList<rk> copyPopulation(ArrayList<rk> currentPopulation1) {
        ArrayList<rk> currentPopulation = new ArrayList<rk>();
        for (int i = 0; i < currentPopulation1.size(); i++) {
            currentPopulation.add(currentPopulation1.get(i).copyOf());
        }
        return currentPopulation;
    }

    public static double meanFromDouble(ArrayList<Double> vals) {
        double rval = 0;
        for (int i = 0; i < vals.size(); i++) {
            rval += vals.get(i);
        }
        return rval / vals.size();

    }

    public static double stdevFromDouble(ArrayList<Double> vals, double mean) {
        double thevalue = 0;
//        System.out.println("The mean is : "+mean);
        for (int i = 0; i < vals.size(); i++) {
            thevalue = thevalue + Math.pow(vals.get(i) - mean, 2); // Square each value and add it to thevalue
        }
        thevalue = Math.sqrt(thevalue / vals.size());
        return thevalue;
    }

    public static double[] getChildAct(double[][] currentModel1) throws ParseException, IOException {
        double[][] currentModel = copy2DArray(currentModel1);
        double[] childAct = new double[currentModel.length];
        Random r = new Random();
        for (int x = 0; x < currentModel.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = currentModel[x][0];
            double stdev = currentModel[x][1];
            double r2;
//            NormalDistribution nd = new NormalDistribution(currentModel[x][0], currentModel[x][1]);
//            double r2 = nd.sample();

            if (stdev == 0) {
                r2 = mean;
            } else {
                r2 = r.nextGaussian() * stdev + mean;
            }
//                NormalDistribution nd = new NormalDistribution(mean, stdev);
//                r2 = nd.sample();
//            } catch (NotStrictlyPositiveException e) {
//                r2 = mean;
//            }

            childAct[x] = r2;

        }

        return childAct;
    }

    public static double[] getChild(double[][] currentModel1) throws ParseException, IOException {
        double[][] currentModel = copy2DArray(currentModel1);
        double[] childAct = new double[currentModel.length];
        Random r = new Random();
        for (int x = 0; x < currentModel.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = currentModel[x][0];
            double stdev = currentModel[x][1];
            double r2;
//            NormalDistribution nd = new NormalDistribution(currentModel[x][0], currentModel[x][1]);
//            double r2 = nd.sample();

//            try {
//                NormalDistribution nd = new NormalDistribution(mean, stdev);
//                r2 = nd.sample();
//            } catch (NotStrictlyPositiveException e) {
//                r2 = mean;
//            }
            if (stdev == 0) {
                r2 = mean;
            } else {
                r2 = r.nextGaussian() * stdev + mean;
            }

            childAct[x] = r2;

        }

        return childAct;
    }

    public static double IndividualNormalizedKendallTau(int[] ordering1, int[] ordering2){

int count = 0;
int nOfTests =0;
for (int i=0; i<ordering1.length; i++){
for (int j=i+1; j<ordering1.length; j++){
nOfTests ++;
if ((ordering1[i]<ordering1[j] && ordering2[i]>ordering2[j]) || (ordering1[i]>ordering1[j] && ordering2[i]<ordering2[j])){
count ++;
}
}
}
double kt = count / (ordering1.length * (ordering1.length -1) * 0.5);
return kt;
}

public static double IndividualNormalizedKendallTau(ArrayList<Integer> ordering1, ArrayList<Integer> ordering2){

int count = 0;
int nOfTests =0;
for (int i=0; i<ordering1.size(); i++){
for (int j=i+1; j<ordering1.size(); j++){
nOfTests ++;
if ((ordering1.get(i)<ordering1.get(j) && ordering2.get(i)>ordering2.get(j)) || (ordering1.get(i)>ordering1.get(j) && ordering2.get(i)<ordering2.get(j))){
count ++;
}
}
}
double kt = count / (ordering1.size() * (ordering1.size() -1) * 0.5);
return kt;
}



    
    
    
    public static double[] getChild(double[][] currentModel1, double stdev, boolean useDefault) throws ParseException, IOException {
        double[][] currentModel = copy2DArray(currentModel1);
        double[] childAct = new double[currentModel.length];
        Random r = new Random();
        for (int x = 0; x < currentModel.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = currentModel[x][0];
            if (useDefault) {
                stdev = currentModel[x][1];
            }

            double r2;

            r2 = r.nextGaussian() * stdev + mean;

//            if (r2 >1|| r2<0) {
//                r2 = mean;
//            }
//            if (r2 > 1) {
//                r2 = 1;
//            }
//            if (r2 < 0) {
//                r2 = 0;
//            }
            childAct[x] = r2;

        }

        return childAct;
    }

       public static double[] getChild(double[] currentModel1, double stdev) throws ParseException, IOException {
        double[] currentModel = Arrays.copyOf(currentModel1,currentModel1.length );
        double[] childAct = new double[currentModel.length];
        Random r = new Random();
        for (int x = 0; x < currentModel.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = currentModel[x];
           

            double r2;

            r2 = r.nextGaussian() * stdev + mean;

//            if (r2 >1|| r2<0) {
//                r2 = mean;
//            }
//            if (r2 > 1) {
//                r2 = 1;
//            }
//            if (r2 < 0) {
//                r2 = 0;
//            }
            childAct[x] = r2;

        }

        return childAct;
    }

    public static double[] getChild(double[][] prevModel1, double[][] currentModel1, double learningRate, double stdev) throws ParseException, IOException {
        double[][] currentModel = copy2DArray(currentModel1);
        double[][] prevModel = copy2DArray(prevModel1);
        double[][] matrix = copy2DArray(currentModel1);
        if (learningRate != 1) {

            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][0] = (learningRate * currentModel[i][0]) + ((1 - learningRate) * prevModel[i][0]);
//                }
            }
        }

        double[] childAct = new double[matrix.length];
        Random r = new Random();
        for (int x = 0; x < matrix.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = matrix[x][0];

            double r2;

            r2 = (r.nextGaussian() * stdev) + mean;

            childAct[x] = r2;

        }

        return childAct;
    }

    public static double[][] getPMTruncationSelection(ArrayList<rk> currentPopulation1, int truncSize, double[][] prevModel1, double learningRate) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[][] matrix = new double[numberOfActivities][2];
        double[][] prevModel = copy2DArray(prevModel1);
        Collections.sort(currentPopulation, new RKCompare());
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }
        double stdev = 0, mean;
        for (int i = 0; i < numberOfActivities; i++) {
            stdev = 0;
            mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);
            stdev = stdevFromDouble(S);

            matrix[i][0] = mean;
            matrix[i][1] = stdev;

        }

        if (learningRate != 1) {

            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][0] = (learningRate * matrix[i][0]) + ((1 - learningRate) * prevModel[i][0]);
//                }
            }
        }
//         System.out.println("stdev: "+  stdev);  
        return matrix;
    }

    public static double[][] getPMTruncationSelectionMaxProb(ArrayList<rk> currentPopulation1, int truncSize, double[][] prevModel1, double learningRate) throws ParseException, IOException {

        LinkedList<rk> selectedGenome = new LinkedList<rk>();
        ArrayList<rk> currentPopulation = copyPopulation(currentPopulation1);
        int numberOfActivities = currentPopulation.get(0).copyGene().length;
        double[][] matrix = new double[numberOfActivities][2];
        double[][] prevModel = copy2DArray(prevModel1);
        Collections.sort(currentPopulation, new RKCompare());
        Collections.reverse(currentPopulation);
//        util.sortPopulationRK(currentPopulation);

        for (int i = 0; i < truncSize; i++) {
            selectedGenome.add(currentPopulation.get(i).copyOf());

        }

        for (int i = 0; i < numberOfActivities; i++) {
            double stdev = 0, mean = 0;
            ArrayList<Double> S = new ArrayList<Double>();
            for (int j = 0; j < truncSize; j++) {
                S.add(selectedGenome.get(j).copyGene()[i]);
            }

            mean = meanFromDouble(S);
            stdev = stdevFromDouble(S);

            matrix[i][0] = mean;
            matrix[i][1] = stdev;
        }
        if (learningRate != 1) {

            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][0] = (learningRate * matrix[i][0]) + ((1 - learningRate) * prevModel[i][0]);
//                }
            }
        }
        return matrix;
    }

    public static double[] getChildbyCrossover(double[] P1, double[] P2) {
        double[] crossedList = new double[P1.length];
        Random r = new Random();
        int crossover_point = 0;
        // no need to ensure the last point is not the crossoverpoint since we only take the genes from parent 1 before the crossover point
        while (crossover_point == 0) {
            crossover_point = r.nextInt(P1.length);
        }
        for (int i = 0; i < crossover_point; i++) {
            crossedList[i] = P1[i];
        }
        for (int i = crossover_point; i < P2.length; i++) {
            crossedList[i] = P2[i];
        }
        return crossedList;
    }

    public static double[] getChild(double[][] currentModel1, double weight) throws ParseException, IOException {
        double[][] currentModel = copy2DArray(currentModel1);
        double[] childAct = new double[currentModel.length];
        Random r = new Random();
        for (int x = 0; x < currentModel.length; x++) {
//             double r2 = r.nextGaussian() * currentModel[x][1] + currentModel[x][0];
            double mean = currentModel[x][0];

            double stdev = currentModel[x][1];
            stdev = stdev * weight;

            double r2;

            r2 = r.nextGaussian() * stdev + mean;

            if (r2 > 1) {
                r2 = 1;
            }
            if (r2 < 0) {
                r2 = 0;
            }

            childAct[x] = r2;

        }

        return childAct;
    }

    public static rk getBestSolutionMax(ArrayList<rk> Sched) {
//		int minMakespan = 0;
//		RKSchedule r = S.get(i).copyOf();

        rk best = Sched.get(0).copyOf();
        for (int i = 1; i < Sched.size(); i++) {
            rk S = Sched.get(i).copyOf();
            if (S.getFitness() > best.getFitness()) {

                best = S;
            }
        }
        return best;
    }

    public static rk getBestSolutionMin(ArrayList<rk> Sched) {
//		int minMakespan = 0;
//		RKSchedule r = S.get(i).copyOf();

        rk best = Sched.get(0).copyOf();
        for (int i = 1; i < Sched.size(); i++) {
            rk S = Sched.get(i).copyOf();
            if (S.getFitness() < best.getFitness()) {

                best = S;
            }
        }
        return best;
    }

    public static double[][] copy2DArray(double[][] gene) {
        double[][] array2D = new double[gene.length][gene[0].length];
        for (int i = 0; i < gene.length; i++) {
            System.arraycopy(gene[i], 0, array2D[i], 0, gene[i].length);

        }
        return array2D;
    }

    public static int[][] copy2DArray(int[][] gene) {
        int[][] array2D = new int[gene.length][gene[0].length];
        for (int i = 0; i < gene.length; i++) {
            System.arraycopy(gene[i], 0, array2D[i], 0, gene[i].length);

        }
        return array2D;
    }

    public static double stdevFromDouble(ArrayList<Double> vals) {
        double mean = meanFromDouble(vals);
        double thevalue = 0;
//        System.out.println("The mean is : "+mean);
        for (int i = 0; i < vals.size(); i++) {
            thevalue = thevalue + Math.pow(vals.get(i) - mean, 2); // Square each value and add it to thevalue
        }
        thevalue = Math.sqrt(thevalue / vals.size());
        return thevalue;
    }

}
