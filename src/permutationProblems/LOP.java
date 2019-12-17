package permutationProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import utils.ArrayUtils;

public class LOP extends PermutationProblem{

	public int[][] matrix;
	
	public LOP(String path) {
		super(path);
		
		ArrayList<ArrayList<Integer>> proc = new ArrayList<ArrayList<Integer>>();
		
		 try {
           BufferedReader reader = new BufferedReader (new FileReader (new File (path)));
            
           String templine = " ";
           String separator = " ";
        	int line=0;

			this.optimum = Integer.parseInt(reader.readLine().trim());
			//templine = reader.readLine().trim(); // blank line

        	int ii = 0;
        	int jj = 0;
        	
        	while(reader.ready()){
        		
        		ArrayList<Integer> arr = new ArrayList<Integer>();
        		templine = reader.readLine().trim();
        		StringTokenizer t = new StringTokenizer(templine,separator);
        		String[] lineArray = templine.split(separator);
        		
        		for(int i=0; i<lineArray.length; i++){
        			int val = Integer.parseInt(lineArray[i]);
        			arr.add(val);
        		}
        			
        		proc.add(arr);
        		
        		line++;
        	}
 
        }   
        catch (IOException e) {System.out.println("Couldn't find file: " + path);}          
		
		 matrix = new int[proc.size()][proc.get(0).size()];
		 for(int i=0; i<proc.size(); i++){
			 matrix[i] = ArrayUtils.ArrayListToArray(proc.get(i));
		 }
		 
		 this.theSize = this.matrix[0].length;
	}

	@Override
	public boolean isNatural() {
		return true;
	}

	@Override
	public double evaluate(int[] chromosome) {
		double score = 0.0;

		for(int i=0; i<chromosome.length-1; i++){
			for(int j=i+1; j<chromosome.length; j++){
				score += matrix[chromosome[i]][chromosome[j]];
			}
		}
		
		return score;
	}

	@Override
	public double optimalFitness() {
		return this.optimum;
	}

}
