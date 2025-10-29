package usr;

/**
 * Representation of username and password for each user.
 */
public class UserDetails {

    private String username;
    private String password;

    /**
     * Constructor for class
     * 
     * @param username      The username.
     * @param password      The associated password.
     */
    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get method for username.
     * 
     * @return The username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Get methd for password.
     * 
     * @return  The password
     */
    public String getPassword(){
        return password;
    }

    /**
     * Set new password
     * 
     * @param password  The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Output saved to txt file.
     */
    public String toString(){
        return username + "," + password;
    }
}