package videopoker.exceptions;


/**
 * Exception class throwned when the parameters of the simulation mode are invalid.
 */
public class InvalidSimulationMode extends Exception {

    /**
     * @param message
     */
    public InvalidSimulationMode(String message) {
        super(message);
    }    
}
