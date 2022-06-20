package videopoker.deck;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class extends the Deck abstract class.
 * 
 * <p> This class generates a deck of cards. It also implements methods for shuffling and also a method for dealing cards.</p>
 * @see videopoker.deck.Deck 
 */
public class RegularDeck extends Deck {
    /**
     * Array List created to save the cards that were already removed from the deck, and that it needs to be returned to the deck on the next suffle.
     */
    ArrayList<Card> bag = new ArrayList<Card>();
    
    /**
     * Constructor for the RegularDeck class 
     * <p>
     * Generates the 52 cards that constitute a valid deck
     * </p>
     * 
     *
     */
    public RegularDeck(){
        this.cardList = new ArrayList<Card> ();
        
        for (int index = 1; index < 14; index++) {
            Card auxD = new Card('D', index);
            cardList.add(auxD);
            Card auxS = new Card('S', index);
            cardList.add(auxS);
            Card auxH = new Card('H', index);
            cardList.add(auxH);
            Card auxC = new Card('C', index);
            cardList.add(auxC);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
        //Pick n random cards from cardslist
        int i=0;
        while (i < n) {
            aux.add(cardList.get(0));
            bag.add(cardList.remove(0));
            i++;
        }
        return aux;
    }

    /**
     * This function adds all the cards in the bag to the cardList, clears the bag, and then shuffles the cardList.
     */
    public void shuffle() {        
        this.cardList.addAll(this.bag);
        this.bag.removeAll(this.bag);
        Collections.shuffle(this.cardList);
    }

    public static void main(String[] args) {
        RegularDeck d = new RegularDeck();

        d.shuffle();
        System.out.println(d);
        d.shuffle();
        System.out.println(d);
        d.shuffle();
        System.out.println(d);
    }
}
