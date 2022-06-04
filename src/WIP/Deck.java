package WIP;

import java.util.ArrayList;

abstract class Deck {
    protected ArrayList<Card> cardlist;
    protected int length;

    abstract ArrayList<Card> getCards(int n);

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int index = 0; index < this.length; index++) {
            str.append(cardlist.get(index));
            str.append(" ");
        }
        return str.toString();
    }
}
