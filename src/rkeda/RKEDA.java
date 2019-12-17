/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rkeda;

import edaUtil.EDAUtil;
import edaUtil.Mutation;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import permutationProblems.*;
import representation.rk;
import utils.ArrayUtils;
import utils.Orderings;

/**
 *
 * @author 1013288
 */
public class RKEDA {

    int populationSize;
    String path;
    String probName;
    int FEs;
    int truncSize;
    boolean elitism;
    double mutationRate;
    String resultsPath;
    String saveAs;

    public RKEDA(int populationSize, String path, String probName, int FEs, int truncSize, boolean elitism, double mutationRate, String resultsPath, String saveAs) {
        this.populationSize = populationSize;
        this.path = path;
        this.probName = probName;
        this.FEs = FEs;
        this.truncSize = truncSize;
        this.elitism = elitism;
        this.mutationRate = mutationRate;
        this.resultsPath = resultsPath;
        this.saveAs = saveAs;
    }



    

       public void runRKEDA(double initialStdev) throws ParseException, IOException {

        boolean isfirst = true;
        boolean isMaxProb = false;
        PermutationProblem permProb;
        if (probName.contains("fsp")) {
            //makespan criteria
//            permProb = new PFSPmakespan(path + probName);
            permProb = new PFSPtotalflowtime(path + probName);
            System.out.println("fsp");
        } else if (probName.contains("tsp")) {
            permProb = new TSP(path + probName);
            System.out.println("tsp");
        } else if (probName.contains("qap")) {
            permProb = new QAP(path + probName);
            System.out.println("qap");
        } else {
            permProb = new LOP(path + probName);
            System.out.println("lop");
            isMaxProb = true;
        }

        int length = permProb.problemSize();
        double optimal = permProb.optimalFitness();


        ArrayList<rk> population = new ArrayList<rk>();
        ArrayList<rk> populationTemp = new ArrayList<rk>();
        int count = 0;
        int countToBest = 0;
        String out = "ProbName \t BestSolution \t bestFitness \terr \tcountToBest \n";
        String out1 = "ProbName \t BestSolution \t bestFitness \terr \tcountToBest \n";

        BufferedWriter br = new BufferedWriter(new FileWriter(resultsPath + "" + saveAs));
          br.write(out1);
        for (int i = 0; i < populationSize; i++) {
            rk perm = new rk(Orderings.generateRandomRK(length));
            perm.setPermutation(Orderings.randomKeyToAL(perm.copyGene()));
            perm.normalise();
            perm.setFitness(permProb.evaluate(perm.getPermutation()));
            count++;
            population.add(perm.copyOf());
//            System.out.println(perm.print());

        }
        double[] matrix;

        rk best;
        if (isMaxProb) {
            best = EDAUtil.getBestSolutionMax(population).copyOf();
            matrix = Arrays.copyOf(EDAUtil.getPMTruncationSelectionMaxProb1(population, truncSize),length );
        } else {
            best = EDAUtil.getBestSolutionMin(population).copyOf();
            matrix = Arrays.copyOf(EDAUtil.getPMTruncationSelection1(population, truncSize),length );
        }

        rk bestTemp;
        long gens = Math.round((double) FEs / populationSize);
        double weight = 1;
        double stdev = initialStdev * weight;
        boolean cool = true;
        int j = 0;
        outer:
        do {
            isfirst = true;
            for (int i = 0; i < populationSize; i++) {

                rk child;
                if (isfirst && elitism) {
                    child = best.copyOf();
                    isfirst = false;
                } else {

                    child = new rk(EDAUtil.getChild(matrix, stdev));

                    child.setPermutation(Orderings.randomKeyToAL(child.copyGene()));
                    child.normalise();
                    child.setFitness(permProb.evaluate(child.getPermutation()));
                    count++;
                    if (count == FEs) {
                        break;
                    }
                }
                populationTemp.add(child.copyOf());

                if (child.fitness == optimal) {
                    best = child.copyOf();
                    break outer;
                }
            }
            
            population = new ArrayList<rk>();
            population.addAll(populationTemp);
            populationTemp = new ArrayList<rk>();

               weight = 1 - ((double) j / gens);
            stdev = initialStdev * weight; 
            j++;
            
            
            if (isMaxProb) {
                if (population.size() == populationSize) {
                    matrix = Arrays.copyOf(EDAUtil.getPMTruncationSelectionMaxProb1(population, truncSize),length );
                }
                bestTemp = EDAUtil.getBestSolutionMax(population);

                if (bestTemp.fitness > best.fitness) {
                    best = bestTemp.copyOf();
                }

            } else {
                if (population.size() == populationSize) {
                        matrix = Arrays.copyOf(EDAUtil.getPMTruncationSelection1(population, truncSize),length );
       
                }
                bestTemp = EDAUtil.getBestSolutionMin(population);

                if (bestTemp.fitness < best.fitness) {
                    
                    best = bestTemp.copyOf();
                    countToBest = count;
//                    double err = ((double) best.fitness - (double) optimal) / (double) optimal;

                      out1 = probName + "\t" + Arrays.toString(best.permutation) + "\t" + best.getFitness() + "\t" + 0.00 + "\t" + countToBest + "\n";
                br.write(out1);
                }
            }
        } while (count < FEs);
        
        
        br.close();
         out += probName + "\t" + Arrays.toString(best.permutation) + "\t" + best.getFitness() + "\t" + 0.00 + "\t" + countToBest + "\n";             
          BufferedWriter br1 = new BufferedWriter(new FileWriter((resultsPath + "" + saveAs).replace(".txt", "all.txt")));
         br1.write(out);
          br1.close();
    }


}
