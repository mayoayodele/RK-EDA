
package output;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class RunResult {

    String r0;
    String r1;
    Double r2;
//    int r2;
    Double r3;
//    double r4;
//    double r5;
//    int r6;

    public RunResult(String inputfile) {

        String unused;

        String templine = tail(new File(inputfile));
//        System.out.println(templine);
        
        StringTokenizer t = new StringTokenizer(templine, "\t");
         r0 = t.nextToken(); // prob name
         r1 = t.nextToken(); //solution String
         r2 =  Double.parseDouble(t.nextToken());//fitness
      unused = t.nextToken();//fitness
      r3 =  Double.parseDouble(t.nextToken());//FEs
//        unused = t.nextToken(); 
      
    }

    public String getProbName() {
        return r0;
    }
    
      public String getSolutionString() {
        return r1;
    }

    public Double getFitness() {
        return r2;
    }

    public Double getFEstoBest() {
        return r3;
    }

    public static String tail(File file) {
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile(file, "r");
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }

                sb.append((char) readByte);
            }

            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null) {
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
            }
        }
    }

}
