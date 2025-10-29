package usr;

/**
 * Methods for user-verfication.
 */
public class User {

    private UserDetails[] entries;
    private int size;

    /**
     * Constructor for User class.
     */
    public User() {
        this.entries = new UserDetails[100];
        this.size = 0;
    }

    /**
     * Adds username and password of user.
     * 
     * @param username The unique username.
     * @param password The password associated with username.
     */
    public void addDetails(String username, String password) {

        if (size >= entries.length)
            expandArray();

        entries[size++] = new UserDetails(username, password);
    }

    /**
     * Checks whether username already exists.
     * 
     * @param username  The username.
     * @return  True if unique, false if al
     */
    public boolean checkUsername(String username){

        for(int i=0; i<size; i++) {
            if (entries[i].getUsername().equals(username))
                return false;
        }

        return true;
    }

    /**
     * Checks whether username/password combination matches.
     * 
     * @param username  The unique username.
     * @param password  The password.
     * @return
     */
    public boolean checkUsernameAndPassword(String username, String password) {
        
        for(int i=0; i<size; i++) {
            if (entries[i].getPassword().equals(password) && entries[i].getUsername().equals(username))
                return true;
        }

        return false;
        
        
    }

    /**
     * Method for changing password.
     * 
     * @param username The username
     * @param password The new password
     */
    public void changePassword(String username, String password) {
        for(int i=0; i<size; i++) {
            if (entries[i].getUsername().equals(username)){
                entries[i].setPassword(password);
                return;
            }
                
        }
    }

    /**
     * Resizes array by a factor of 2.
     */
    public void expandArray(){

        UserDetails[] newEntries = new UserDetails[entries.length * 2];
        System.arraycopy(entries, 0, newEntries, 0, entries.length);
        entries = newEntries;
    }


}