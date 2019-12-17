/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

/**
 *
 * @author 1013288
 */
public class GenerateParams {

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    public static void main(String[] args) {

        int populationSize;
        String path;
        String probName;
        int FEs;
        int truncSize;
        int elitism; //0 or 1;
        String resultsPath;
        String saveAs;
        double variance;
        int ps = 0;
        int numberOfruns = 10;
        populationSize = 1000;
        path = "../src/data/";
//        path = "../src/taillard_instances/";
        resultsPath = "../results/";
        FEs = 0;

//          //list the names of the problems you want to apply the algorithm to.
        //FSSP instances in tailard_instances sub folder.
        //FSSP size 20
//        String[] problemNames = {"tai20_5_0.fsp", "tai20_5_1.fsp", "tai20_5_2.fsp", "tai20_5_3.fsp", "tai20_5_4.fsp",
//            "tai20_5_5.fsp", "tai20_5_6.fsp", "tai20_5_7.fsp", "tai20_5_8.fsp", "tai20_5_9.fsp",
//            "tai20_10_0.fsp", "tai20_10_1.fsp", "tai20_10_2.fsp", "tai20_10_3.fsp", "tai20_10_4.fsp",
//            "tai20_10_5.fsp", "tai20_10_6.fsp", "tai20_10_7.fsp", "tai20_10_8.fsp", "tai20_10_9.fsp",
//            "tai20_20_0.fsp", "tai20_20_1.fsp", "tai20_20_2.fsp", "tai20_20_3.fsp", "tai20_20_4.fsp",
//            "tai20_20_5.fsp", "tai20_20_6.fsp", "tai20_20_7.fsp", "tai20_20_8.fsp", "tai20_20_9.fsp"};
        
        //FSSP size 50
//         String[] problemNames = {"tai50_5_0.fsp", "tai50_5_1.fsp", "tai50_5_2.fsp", "tai50_5_3.fsp", "tai50_5_4.fsp",
//            "tai50_5_5.fsp", "tai50_5_6.fsp", "tai50_5_7.fsp", "tai50_5_8.fsp", "tai50_5_9.fsp",
//            "tai50_10_0.fsp", "tai50_10_1.fsp", "tai50_10_2.fsp", "tai50_10_3.fsp", "tai50_10_4.fsp",
//            "tai50_10_5.fsp", "tai50_10_6.fsp", "tai50_10_7.fsp", "tai50_10_8.fsp", "tai50_10_9.fsp",
//            "tai50_20_0.fsp", "tai50_20_1.fsp", "tai50_20_2.fsp", "tai50_20_3.fsp", "tai50_20_4.fsp",
//            "tai50_20_5.fsp", "tai50_20_6.fsp", "tai50_20_7.fsp", "tai50_20_8.fsp", "tai50_20_9.fsp"};

            //FSSP size 100
//         String[] problemNames = {"tai100_5_0.fsp", "tai100_5_1.fsp", "tai100_5_2.fsp", "tai100_5_3.fsp", "tai100_5_4.fsp",
//            "tai100_5_5.fsp", "tai100_5_6.fsp", "tai100_5_7.fsp", "tai100_5_8.fsp", "tai100_5_9.fsp",
//            "tai100_10_0.fsp", "tai100_10_1.fsp", "tai100_10_2.fsp", "tai100_10_3.fsp", "tai100_10_4.fsp",
//            "tai100_10_5.fsp", "tai100_10_6.fsp", "tai100_10_7.fsp", "tai100_10_8.fsp", "tai100_10_9.fsp",
//            "tai100_20_0.fsp", "tai100_20_1.fsp", "tai100_20_2.fsp", "tai100_20_3.fsp", "tai100_20_4.fsp",
//            "tai100_20_5.fsp", "tai100_20_6.fsp", "tai100_20_7.fsp", "tai100_20_8.fsp", "tai100_20_9.fsp"};
       
        //FSSP size 200
//         String[] problemNames = {"tai200_10_0.fsp", "tai200_10_1.fsp", "tai200_10_2.fsp", "tai200_10_3.fsp", "tai200_10_4.fsp",
//            "tai200_10_5.fsp", "tai200_10_6.fsp", "tai200_10_7.fsp", "tai200_10_8.fsp", "tai200_10_9.fsp",
//            "tai200_20_0.fsp", "tai200_20_1.fsp", "tai200_20_2.fsp", "tai200_20_3.fsp", "tai200_20_4.fsp",
//            "tai200_20_5.fsp", "tai200_20_6.fsp", "tai200_20_7.fsp", "tai200_20_8.fsp", "tai200_20_9.fsp"};
      
        //FSSP size 500
//         String[] problemNames = { "tai500_20_0.fsp", "tai500_20_1.fsp", "tai500_20_2.fsp", "tai500_20_3.fsp", "tai500_20_4.fsp",
//            "tai500_20_5.fsp", "tai500_20_6.fsp", "tai500_20_7.fsp", "tai500_20_8.fsp", "tai500_20_9.fsp"};
     
        
    //common permutation problem in data subfolder
          String[] problemNames = {"tsp-bays29.tsp", "tsp-berlin52.tsp", "tsp-fri26.tsp", "tsp-dantzig42.tsp", "tai20_5_0.fsp", "tai20_5_1.fsp","tai20_10_0.fsp", "tai20_10_1.fsp", "qap-tai15a.dat", "qap-tai40a.dat",
            "qap-tai15b.dat", "qap-tai40b.dat", "lop-IO-t65b11xx.txt", "lop-IO-be75np.txt", "lop-IO-be75oi.txt"};
//        "tai50_10_0.fsp", "tai50_10_1.fsp", "tai100_20_0.fsp", "tai100_20_1.fsp" };
        int trunc = 0;
//       double[] stdevs = {0.2};
        double[] stdevs = {0.15};
//         double[] stdevs = {0.2, 0.5, 0.8};
//        double[] stdevs = {0};
        String out = "";
        int[] elit = {0};
        elitism = 0;

        for (int j = 0; j < problemNames.length; j++) {
            probName = problemNames[j];
            String a;
            if (probName.contains(".fsp")) {
                a = probName.substring((probName.indexOf("i") + 1), (probName.indexOf("_")));
            } else if (probName.contains("tsp")) {
                a = probName.substring((probName.indexOf(".tsp") - 2), (probName.indexOf(".tsp")));
            } else if (probName.contains("qap")) {
                a = probName.substring((probName.indexOf(".dat") - 3), (probName.indexOf(".dat") - 1));

            } else if (probName.contains(".txt")) {
                a = probName.substring((probName.indexOf(".txt") - 4), (probName.indexOf(".txt") - 2));
                if (probName.contains("65")) {
                    a = "65";
                }
            } else {
                System.out.println("error");
                a = "0";
            }

            int[] popSize = {(int) Math.round(Integer.parseInt(a) * 10)};
            ps = popSize.length;
//            FEs = 1000 * (Integer.parseInt(a) * Integer.parseInt(a));

            if (probName.replace("-", "_").contains("tai20_5")) {
                FEs = 182224100;
            } else if (probName.replace("-", "_").contains("tai20_10")) {
                FEs = 224784800;
            } else if (probName.replace("-", "_").contains("tai20_20")) {
                FEs = 256896400;
            } else if (probName.replace("-", "_").contains("tai50_5")) {
                FEs = 220712150;
            } else if (probName.replace("-", "_").contains("tai50_10")) {
                FEs = 256208100;
            } else if (probName.replace("-", "_").contains("tai50_20")) {
                FEs = 275954150;
            } else if (probName.replace("-", "_").contains("tai100_5")) {
                FEs = 235879800;
            } else if (probName.replace("-", "_").contains("tai100_10")) {
                FEs = 266211000;
            } else if (probName.replace("-", "_").contains("tai100_20")) {
                FEs = 283040000;
            } else if (probName.replace("-", "_").contains("tai200_10")) {
                FEs = 272515500;
            } else if (probName.replace("-", "_").contains("tai200_20")) {
                FEs = 287728850;
            } else if (probName.replace("-", "_").contains("tai500_20")) {
                FEs = 260316750;
            }

            // we tried to run our algorithm based on a fifth of the fitness evaluation used in literature
        FEs = (int)(0.2*FEs);
        
         FEs = 1000 * (Integer.parseInt(a) * Integer.parseInt(a)); 
            if (probName.contains(".fsp")) {
                path = "../src/taillard_instances/";
            } else {
                path = "../src/data/";
            }

            for (int i = 0; i < popSize.length; i++) {
                //set population size
//                populationSize = popSize[i];
                populationSize = 50;
                //set truncation size as a fraction of population size
                int[] tSize = {(int) Math.round(populationSize * 0.10)};
                trunc = tSize.length;
                for (int k = 0; k < tSize.length; k++) {
                    truncSize = tSize[k];
                    for (int l = 0; l < stdevs.length; l++) {
                        //set variance value
//                        variance = stdevs[l];
                        variance = 1/(3.14 *Math.log10(Integer.parseInt(a)));
                        for (int q = 0; q < elit.length; q++) {
                            //set elitism
                            elitism = elit[q];
                            for (int m = 0; m < numberOfruns; m++) {
                                // name the result file
                                saveAs = probName.replace(".tsp", "").replace(".dat", "").replace(".txt", "").replace(".fsp", "").replace("-", "_") + "P" + populationSize + "T" + truncSize + "V" + variance + "e" + elitism + "run" + m + ".txt";
                                int s = (j * popSize.length * tSize.length * stdevs.length * elit.length * numberOfruns)
                                        + (i * tSize.length * stdevs.length * elit.length * numberOfruns)
                                        + (k * stdevs.length * elit.length * numberOfruns)
                                        + (l * elit.length * numberOfruns)
                                        + (q * numberOfruns) + m + 1;
                                out += ("PARAMS[" + s + "]=\"" + populationSize + " " + path + " " + probName + " "
                                        + FEs + " " + truncSize + " " + elitism + " " + variance + " " + resultsPath
                                        + " " + saveAs + "\"" + "\n");

//      
                            }
                        }
                    }

                }
            }
        }
        System.out.println("!/bin/bash\n"
                + "#$ -N RKEDA\n"
                + "#$ -q serial.q\n"
                + "#$ -o out/RKEDA.$TASK_ID.dat\n"
                + "#$ -e err/RKEDA.$TASK_ID.dat\n"
                + "#$ -cwd\n"
                + "#$ -t 1-" + (ps * problemNames.length * trunc * stdevs.length * elit.length * numberOfruns) + "\n");
        System.out.println(out);

        System.out.println("java run.RunRKEDA ${PARAMS[$SGE_TASK_ID]}");
    }

}
