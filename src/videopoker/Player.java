package videopoker;

import videopoker.exceptions.InvalidPlayer;

/**
 * This class is used to implement the player.
 */
public class Player {
    //Attributes for the credits, name and hand
    protected int credits;
    public String name;
    protected Hand hand;

    /**
     * This is the constructor for the Player class.
     * <p>
     * It takes the name of the player, and the initial credit.
     * It may throw an InvalidPlayer exception if the player is created with an invalid number of initial credits.
     * </p>
     * 
     * @param c              Initial credits.
     * @param name           The name of the player
     * @throws InvalidPlayer if the initial credit if lower or equal than 0
     */
    public Player(int c, String name) throws InvalidPlayer {
        if ( c <= 0 ) {
            throw new InvalidPlayer("Credits must be an integer greater than 0");
        }
        this.credits = c;
        this.name = name;
    }

    /**
     * This is another constructor (overloaded) for the Player class.
     * <p>
     * It takes the same initial credit and may throw an InvalidPlayer just like the other contructor.
     * Use this constructor if you dont want to give a name to the player
     * </p>
     * 
     * @param c              Initial credits.
     * @throws InvalidPlayer if the initial credit if lower or equal than 0
     */
    public Player(int c) throws InvalidPlayer {
        if ( c <= 0 ) {
            throw new InvalidPlayer("Credits must be an integer greater than 0");
        }
        this.credits = c;
    }

    /**
     * @return the credits
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Method to increase the credit of the player.
     * 
     * @param amount    The amount to increase the player's credit.
     */
    public void increaseCredit(int amount) {
        this.credits += amount;
    }

    /**
     * Method to bet (derease the credit of the player).
     * 
     * @param amount    The amount of the bet.
     */
    public void bet(int amount) {
        this.credits -= amount;
        // if (this.credits < 0) {
        //     // this.credits += amount;
        //     System.out.println("Player doesnt have enough credits :(");
        // }
    }

    @Override
    public String toString() {
        return "Player " + this.name + " has " + this.credits + " credits";
    }

    /**
     * Method to display the current hand of the player.
     * 
     */
    public void displayHand() {
        System.out.println("player's hand " + this.hand);
    }

    /**
     * Method to set the hand of the player.
     * 
     * @param hand    The hand to be setted, must be of type Hand.
     * @see videopoker.Hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
        // this.displayHand();
    }
}
