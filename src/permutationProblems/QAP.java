package permutationProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QAP extends PermutationProblem{

	public int[][] matrixA;
	public int[][] matrixB;
	
	public QAP(String path) {
		super(path);

		try {
			BufferedReader reader = new BufferedReader (new FileReader (new File (path)));

			String templine = " ";
			String separator = ",";
			int line=0;

			this.optimum = Integer.parseInt(reader.readLine().trim());
			
			this.theSize = Integer.parseInt(reader.readLine().trim());
			matrixA = new int[this.theSize][this.theSize];
			matrixB = new int[this.theSize][this.theSize];

			//System.out.println("size: "+problemSize);

			templine = reader.readLine().trim(); // blank line

			line =0;
			for(int i = 0; i< this.theSize; i++){
				templine = reader.readLine().trim();
				String[] lineArray = templine.split(separator);
				for(int j=0; j<lineArray.length; j++){
					int val = Integer.parseInt(lineArray[j]);
					matrixA[line][j] = val;
				}
				line++;
				//		System.out.println("line: "+templine);	
			}

			templine = reader.readLine().trim(); // blank line

			line =0;
			for(int i = 0; i< this.theSize; i++){
				templine = reader.readLine().trim();
				String[] lineArray = templine.split(separator);
				for(int j=0; j<lineArray.length; j++){
					int val = Integer.parseInt(lineArray[j]);
					matrixB[line][j] = val;
				}
				line++;
			}



		}   
		catch (IOException e) {System.out.println("Couldn't find file: " + path);}          


	}

	@Override
	public boolean isNatural() {
		return false;
	}

	@Override
	public double evaluate(int[] chromosome) {
		int total = 0;
		for (int i=0; i<this.theSize; i++){
			for (int j=0; j<this.theSize; j++){
				total += this.matrixA[i][j] * this.matrixB[chromosome[i]][chromosome[j]];
			}
		}
		
		double fitness = (double)total;
		return fitness;
	}

	@Override
	public double optimalFitness() {
		return this.optimum;
	}

}
