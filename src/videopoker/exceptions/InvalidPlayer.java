package videopoker.exceptions;


/**
 * Exception class throwned when player object creation fails.
 */
public class InvalidPlayer extends Exception {

    /**
     * @param message
     */
    public InvalidPlayer(String message) {
        super(message);
    }
    
}
