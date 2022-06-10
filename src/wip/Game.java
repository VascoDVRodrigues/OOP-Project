package wip;

/**
 * Game
 */
abstract class Game {
    protected Deck deck;
    protected Player player;

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
