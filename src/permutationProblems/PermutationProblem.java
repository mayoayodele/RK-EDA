/*
 * BinaryProblem.java
 * Generic structure of a problem (fitness function) using a bit string representation
 * Created on 07 March 2006, 10:54
 */

package permutationProblems;
import java.util.Calendar;
/**
 *
 * @author  sb
 */
public abstract class PermutationProblem
{
    /** The number of times this function has been called */
    protected int evals;
    public Calendar calstart;
    protected int theSize;
    protected double optimum;
    
    /** Creates a new instance of BinaryProblem */
    public PermutationProblem(String filepath)
    {
    	calstart = Calendar.getInstance();
        evals = 0;
    }
    
    public abstract boolean isNatural();
    
    /** Returns the fitness of a given string of booleans, make sure evals is updated by this method */
    public abstract double evaluate(int[] chromosome);
    
    /** Evaluate a whole population at once, default implementation just calls evaluate() repeatedly */
    public double[] evaluatePopulation(int[][] population)
    {
        double[] rval = new double[population.length];
        for (int i = 0; i < population.length; i++)
            rval[i] = evaluate(population[i]);
        return rval;
    }
    
    /** Retrieve the number of evaluations so far */
    public int getEvals()
    {
        return evals;
    }
    
    /** Resets eval count to zero */
    public void resetEvals()
    {
        evals = 0;
    }
    
    public int problemSize()
    {
        return theSize;
    }
    
    /** The optimal fitness of we could have for an individual of given size */
    public abstract double optimalFitness();
    
    /** Returns a univariate structure, override this for more complex structures */
    public int[][] getStructure()
    {
        int[][] rval = new int[theSize][1];
        for (int i = 0; i < rval.length; i++)
            rval[i][0] = i;
        
        return rval;
    }

    public Calendar getcalstart() {
        //return 1.0;
        return calstart;
}
}
