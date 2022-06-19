package videopoker.exceptions;

public class IllegalCommandException extends Exception {

    /**
     * @param message
     */
    public IllegalCommandException(String message) {
        super(message + ": illegal command\n");
    }
    
}
