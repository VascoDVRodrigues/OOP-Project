package videopoker.exceptions;
 /**
 * Exception class throwned when the amount of money is not valid.
  */
public class IllegalAmountException extends Exception{

    /**
     * @param message
     */
    public IllegalAmountException() {
        super("b: illegal amount\n");
    }    
}
