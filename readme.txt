Major Methods for the RK-EDA

* run.Main.java executes the program specific to fssp 

* runRKEDA(double) method of rkeda.RKEDA.java solves the fssp using a fitness function based on total flow time 

* define  perm problem as PFSPmakespan to change to makespan criteria 
		
	i.e change 	
		permProb = new PFSPmakespan(path + probName);
	to
            permProb = new PFSPtotalflowtime(path + probName);

* rkeda.RKEDA.java is initialised with the parameters for the algorithm.The parameters are
		populationSize - number of solutions in a population;
         path - path to the data (e,g ”../src/data/" or “../src/taillard_instances/“)
        probName - name of the problem file (e.g "fsp-tai20-5-0.txt" )
         FEs - number of fitness evaluations 
        truncSize - number of candidate solutions used to build the model (typically use 10% to 50% of the population size)
         elitism - takes value 0 or 1, 0 means you don't want to keep the best solution in the next generation.
         mutationRate - a float point value between 0 and 1 where 0 means no mutation is used. We have used 0 in the PPSN paper i.e we do not use mutation at all.
         resultsPath - this is where you want to save your results (e.g  "../results/" ), remember to create the specified folder (in the example I will create a folder named results in the root direction: RKEDA folder).
        saveAs - the name of the file for individual run (I used the name of the problem to make it easy to identify)
        initialStdev - this is the variance parameter, takes value between 0 and 1 (we have found values between 0.1 and 0.3 to be reasonable)
		
* output.GenerateParams.java prints out a script for running some configurations on many Flow Shop Scheduling Problem (fssp) problems. Copy the printed statements and save as a text file in src. set the path for the saved text file in run.Main2.java. The printed file can be saved as a .sh or even .txt file in src and executed on the cluster. It can also be executed locally from run.Main (remember to point to the parameter file saved above)

* permutataionProblems folder contains the evaluation functions for TSP, FSSP, LOP and QAP. 

* output.Summary.java summarises results based on several runs ( it generate the average), note that you need to set the number of runs as well as the results path.
output.Summary1.java is similar to one above but prints for each run.

* the results file ending in all.txt saves just the result for the final generation while the one without “all” saves for each generation.





