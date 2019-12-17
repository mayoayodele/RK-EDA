/*
 * Orderings.java
 *
 * Main method takes a given array and an ordering array and rearrage the first to the ordering specified by the second
 * Now expanded to include methods with default orderings
 *
 */

package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author sb
 */
public class Orderings
{
    /** @returns an array with each element containing a number corresponding to its index */
    public static int[] linearOrderingAscending(int length)
    {
        int[] rval = new int[length];
        for (int i = 0; i < rval.length; i++)
            rval[i] = i;
        
        return rval;
    }
    
    /** @returns an array with each element containing a number corresponding to length - its index (descending order) */
    public static int[] linearOrderingDescending(int length)
    {
        int[] rval = new int[length];
        for (int i = rval.length - 1; i >= 0; i--)
            rval[i] = i;
        
        return rval;
    }
    
    /** @returns an array with elements in a random ordering */
    public static int[] randomOrdering(int length, Random r)
    {
        // Get a linear ordering
        int[] rval = linearOrderingAscending(length);
        
        // Now shuffle it
        for (int i = 0; i < length * 3; i++)
        {
            int x = (int)(length * r.nextDouble());
            int y = (int)(length * r.nextDouble());
            int temp = rval[x];
            rval[x] = rval[y];
            rval[y] = temp;
        }
        
        return rval;
    }
    
    public static int[] randomOrdering(int length)
    {
        return randomOrdering(length, new Random());
    }
    
    public static void main(String[] args)
    {
        int[][] population = new int[][]{
            {0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,1,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1},
            {0,0,0,0,1,0,0,0,1,1,0,1,1,0,0,1},
            {0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,1,0,0,0,1,0,1,0,0,0,1},
            {0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,1,1,1,0,0,1,1,0,1,1,0,0,1},
            {1,0,1,1,1,1,0,0,1,1,0,1,1,0,0,1}
        };
        
        // fitnesses for above: 12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,8,8,8,8,8,4
        
        int[] ordering = new int[]{8,9,12,5,3,2,0,15,14,13,11,10,7,6,4,1 };
        
        System.out.println("Java:");
        System.out.print("{");
        for (int i = 0; i < population.length; i++)
        {
            System.out.print("{");
            for (int j = 0; j < population[i].length; j++)
            {
                System.out.print(population[i][ordering[j]]);
                if (j < population[i].length - 1) System.out.print(",");
            }
            if (i < population.length - 1) System.out.println("},");
            else System.out.println("}};");
        }
        
        System.out.println("C:");
        for (int i = 0; i < population.length; i++)
        {
            //System.out.print("");
            for (int j = 0; j < population[i].length; j++)
            {
                System.out.print("w.s.bPop["+(i+1)+"]["+(j+1)+"] = ");
                System.out.print((population[i][ordering[j]]==1?"true":"false"));
                /*if (j < population[i].length - 1)*/ System.out.println(";");
            }
            //if (i < population.length - 1) System.out.println("},");
            //else System.out.println("");
        }
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
//		double kt = count;
		return kt;
	}
    
    public static double[] generateRandomRK(int n) {
        double[] rk = new double[n];

        Random rand = new Random();
        for (int i = 0; i < rk.length; i++) {
            rk[i] = rand.nextDouble();
        }

        return rk;
    }
    
       public static LinkedList<Integer> randomKeyToAL1(double[] priorities1) {
        LinkedList<Double> p = new LinkedList<Double>();
        LinkedList<Integer> AL = new LinkedList<Integer>();
        double[] priorities = Arrays.copyOf(priorities1, priorities1.length);
        for (int i = 0; i < priorities.length; i++) {
            p.add(priorities[i]);
        }
        Collections.sort(p);

        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < priorities.length; j++) {
                if (p.get(i) == priorities[j] && !AL.contains(j + 1)) {
                    AL.add(j + 1);
                }

            }

        }
        return AL;
    }
    
       
          public static int[] randomKeyToAL(double[] priorities1) {
        LinkedList<Double> p = new LinkedList<Double>();
        LinkedList<Integer> AL = new LinkedList<Integer>();
      
        double[] priorities = Arrays.copyOf(priorities1, priorities1.length);
        for (int i = 0; i < priorities.length; i++) {
            p.add(priorities[i]);
        }
//              System.out.println("unsorted: " + p);
        Collections.sort(p);
//              System.out.println("sorted: " + p);

        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < priorities.length; j++) {
                if (p.get(i) == priorities[j] && !AL.contains(j)) {
                    AL.add(j);
                }

            }

        }

          int[] AL1 = new int[AL.size()];
              for (int i = 0; i < AL.size(); i++) {
                  AL1[i] = AL.get(i);
              }
//              System.out.println("AL: " + AL);
//                System.out.println("AL1: " + Arrays.toString(AL1));
        return AL1;
    }
    
          
            public static ArrayList<Double> ArraytoList(double[] a) {
       
        ArrayList<Double> a1 = new ArrayList<Double>();
        for (int i = 0; i < a.length; i++) {
            a1.add(a[i]);
        }
        return a1;
    }
    
         
               
            public static double[] ListtoArray(ArrayList<Double> a) {
       
        double[] a1 = new double[a.size()];
        for (int i = 0; i < a.size(); i++) {
            a1[i]= a.get(i);
        }
        return a1;
    }
    
                 public static ArrayList<Integer> ArraytoList(int[] a) {
       
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            a1.add(a[i]);
        }
        return a1;
    }
    
         
               
            public static int[] ListtoArrayInt(ArrayList<Integer> a) {
       
        int[] a1 = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            a1[i]= a.get(i);
        }
        return a1;
    }
          
       
         public static double[] normaliseRanks(int[] ranks1) {
//             System.out.println("ranks: " + Arrays.toString(ranks1));
        LinkedList<Double> p = new LinkedList<Double>();
        int size = ranks1.length;
        double[] AL = new double[size];
        int[] ranks = Arrays.copyOf(ranks1, size);
        for (int i = 0; i < size; i++) {
//            AL[i] = (double)(ranks[i]-1)/(size-1);
             AL[ranks[i]] = (double)(i)/(size-1);
        }
//             System.out.println("normalised: " + Arrays.toString(AL));
        return AL;
    }

           public static double[] permutationToRK(int[] perm) {

        int n = perm.length;
        double[] rk = new double[n];

        int[] sortedperm = new int[n];
        for (int i = 0; i < perm.length; i++) {
            sortedperm[i] = perm[i];
        }

        Arrays.sort(sortedperm);

        Random rand = new Random();
        LinkedList<Double> randomKeys = new LinkedList<Double>();
        for (int i = 0; i < perm.length; i++) {

            double d = rand.nextDouble();
            randomKeys.add(d);

        }

        Collections.sort(randomKeys);
        for (int i = 0; i < n; i++) {
            rk[perm[i]] = randomKeys.get(i);
        }

        return Arrays.copyOf(rk, rk.length);

    }
}
