package wip;

/**
 * Game
 */
abstract class Game {
    protected Deck deck;
    protected Player player;

    public void printDeck(){
        System.out.println(deck);
    }

    abstract public void giveHand();
}
