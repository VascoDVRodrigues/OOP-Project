package videopoker;

import videopoker.deck.Deck;

/**
 * Game
 */
abstract class Game implements IGame{
    protected Deck deck;
    protected Player player;
    protected Hand hand;

    // final????
    protected final int defaultAmount = 5; 
    protected final int maxBet = 5;

    public final void giveHand() {
        // Both the player and the game have acess to the same hand
        this.hand = new Hand(this.deck.getCards(5));

        this.player.setHand(this.hand);
    }

    //final here so all subclasses of this one have the same method
    public final void printDeck(){
        System.out.println(deck);
    }

    abstract public void play();
    
}
