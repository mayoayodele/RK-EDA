package run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        String filePath = "./src/all.sh";
//                    String filePath = "./src/fssp50.sh";
        //                    String filePath = "./src/fssp100.sh";
        //                    String filePath = "./src/fssp200.sh";
        //                    String filePath = "./src/fssp500.sh";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = br.readLine()) != null) {

                if (line.contains("PARAMS") && !line.contains("java")) {
                    String line1 = line.substring(line.indexOf("\"") + 1, line.length() - 1);

                    String[] parts = line1.split("\\s");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].contains("..")) {
                            parts[i] = parts[i].replace("..", ".");
                        }

                    }
                    System.out.println(Arrays.toString(parts));
                    run.RunRKEDA.main(parts);
                }
            }
            br.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
