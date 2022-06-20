package videopoker;

import java.util.ArrayList;

import videopoker.deck.Card;

/**
 * This class is used to implement the player.
 */
public class Hand implements IHand {
    //Attribute to save the cards inside the hand
    private ArrayList<Card> cards;

    /**
     * This is the constructor for the Hand class.
     * <p>
     * It takes and ArrayList of Card objects.
     * </p>
     * 
     * @param c     An ArrayList of Card objects
     * @see videopoker.deck.Card
     */
    public Hand(ArrayList<Card> c) {
        this.cards = c;
    }

    /**
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
    * {@inheritDoc}
    */
    public void addToHand(Card c) {
        cards.add(c);
    }

    /**
    * {@inheritDoc}
    */
    public void addToHand(int index, Card c) {
        cards.add(index, c);
    }

    /**
    * {@inheritDoc}
    */
    public void removeFromHand(int idx) {
        cards.remove(idx);
    }

    /**
    * {@inheritDoc}
    */
    public void holdCards(ArrayList<Integer> idxs, ArrayList<Card> toReplace) {
        // Remove the cards that are not in those indexes
        
        int curr;
        //Calculate first the indexes of the cards to be dropped
        ArrayList<Integer> droppedIdxs = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            if (idxs.contains(i)) {
                continue;
            } else {
                droppedIdxs.add(i);
            }
        }

        //Drop those indexes and replace
        while (!droppedIdxs.isEmpty()) {
            curr = droppedIdxs.remove(0);
            this.cards.remove(curr);
            //Use the addToHand method with the index, so the new card actually replaces in the same spot
            this.addToHand(curr, toReplace.remove(0));
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Card card : cards) {
            str.append(card);
            str.append(" ");
        }

        return str.toString();
    }

    /**
     * Method to print all cards in the hand withouth the conversion to the real card names.
     * 
     * @see videopoker.deck.Card#printUnconverted()
     */
    public void printUnconverted() {
        for (Card card : cards) {
            card.printUnconverted();
        }
    }
}
