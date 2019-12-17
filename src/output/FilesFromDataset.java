/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.io.File;
import java.util.LinkedList;

/**
 *
 * @author 1013288
 */
public class FilesFromDataset {

    LinkedList<String> filePaths;
    LinkedList<String> folderPaths = new LinkedList<String>();

    public FilesFromDataset(String datasetPath) {
        filePaths = new LinkedList<String>();
        File folder = new File(datasetPath);
        File[] listOfFolders = folder.listFiles();
        for (int y = 0; y < listOfFolders.length; y++) {
            String folderName = listOfFolders[y].getName();
            if (listOfFolders[y].isDirectory() && !folderName.toLowerCase().contains("store")) {
//                System.out.println("Directory " + folderName);
                String path = datasetPath + folderName;
                folderPaths.add(path);
            }
        }

    }

    public FilesFromDataset(String datasetPath, String folderName) {
        filePaths = new LinkedList<String>();
//        folderPaths =new LinkedList<String>(); 
        String path = datasetPath + folderName;
        folderPaths.add(path);

    }

    public LinkedList<String> getFolderPaths() {
        return folderPaths;
    }

    public LinkedList<String> getAllFilePaths() {
//        filePaths = new LinkedList<String>();
        System.out.println("folderPaths: " + folderPaths);
        for (int i = 0; i < folderPaths.size(); i++) {
            String path = folderPaths.get(i);
            File folder1 = new File(path);
            File[] listOfFiles = folder1.listFiles();
            for (int z = 0; z < listOfFiles.length; z++) {
                String nameOfFile = listOfFiles[z].getName();
                if (listOfFiles[z].isFile() && !nameOfFile.toLowerCase().contains("store")) {
                    //                       System.out.println("File " + nameOfFile);
                    String filePath = path + "/" + nameOfFile.replace("._", "");
                    filePaths.add(filePath);
                }
            }
        }
        return filePaths;
    }

      
        
          public LinkedList<String> getAllFilePaths1(String datasetPath, String folderName ) {
//        filePaths = new LinkedList<String>();
              String path = datasetPath + folderName;

            File folder1 = new File(path);
            File[] listOfFiles = folder1.listFiles();
            for (int z = 0; z < listOfFiles.length; z++) {
                String nameOfFile = listOfFiles[z].getName();
                if (listOfFiles[z].isFile() && !nameOfFile.toLowerCase().contains("store")) {
                    //                       System.out.println("File " + nameOfFile);
                    String filePath = path + "/" + nameOfFile.replace("._", "");
                    if (!filePaths.contains(filePath)) {
                        filePaths.add(filePath);
                    }
                    
                }
            }
        
        return filePaths;
    }

    public LinkedList<String> getFilePaths(int numberOfFilesPerFolder) {
        for (String folderPath : folderPaths) {
            int x = 0;
            String path = folderPath;
            File folder1 = new File(path);
            File[] listOfFiles = folder1.listFiles();
            for (File listOfFile : listOfFiles) {
                if (x >= numberOfFilesPerFolder) {
                    break;
                }
                String nameOfFile = listOfFile.getName();
                if (listOfFile.isFile() && !nameOfFile.toLowerCase().contains("store")) {
                    //                       System.out.println("File " + nameOfFile);
                    String filePath = path + "//" + nameOfFile.replace("._", "");
                    filePaths.add(filePath);
                    x++;
                }
            }
        }
        return filePaths;
    }

}
