package app;

import java.util.Scanner;

import core.KnowledgeBase;
import implementations.ArrayKB;
import implementations.BSTKB;
import usr.User;
import usr.UserDetails;
import utils.FileHandler;
import utils.Logger;

/**
 * Main application of knowledge base. 
 */

public class GenericsKBApp {

    private static String implementation;
    private static User user;
    private static Logger logger;
    private static String filename;

    public static void main(String[] args) {

        user = new User();

        // Initializer logger
        logger = new Logger("app.log");

        // Intialises userdetails file if does not exist 
        FileHandler.writeToFile("userdetails.txt", null);

        FileHandler.loadFromFile("userdetails.txt", user);

        homePage();

        logger.log("KnowledgeBase application started.");

        KnowledgeBase kb = chooseImplementation();

        logger.log("Knowledge base impelementation: " + implementation);

        MenuHandler menu = new MenuHandler(kb, logger, filename);
        menu.run();

        
    }

    /**
     * Choose implementation to data storage and access operations.
     * 
     * @return Array or Binary Search object.
     */
    private static KnowledgeBase chooseImplementation() {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("Choose knowledge base implementation:");
            System.out.println("1. Array");
            System.out.println("2. Binary Search Tree");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1: 
                    implementation = "Array";
                    return new ArrayKB();
                case 2: 
                    implementation = "Binary Search Tree";
                    return new BSTKB();
                default:  System.out.println("Invalid input. Please try again.");
                
            }
            
        } while (choice!=1 && choice!=2);

        return null;
        
    }

    /**
     * Home page for application.
     * 
     */
    public static void homePage(){
        String choice;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Login [Press 'L'] or Signup [Press 'S'] or Exit [Press 'E']");
        System.out.print("Enter your choice: ");

        choice = scanner.next();

        switch (choice) {
            case "L": logIn(); return;
            case "S": signUp(); return;
            case "E": 
            System.out.println("Exiting. See you later.");
            System.exit(0);;
            default: System.out.println("Invalid input. Please try again");
        }
        

    }

    /**
     * Login interface for previous users.
     */
    public static void logIn(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (!user.checkUsernameAndPassword(username, password)) {
            System.out.println("Username/Password combination is incorrect.");
            homePage();
        }
        else{
            filename = username + ".log";

            logger = new Logger(filename);

            logger.log("Signed in.");
        }

        

    }

    /**
     * SignUp interface for new users.
     */
    public static void signUp(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        if (logger == null) { // Initialize logger
            logger = new Logger("app.log");
        }
        
        if (user.checkUsername(username)) {
            System.out.print("Enter password: ");
            String password = scanner.next();

            UserDetails userDetails = new UserDetails(username, password);

            FileHandler.writeToFile("userdetails.txt", userDetails.toString());
            
            filename = username + ".log";

            logger = new Logger(filename);

            logger.log("Initial commit.");

            user.addDetails(username, password);
        } else {
            System.out.println("Username already exists. Please enter a unique username.");
        }

    }

    
}