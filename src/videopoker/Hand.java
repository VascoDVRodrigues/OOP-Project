package videopoker;

import java.util.ArrayList;

import videopoker.deck.Card;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> c) {
        this.cards = c;
    }

    /**
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    // Adds card to hand
    public void addToHand(Card c) {
        cards.add(c);
    }

    // Adds card to hand in a specific index
    public void addToHand(int index, Card c) {
        cards.add(index, c);
    }

    // Deletes the card at a index
    public void removeFromHand(int idx) {
        cards.remove(idx);
    }

    // Holds the cards at the specified index
    public void holdCards(ArrayList<Integer> idxs, ArrayList<Card> toReplace) {
        // Remove the cards that are not in those indexes
        int curr;
        ArrayList<Integer> droppedIdxs = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            if (idxs.contains(i)) {
                continue;
            } else {
                droppedIdxs.add(i);
            }
        }

        // Just removing from the list causes the elements to shift and can produce
        // wrong results

        // Sorting idxs in descending order and removing them in descending order fixes
        // this
        // Collections.sort(droppedIdxs, Collections.reverseOrder());

        while (!droppedIdxs.isEmpty()) {
            curr = droppedIdxs.remove(0);
            this.cards.remove(curr);
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

    public void printUnconverted() {
        for (Card card : cards) {
            card.printUnconverted();
        }
    }
}
