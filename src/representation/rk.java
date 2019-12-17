/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.Arrays;
import java.util.LinkedList;
import utils.Orderings;

/**
 *
 * @author 1013288
 */
public class rk {

    public double[] randomkeys;
    public double fitness;
     public int[] permutation;

    public rk(double[] randomkeys) {

        this.randomkeys = Arrays.copyOf(randomkeys, randomkeys.length);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int[] getPermutation() {
       return  Arrays.copyOf(permutation, permutation.length);
//        return permutation;
    }

    public void setPermutation(int[] permutation) {
        this.permutation = permutation;
    }

 

    public double[] copyGene() {

        return Arrays.copyOf(randomkeys, randomkeys.length);
    }

    public rk copyOf() {

        rk rk1 = new rk(this.copyGene());
        rk1.fitness = this.fitness;
        rk1.setPermutation(this.getPermutation());
        return rk1;
    }
    
    public void normalise()
    {
     randomkeys  = Arrays.copyOf(Orderings.normaliseRanks(permutation), permutation.length) ;
    }

    public String print()
    {
        return ("RK: " + Arrays.toString(this.randomkeys) + "\n Permutation: " + Arrays.toString(this.permutation) + "\n Fitness: " + this.fitness);
    }
    
}
