package utils;

import java.io.FileWriter;
import java.io.BufferedReader; // used for large files
import java.io.FileReader;

import core.KnowledgeBase;
import usr.User;

/**
 * Procedure for all file handling.
 */
public class FileHandler {

    /**
     * Loads knowledge base from file.
     * 
     * @param filename      The file name.
     * @param knowledgeBase The knowledgebase implementation.
     */
    public static void loadFromFile(String filename, KnowledgeBase knowledgeBase) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] fields = line.split("\t");

                if (fields.length == 3) {
                    String term = fields[0].trim();
                    String sentence = fields[1].trim();
                    double confidence = Double.parseDouble(fields[2].trim());
                    knowledgeBase.addOrUpdateEntry(term, sentence, confidence);
                }


            }

            reader.close();

        } catch (Exception e) {
            System.err.println("Error details: \n" + e.getMessage());
        }

    }


    /**
     * Display contents of file to terminal.
     * 
     * @param filename  The filename.
     */
    public static void displayFile(String filename) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
            
        } catch (Exception e) {
            System.err.println("Error details: \n" + e.getMessage());
        }


    }

    /**
     * Writes to file.
     * 
     * @param filename  The filename.
     * @param message   The message appended to file.
     */
    public static void writeToFile(String filename, String message){

        try {
            FileWriter writer = new FileWriter(filename, true); // create if does not exist
            writer.write("\n" + message);
            writer.close();

        } catch (Exception e) {
            System.err.println("Error details: \n" + e.getMessage());
        }
        

    }


    /**
     * Clear file contents.
     * 
     * @param filename The filename
     */
    public static void clearFile(String filename){

        try {
            FileWriter writer = new FileWriter(filename, false); // clear file
            System.out.println("File cleaning was successful.");
            writer.close();

        } catch (Exception e) {
            System.err.println("Error details: \n" + e.getMessage());
        }
    }


    /**
     * Loads userdetails from file.
     * 
     * @param filename  The file name
     * @param user      The User class
     */
    public static void loadFromFile(String filename, User user) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[1].trim();
                String password = fields[2].trim();
                user.addDetails(username, password);
            }

            reader.close();

            
        } catch (Exception e) {
            System.out.println("Error details " + e.getMessage());
        }
    }
}