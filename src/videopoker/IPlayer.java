package videopoker;

public interface IPlayer {
    /**
     * Method to increase the credit of the player.
     * 
     * @param amount    The amount to increase the player's credit.
     */
    public void increaseCredit(int amount);
    /**
     * Method to bet (derease the credit of the player).
     * 
     * @param amount    The amount of the bet.
     */
    public void bet(int amount);
    /**
     * Method to set the hand of the player.
     * 
     * @param hand    The hand to be setted, must be of type Hand.
     * @see videopoker.Hand
     */
    public void setHand(Hand hand);
}
