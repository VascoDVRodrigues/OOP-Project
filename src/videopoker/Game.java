package videopoker;

import videopoker.deck.Deck;

/**
 * Game
 */
abstract class Game {
    protected Deck deck;
    protected Player player;
    protected Hand hand;

    // final????
    protected final int defaultAmount = 5; 
    protected final int maxBet = 5;

    //final here so all subclasses of this one have the same method
    public final void printDeck(){
        System.out.println(deck);
    }

    abstract public void play();

    abstract public void giveHand();
}
