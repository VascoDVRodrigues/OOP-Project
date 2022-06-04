package WIP;
import java.util.ArrayList;

public class Hand {
    protected ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }
    public void addToHand(Card c) {
        cards.add(c);
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
}
