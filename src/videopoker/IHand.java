package videopoker;

import java.util.ArrayList;

import videopoker.deck.Card;

/**
 * Interface to implement a hand object
 */
public interface IHand {
    
    /**
     * Method to add a single Card object to the hand to a specific index;
     * <p>
     * It takes a single Card object and adds it to the hand to a specific index, may be usefull to later produce pretty prints;
     * This method is overloaded.
     * If no index is provided, the Card will be added to the end of the list
     * </p>
     * 
     * @param index The index where the card should be added
     * @param c     The Card object
     * @see videopoker.deck.Card
     */
    public void addToHand(Card c);
    
    /**
     * Method to remove a single Card object from the hand in a specific index;
     * <p>
     * It takes the index of the card to be removed.
     * </p>
     * 
     * @param index The index of the card to be removed
     */
    public void removeFromHand(int idx);
    
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
    public void holdCards(ArrayList<Integer> idxs, ArrayList<Card> toReplace);
}
