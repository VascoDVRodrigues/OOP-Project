package videopoker.deck;

import java.util.ArrayList;

public abstract class Deck implements IDeck {
    protected ArrayList<Card> cardList;

    /**
     * Method to return n cards from the deck
     * 
     * @param n Integer representing the number of cards to draw from the deck
     */
    abstract public ArrayList<Card> getCards(int n);

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int index = 0; index < this.cardList.size(); index++) {
            str.append(cardList.get(index));
            str.append(" ");
        }
        return str.toString();
    }
}
