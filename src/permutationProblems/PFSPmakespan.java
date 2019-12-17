package permutationProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import utils.ArrayUtils;

public class PFSPmakespan extends PermutationProblem{

	public int[][] processingTimes;
           Double optimum = 0.0;
        
           public PFSPmakespan(String path) {
		super(path);
          
        ArrayList<ArrayList<Integer>> proc = new ArrayList<ArrayList<Integer>>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            String line = " ";
            int counter = 0;
            int jobs = 0;
            int machines = 0;
            while (reader.ready()) {
               line = reader.readLine().trim(); 
                 if (counter ==1) {
                    String parts1[] = line.trim().split("\\s+");
                    jobs =  Integer.parseInt(parts1[0]);
                             machines = Integer.parseInt(parts1[1]);
                            optimum = Double.parseDouble(parts1[3]);
//                            System.out.println("optimum: " + optimum);
//                            System.out.println("jobs: " + jobs);
//                            System.out.println("machines:  " + machines);
                            
                              }
                if (counter >2 && counter <= (machines + 2) ) {
                 ArrayList<Integer> arr = new ArrayList<Integer>();
//                    System.out.println("line");
//                    System.out.println(line);
                String lineArray[] = line.trim().split("\\s+");
                for (int i = 0; i < jobs; i++) {
//                    System.out.println("arrays");
//                    System.out.println(lineArray[i]);
                    int val = Integer.parseInt(lineArray[i]);
                    arr.add(val);
                }
                proc.add(arr);

               
            }
                 counter++;
                }
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + path);
        }

        processingTimes = new int[proc.size()][proc.get(0).size()];
        for (int i = 0; i < proc.size(); i++) {
            processingTimes[i] = ArrayUtils.ArrayListToArray(proc.get(i));
//            System.out.println(Arrays.toString(processingTimes[i]));
        }
         this.theSize = processingTimes[0].length;
     }

       /*
    public PFSPmakespan(String path) {
		super(path);
             Double optimum;
        ArrayList<ArrayList<Integer>> proc = new ArrayList<ArrayList<Integer>>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            String line = " ";
            int counter = 0;

            while (reader.ready()) {
               line = reader.readLine().trim(); 
                 if (counter ==1) {
                    String parts1[] = line.trim().split("\\s+");
                            optimum = Double.parseDouble(parts1[3]);
                              }
                if (counter >2) {
                 ArrayList<Integer> arr = new ArrayList<Integer>();
                String lineArray[] = line.trim().split("\\s+");
                for (int i = 0; i < lineArray.length; i++) {
                    int val = Integer.parseInt(lineArray[i]);
                    arr.add(val);
                }
                proc.add(arr);

               
            }
                 counter++;
                }
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + path);
        }

        processingTimes = new int[proc.size()][proc.get(0).size()];
        for (int i = 0; i < proc.size(); i++) {
            processingTimes[i] = ArrayUtils.ArrayListToArray(proc.get(i));
//            System.out.println(Arrays.toString(processingTimes[i]));
        }
         this.theSize = processingTimes[0].length;
     }
*/
   
	
//	public PFSPmakespan(String path) {
//		super(path);
//		//System.out.println(path);
//		
//		ArrayList<ArrayList<Integer>> proc = new ArrayList<ArrayList<Integer>>();
//		
//		 try {
//            BufferedReader reader = new BufferedReader (new FileReader (new File (path)));
//             
//            String templine = " ";
//            String separator = ",";
//         	int line=0;
//
//			optimum = Double.parseDouble(reader.readLine().trim());
//			templine = reader.readLine().trim(); // blank line
//
//         	
//         	while(reader.ready()){
//         		
//         		ArrayList<Integer> arr = new ArrayList<Integer>();
//         		templine = reader.readLine().trim();
//         		StringTokenizer t = new StringTokenizer(templine,separator);
//         		String[] lineArray = templine.split(separator);
//         		for(int i=0; i<lineArray.length; i++){
//         			int val = Integer.parseInt(lineArray[i]);
//         			arr.add(val);
//         		}
//         		proc.add(arr);
//         		
//         		line++;
//         	}
//   
//         }   
//         catch (IOException e) {System.out.println("Couldn't find file: " + path);}          
//		
//		 processingTimes = new int[proc.size()][proc.get(0).size()];
//		 for(int i=0; i<proc.size(); i++){
//			 processingTimes[i] = ArrayUtils.ArrayListToArray(proc.get(i));
///*			 for(int j=0; j<proc.get(i).size(); j++){
//				System.out.print(proc.get(i).get(j)+" - "); 
//			 }
//			 System.out.print("\n");*/
//		 }
//		 
//		 this.theSize = this.processingTimes[0].length;
//	}

	@Override
	public boolean isNatural() {
		return false;
	}

	@Override
	public double evaluate(int[] arg0) {
		double score = 0.0;
		
		int[][] completionTimes= new int[processingTimes.length][processingTimes[0].length];
		boolean[][] completionTimesEvaluated= new boolean[processingTimes.length][processingTimes[0].length];
		score =  getCompletionTimeJobOnMachineWithCache(arg0.length-1, processingTimes.length-1, arg0, completionTimesEvaluated, completionTimes);
		
		return score;
	}

	@Override
	public double optimalFitness() {
		return this.optimum;
	}
	
	public int getCompletionTimeJobOnMachine(int pos, int machine, int[] permutation){
		int c = 0;
		int c1=0;
		int c2 =0;

		if(pos == 0 && machine == 0){
			c = processingTimes[0][permutation[0]];
		}

		else{

			//has previous job finished on same machine?
			if(pos != 0){
				c1 = getCompletionTimeJobOnMachine(pos-1,machine,permutation);
			} 
			else{
				c1 = 0;
			}

			//has current job finished on previous machine?
			if(machine != 0){
				c2 = getCompletionTimeJobOnMachine(pos,machine-1,permutation);
			}
			else{
				c2 = 0;	
			}

			// compare both c1 and c2: the largest value represent the time the job can actually start being processed on the specified machine
			// it is then needed to add the processing time.
			if (c1 >c2){
				c = c1+processingTimes[machine][permutation[pos]];
			}else{
				c = c2+processingTimes[machine][permutation[pos]];	
			}
		}

		return c;
	}


	public int getCompletionTimeJobOnMachineWithCache(int pos, int machine, int[] permutation, boolean[][] completionTimesEvaluated, int[][] completionTimes){
		int c = 0;
		int c1 = 0;
		int c2 = 0;

	//	System.out.println("**"+pos+"/"+machine+"/");
		String out = pos+"/"+machine+"/";
		if(completionTimesEvaluated[machine][permutation[pos]]){
			out+="cache";
			c = completionTimes[machine][permutation[pos]];
		}
		else{
			out+="nocache";
			if(pos == 0 && machine == 0){
				c = processingTimes[0][permutation[pos]];
			}

			else{

				//has previous job finished on same machine?
				if(pos != 0){
					c1 = getCompletionTimeJobOnMachineWithCache(pos-1,machine,permutation, completionTimesEvaluated, completionTimes);
				} 
				else{
					c1 = 0;
				}

				//has current job finished on previous machine?
				if(machine != 0){
					c2 = getCompletionTimeJobOnMachineWithCache(pos,machine-1,permutation, completionTimesEvaluated, completionTimes);
				}
				else{
					c2 = 0;	
				}

				// compare both c1 and c2: the largest value represent the time the job can actually start being processed on the specified machine
				// it is then needed to add the processing time.
				if (c1 >c2){
					c = c1+processingTimes[machine][permutation[pos]];
				}else{
					c = c2+processingTimes[machine][permutation[pos]];	
				}
			}

			completionTimesEvaluated[machine][permutation[pos]] = true;
			completionTimes[machine][permutation[pos]]  = c;
		}

	//	System.out.print(out+"/"+c+"\n");
		return c;
	}

}
