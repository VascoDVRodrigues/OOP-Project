package videopoker;

/**
 * Interface to implement a player object
 */
public interface IGame {

    /**
     * Method to give an hand
     */
    public void giveHand();

    /**
     * This function prints the deck.
     */
    public void printDeck();

    /**
     * Method to run the Game
     * 
     * <p>
     * This function runs the game, classes that implements this interface must implement this method with the appropriate logic
     * </p>
     */
    public void play();
}
