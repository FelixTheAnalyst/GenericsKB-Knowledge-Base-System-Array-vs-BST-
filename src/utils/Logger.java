package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tracks user past actions
 */
public class Logger {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String filename;

    /**
     * Construtor for Logger class.
     * 
     * @param filename  The file name
     */
    public Logger(String filename){
        this.filename = filename;
    }
    
    /**
     * Writes action to file and terminal
     * 
     * @param message   The associated message
     */
    public void log(String message) {

        String record = '[' + LocalDateTime.now().format(format) +  ']' + message;

        FileHandler.writeToFile(filename, record);

        System.out.println(message);

    }

}
