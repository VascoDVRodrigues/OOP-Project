package videopoker;

import videopoker.deck.Deck;

/**
 * Abstract class Game, to play a variation a class that extends this one must be implemented.
 */
abstract class Game implements IGame{
    protected Deck deck;
    protected Player player;
    protected Hand hand;

    protected final int defaultAmount = 5; 
    protected final int maxBet = 5;

    /**
     * Method to give an hand, sets the attribute hand and sets the hand in the player, since the player and the game have acess to the same hand.
     * 
     * Final so that classes that extend this one all have the same method
     */
    public final void giveHand() {
        // Both the player and the game have acess to the same hand
        this.hand = new Hand(this.deck.getCards(5));

        this.player.setHand(this.hand);
    }

    /**
     * This function prints the deck.
     * 
     * Final so that classes that extend this one all have the same method
     */
    public final void printDeck(){
        System.out.println(deck);
    }

    /**
     * Method to run the Game
     * 
     * <p>
     * This function runs the game, classes that extends this one must implement this method with the appropriate logic
     * </p>
     */
    abstract public void play();
    
}
