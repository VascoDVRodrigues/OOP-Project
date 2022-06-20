package videopoker.exceptions;


/**
 * Exception class throwned when an illegal command is given to the game.
 */
public class IllegalCommandException extends Exception {

    /**
     * @param message
     */
    public IllegalCommandException(String message) {
        super(message + ": illegal command\n");
    }
    
}
