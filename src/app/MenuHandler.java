package app;

import java.util.Scanner;

import core.KnowledgeBase;
import usr.User;
import utils.FileHandler;
import utils.Logger;

/**
 * Handler main actions and functionality of application.
 */
public class MenuHandler {

    private KnowledgeBase kb;
    private Scanner scanner;
    private Logger logger;
    private String filename;

    /**
     * Constructor for MenuHandler
     * 
     * @param kb        Knowledge Base implementation type
     * @param logger    Writing to log file.
     * @param filename  Log file for exporting logging actions.
     */
    public MenuHandler(KnowledgeBase kb, Logger logger, String filename) {
        this.filename = filename;
        this.logger = logger;
        this.kb = kb;
        this.scanner = new Scanner(System.in);

    }

    /**
     * Runs a list a actions for user to choose from
     */
    public void run(){

        int choice;

        do {
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file.");
            System.out.println("2. Add a new statement to the knowledge base.");
            System.out.println("3. Search for an item in the knowledge base by term.");
            System.out.println("4. Search for an item in the knowledge base by term and sentence.");
            System.out.println("5. Generate login history.");
            System.out.println("6. Clear history.");
            System.out.println("7. Quit");
            System.err.print("Enter choice: ");

            choice = scanner.nextInt();

            switch(choice) {

                case 1 -> loadFromFile();
                case 2 -> addOrUpdateStatement();
                case 3 -> searchByTerm();
                case 4 -> searchByTermAndSentence();
                case 5 -> generateHistory();
                case 6 -> clearHistory();
                case 7 -> System.out.println("Exiting application. See you later!");
                default -> System.out.println("Not a valid choice. Please try again.");
            }
            
        } while (choice != 7);

        
    }

    /**
     * Loads knowledge base file.
     */
    public void loadFromFile() {

        System.out.print("Enter knowledge base file name: ");
        String filename = scanner.next();

        try {
            FileHandler.loadFromFile(filename, kb);
            logger.log (filename + " loaded successfully.");

        } catch (Exception e) {
            System.out.println("Error details: \n " + e.getMessage());
        }

    }

    /**
     * Adds or updates statements to knowledge base
     */
    public void addOrUpdateStatement(){

        System.out.println("Enter term:");
        String term = scanner.next();

        System.out.println("Enter sentence:");
        scanner.next();
        String sentence = scanner.nextLine();
        
        System.out.print("Enter confidence score (0 or 1):");
        double confidence = scanner.nextDouble();

        kb.addOrUpdateEntry(term, sentence, confidence);

        logger.log("Added/Updated statement.");

    }

    /**
     * Searches an item by the term.
     */
    public void searchByTerm(){

        System.out.println("Enter term to search:");
        String term = scanner.next();

        var entry = kb.searchByTerm(term);

        String result;

        if (entry != null) {
            result = "term found"; 
            System.out.println(entry);
        } 
        else {
             result = "term not found";
             System.out.println(result);
        }
            

        logger.log("Term searched: " + term + " -> " + result);

    }

    /**
     * Searches an item by term and sentence
     */
    public void searchByTermAndSentence(){

        System.out.println("Enter term to search:");
        String term = scanner.next();

        System.out.println("Enter sentence: ");
        scanner.nextLine();
        String sentence = scanner.nextLine();

        double confidence = kb.searchByTermAndSentence(term, sentence);

        String result;

        if (confidence != -1) {
            result = "Confidence score: " + confidence;
            System.out.println(result);
        } else {
            result = "Term and sentence combination";
            System.out.println(result);
        }

        logger.log("Term: " + term + "\nSentence: " + sentence + "\nResult ->" + confidence); 


    }

    /**
     * Generates user-specific logging history.
     */
    public void generateHistory() {

        FileHandler.displayFile(filename);
        System.out.println("");
        System.out.println("History generation complete.");

    }

    /**
     * Clears all previous logging history of user.
     */
    public void clearHistory() {

        FileHandler.clearFile(filename);

        logger.log("History cleared successfully");

    }

}