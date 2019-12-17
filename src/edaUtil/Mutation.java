/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edaUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import representation.rk;
import utils.Orderings;

/**
 *
 * @author 1013288
 */
public class Mutation {
    
    public int countEvals =0;
    
  public  static double[] performRandomMutation(double[] ordering) {
        double[] activities = Arrays.copyOf(ordering, ordering.length);
        Random r = new Random();

        int randomPosition = 0;
        double a = 0;

 
            randomPosition = (r.nextInt(activities.length));
  
//            double priorityValue = activities[randomPosition];
        a = r.nextDouble();
        activities[randomPosition] = a;
        return activities;
    }
  
  
    public  static double[] performMutation(double[] ordering, int mutationType) {
        double[] activities = Arrays.copyOf(ordering, ordering.length);
           double[] offspring = new double[ordering.length];
        if (mutationType == 1) {
               offspring  = Arrays.copyOf(performSwapMutation(activities), ordering.length);
        }
        else if (mutationType == 2) {
         offspring  = Arrays.copyOf(performPointMutation(activities), ordering.length);
        }
        else if (mutationType == 3)
        {
             double[] activities1  = Arrays.copyOf(performSwapMutation(activities), ordering.length);
            
         offspring  = Arrays.copyOf(performPointMutation(activities1), ordering.length);
        }
        else if (mutationType == 4)
        {
            if (Math.random() < 0.5) {
                   
        offspring  = Arrays.copyOf(performPointMutation(activities), ordering.length);
            }
            else
            {
                offspring  = Arrays.copyOf(performSwapMutation(activities), ordering.length);
            }
        }
    
        return offspring;
    }
 
  
  public  static double[] performSwapMutation(double[] ordering) {
        double[] activities = Arrays.copyOf(ordering, ordering.length);
        Random r = new Random();
        
        int randomPosition = 0;
         int randomPosition1 = 0;
        double a = 0;
        double a1 =0;

           do {
            randomPosition = (r.nextInt(activities.length));
             randomPosition1 = (r.nextInt(activities.length));
            } while (randomPosition ==randomPosition1);
//            double priorityValue = activities[randomPosition];
        a = activities[randomPosition];
        a1 = activities[randomPosition1];
        activities[randomPosition] = a1;
         activities[randomPosition1] = a;
        return activities;
    }
 
  
  
   public  static double[] performPointMutation(double[] ordering) {
        double[] activities = Arrays.copyOf(ordering, ordering.length);
        Random r = new Random();
        
        int randomPosition = 0;
        double a = r.nextDouble();

            randomPosition = (r.nextInt(activities.length));
                activities[randomPosition] = a;
        
        return activities;
    }
 
  public  static double[] performSwapMutation(double[] ordering, int randomPosition ,int randomPosition1) {
        double[] activities = Arrays.copyOf(ordering, ordering.length);
  
        double a = 0;
        double a1 =0;

        a = activities[randomPosition];
        a1 = activities[randomPosition1];
        activities[randomPosition] = a1;
         activities[randomPosition1] = a;
        return activities;
    }
 
  
    public static ArrayList<double[]> getSwapNeighboursRK(rk solution) {
        ArrayList<double[]> list = new ArrayList<double[]>();
        double[] a = Arrays.copyOf(solution.copyGene(), solution.copyGene().length);
 
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
               list.add(performSwapMutation(solution.randomkeys,i,j) );
            }
        }
        return list;
    }

  
    public static ArrayList<double[]> getInsertNeighboursRK(rk solution) {
        ArrayList<double[]> list = new ArrayList<double[]>();
        double[] a = Arrays.copyOf(solution.copyGene(), solution.copyGene().length);
        //n +1 possible insert positions
        double[] c = new double[a.length + 1];
        double x = (double) 1 / (a.length - 1);
        for (int i = 0; i < a.length + 1; i++) {
            //  x is the difference between each element of the rescaled RK
            if (i == 0) {
                c[i] = (double) 0 - 0.1 * x;
            } else {
                c[i] = (double) (i - 1) / (a.length - 1) + (double) 0.1 * x;
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < c.length; j++) {
                boolean skip = false;
                double[] b = Arrays.copyOf(a, a.length);
                if (!(c[j] < b[i] && b[i] < c[j + 1])) {
                    // skip juxtapositions that have already been seen.
                    for (int k = 0; k < a.length; k++) {
                        if ((a[k] == (a[i] - x)) && (c[j] < a[k] && a[k] < c[j + 1])) {
                            skip = true;
                        }

                    }

                    if (!skip) {
                        b[i] = c[j];
                        list.add(Arrays.copyOf(b, b.length));
                    } else {
                    }

                } else {
                    j++;
                }
            }
        }

        return list;
    }
    
    
//     public static ArrayList<double[]> getInsertNeighboursRK(rk solution) {
//        ArrayList<double[]> list = new ArrayList<double[]>();
//        double[] a = Arrays.copyOf(solution.copyGene(), solution.copyGene().length);
//        //n +1 possible insert positions
//        double[] c = new double[a.length + 1];
//        double x = (double) 1 / (a.length - 1);
//        for (int i = 0; i < a.length + 1; i++) {
//            //  c[1] is the difference between each element of the normalise RK
//            if (i == 0) {
//                c[i] = (double) 0 - 0.1 * x;
//            } else {
//                c[i] = (double) (i - 1) / (a.length - 1) + (double) 0.1 * x;
//            }
//        }
//
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < c.length; j++) {
//                boolean skip = false;
//                double[] b = Arrays.copyOf(a, a.length);
//                if (!(c[j] < b[i] && b[i] < c[j + 1])) {
//                    // skip juxtapositions that have already been seen.
//                    for (int k = 0; k < a.length; k++) {
//                        if ((a[k] == (a[i] - x)) && (c[j] < a[k] && a[k] < c[j + 1])) {
//                            skip = true;
//                        }
//
//                    }
//
//                    if (!skip) {
//                        b[i] = c[j];
//                        list.add(Arrays.copyOf(b, b.length));
//                    } else {
//                    }
//
//                } else {
//                    j++;
//                }
//            }
//        }
//
//        return list;
//    }

    
       public static ArrayList<double[]> getInsertNeighboursRK(double[] solution) {
        ArrayList<double[]> list = new ArrayList<double[]>();
        double[] a = Arrays.copyOf(solution, solution.length);
        //n +1 possible insert positions
        double[] c = new double[a.length + 1];
        double x = (double) 1 / (a.length - 1);
        for (int i = 0; i < a.length + 1; i++) {
            //  x is the difference between each element of the rescaled RK
            if (i == 0) {
                c[i] = (double) 0 - 0.1 * x;
            } else {
                c[i] = (double) (i - 1) / (a.length - 1) + (double) 0.1 * x;
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < c.length; j++) {
                boolean skip = false;
                double[] b = Arrays.copyOf(a, a.length);
                if (!(c[j] < b[i] && b[i] < c[j + 1])) {
                    // skip juxtapositions that have already been seen.
                    for (int k = 0; k < a.length; k++) {
                        if ((a[k] == (a[i] - x)) && (c[j] < a[k] && a[k] < c[j + 1])) {
                            skip = true;
                        }

                    }

                    if (!skip) {
                        b[i] = c[j];
                        list.add(Arrays.copyOf(b, b.length));
                    } else {
                    }

                } else {
                    j++;
                }
            }
        }

        return list;
    }

     public static ArrayList<int[]> getInsertNeighboursPermutation(rk solution) {
       ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<Integer> a2 = new ArrayList<Integer>();
        int[] a = Arrays.copyOf(solution.permutation, solution.permutation.length);
        a1.addAll(Orderings.ArraytoList(a));
        a2.addAll(a1);
        ArrayList<int[]> list = new ArrayList<int[]>();
//        System.out.println(Arrays.toString(a));
//               System.out.println(Arrays.toString(Orderings.normaliseRanks(a)));
        for (int i = 0; i < a.length; i++) {
          
            for (int j = 0; j < a.length; j++) {
                if (i!=j && j!=(i-1)) {
                      a2 = new ArrayList<Integer>();
                a2.addAll(a1);
                a2.remove(i);
                a2.add(j, a1.get(i));
                list.add(Orderings.ListtoArrayInt(a2));

                }
      }
           
        }
        return list;
    }

     
    
         public static double[] shakeRK (rk solution, int numberofShakes) {
//        ArrayList<double[]> list = new ArrayList<double[]>();
            int randomPosition = 0;
         int randomPosition1 = 0;
            Random r = new Random();
                 double[] a = Arrays.copyOf(solution.copyGene(), solution.copyGene().length);
                    rk RK = new rk(a);
          int d =0;
          do{
              
            randomPosition = (r.nextInt(a.length));
             
   
        //n +1 possible insert positions
        double[] c = new double[a.length + 1];
        double x = (double) 1 / (a.length - 1);
        for (int i = 0; i < a.length + 1; i++) {
            //  x is the difference between each element of the rescaled RK
            if (i == 0) {
                c[i] = (double) 0 - 0.1 * x;
            } else {
                c[i] = (double) (i - 1) / (a.length - 1) + (double) 0.1 * x;
            }
        }
                randomPosition1 = (r.nextInt(c.length));
                        a[randomPosition] = c[randomPosition1];
                         RK = new rk(a);
                         RK.setPermutation(Orderings.randomKeyToAL(a));
                        RK.normalise();
                         a = RK.copyGene();
         d++;
          }while (d <numberofShakes);
                return RK.copyGene();

}


         public static rk shakeRK1 (rk solution, int numberofShakes) {
//        ArrayList<double[]> list = new ArrayList<double[]>();
            
            int randomPosition = 0;
         int randomPosition1 = 0;
            Random r = new Random();
                 double[] a = Arrays.copyOf(solution.copyGene(), solution.copyGene().length);
                  rk RK = new rk(a);
          int d =0;
          do{
                 do {
            randomPosition = (r.nextInt(a.length));
             randomPosition1 = (r.nextInt(a.length));
            } while (randomPosition ==randomPosition1);
           
   
        //n +1 possible insert positions
        double[] c = new double[a.length + 1];
        double x = (double) 1 / (a.length - 1);
        for (int i = 0; i < a.length + 1; i++) {
            //  x is the difference between each element of the rescaled RK
            if (i == 0) {
                c[i] = (double) 0 - 0.1 * x;
            } else {
                c[i] = (double) (i - 1) / (a.length - 1) + (double) 0.1 * x;
            }
        }

                        a[randomPosition] = c[randomPosition1];
                          RK = new rk(a);
                         RK.setPermutation(Orderings.randomKeyToAL(a));
                        RK.normalise();
                        a = RK.copyGene();
                        System.out.println(Arrays.toString(a));
                        System.out.println(Arrays.toString(Orderings.randomKeyToAL(a)));
         d++;
          }while (d <numberofShakes);
                return RK;

}

}