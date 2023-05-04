package havefun.randomfileselector;

import java.io.File;
import java.util.Random;

public class RandomFileSelector {
    public static void main(String[] args) {
        // Define the directory to search for files
        File havefunDir = new File("src/main/java/havefun");
        System.out.println(havefunDir.getAbsolutePath());

        // Get all files in the directory and its subdirectories
        File[] files = havefunDir.listFiles();
        if (files != null && files.length > 0) {
            // Select a random file from the list of files
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];

            // If the random file is a directory, select a random file from its
            // subdirectories
            while (randomFile.isDirectory()) {
                File[] subFiles = randomFile.listFiles();
                if (subFiles != null && subFiles.length > 0) {
                    randomFile = subFiles[random.nextInt(subFiles.length)];
                } else {
                    System.out.println("No files found in the directory.");
                    return;
                }
            }

            // Print the name of the selected file
            System.out.println(randomFile.getName());
        } else {
            System.out.println("No files found in the directory.");
        }
    }
}
