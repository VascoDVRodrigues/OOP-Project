package videopoker;

import java.util.ArrayList;

import videopoker.deck.Card;

/**
 * This class is used to implement the player.
 */
public class Hand {
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
     * Method to add a single Card object to the hand;
     * <p>
     * It takes a single Card object;
     * </p>
     * 
     * @param c     The Card object
     * @see videopoker.deck.Card
     */
    public void addToHand(Card c) {
        cards.add(c);
    }

    /**
     * Method to add a single Card object to the hand to a specific index;
     * <p>
     * It takes a single Card object and adds it to the hand to a specific index, may be usefull to later produce pretty prints;
     * This method is overloaded.
     * </p>
     * 
     * @param index The index where the card should be added
     * @param c     The Card object
     * @see videopoker.deck.Card
     */
    public void addToHand(int index, Card c) {
        cards.add(index, c);
    }

    /**
     * Method to remove a single Card object from the hand in a specific index;
     * <p>
     * It takes the index of the card to be removed.
     * </p>
     * 
     * @param index The index of the card to be removed
     */
    public void removeFromHand(int idx) {
        cards.remove(idx);
    }

    /**
     * Method to hold cards at the given indexes;
     * <p>
     * It takes an ArrayList of indexes (Integers) of the cards to be held, and an ArrayList of Card objects with the cards that will replace the dropped ones. 
     * </p>
     * 
     * @param idxs      The ArrayList of indexes of the cards to hold
     * @param toReplace The ArrayList of Cards that will replace the dropped ones
     * @see videopoker.deck.Card
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
