package permutationProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import utils.ArrayUtils;

public class TSP extends PermutationProblem{

	public double[][] distances;
	public double[][] coordinates;

	public TSP(String path) {
		super(path);

		ArrayList<ArrayList<Double>> proc = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> coo = new ArrayList<ArrayList<Double>>();

		try {
			BufferedReader reader = new BufferedReader (new FileReader (new File (path)));

			String templine = " ";
			String separator = ",";
			int line=0;

			this.optimum = Integer.parseInt(reader.readLine().trim());
			this.theSize = Integer.parseInt(reader.readLine().trim());

			int hasDistance = Integer.parseInt(reader.readLine().trim());

			coordinates = new double[this.theSize][2];

			if(hasDistance == 1){

				while(reader.ready()){

					ArrayList<Double> arr = new ArrayList<Double>();
					templine = reader.readLine().trim();
					StringTokenizer t = new StringTokenizer(templine,separator);
					String[] lineArray = templine.split(separator);
					for(int i=0; i<lineArray.length; i++){
						int val = Integer.parseInt(lineArray[i]);
						arr.add((double) val);
					}
					proc.add(arr);

					line++;
				}


			}
			else{
				while(reader.ready()){

					ArrayList<Double> arr = new ArrayList<Double>();
					templine = reader.readLine().trim();
					StringTokenizer t = new StringTokenizer(templine,separator);
					String[] lineArray = templine.split(separator);
					for(int i=1; i<lineArray.length; i++){
						Double val = Double.parseDouble(lineArray[i]);
						arr.add(val);
					}
					coo.add(arr);

					line++;
				}

				for(int i=0; i<coo.size(); i++){
					coordinates[i] = ArrayUtils.ArrayListToArrayDouble(coo.get(i));
				}

				for(int i = 0; i<coo.size(); i++){
					ArrayList<Double> arr = new ArrayList<Double>();
					for(int j = 0; j<coo.size(); j++){
						arr.add(getDistanceFromCoordinate(i,j));
					}
					proc.add(arr);
				}
			}
		}
		catch (IOException e) {System.out.println("Couldn't find file: " + path);}         

		distances = new double[proc.size()][proc.get(0).size()];
		for(int i=0; i<proc.size(); i++){
			distances[i] = ArrayUtils.ArrayListToArrayDouble(proc.get(i));

		}


//		this.theSize = this.theSize - 1;
	}

	
	public double getDistanceFromCoordinate(int loc1, int loc2){
		double dist = 0.0;

		double term1 = Math.pow(Math.abs((this.coordinates[loc1][0]-this.coordinates[loc2][0])), 2);
		double term2 = Math.pow(Math.abs((this.coordinates[loc1][1]-this.coordinates[loc2][1])), 2);
		dist = Math.round(Math.sqrt(term1 + term2));
		return dist;
	}
	
	@Override
	public boolean isNatural() {
		return false;
	}

//	@Override
//	public double evaluate(int[] chromosome) {
//		ArrayList<Integer> newInd = new ArrayList<Integer>();
//		newInd.add(0);
//		for(int i=0; i<chromosome.length-1; i++){
//			newInd.add(chromosome[i]+1);
//		}
//
//		double score = 0.0;
//
//		for(int i=0; i<newInd.size()-1; i++){
//			score += distances[newInd.get(i)][newInd.get(i+1)];
//		}
//
//		score += distances[newInd.get(newInd.size()-1)][newInd.get(0)];
//		return score;
//	}

        @Override
	public double evaluate(int[] chromosome) {
		ArrayList<Integer> newInd = new ArrayList<Integer>();
//		newInd.add(0);
		for(int i=0; i<chromosome.length; i++){
			newInd.add(chromosome[i]);
		}
                
//                System.out.println("newInd");
//                System.out.println(newInd);
		double score = 0.0;

		for(int i=0; i<newInd.size()-1; i++){
			score += distances[newInd.get(i)][newInd.get(i+1)];
		}

		score += distances[newInd.get(newInd.size()-1)][newInd.get(0)];
		return score;
	}

        
	@Override
	public double optimalFitness() {
		return this.optimum;
	}

//    public double More ...distance(TSPInstance problem) {
//		DistanceTable distanceTable = problem.getDistanceTable();
//		double result = 0.0;
//		
//		for (int i = 0; i < nodes.size(); i++) {
//			result += distanceTable.getDistanceBetween(get(i), get(i+1));
//		}
//		
//		return result;
//	}    
        
}
