package models;

/**
 * Singleton Model
 * @author shruti
 *
 */
public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }
    
    private UserChoice userchoice = new UserChoice();

    public UserChoice currentUserchoice() {
        return userchoice;
    }
}