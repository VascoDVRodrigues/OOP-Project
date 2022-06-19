package videopoker.deck;

import java.util.ArrayList;

public abstract class Deck {
    protected ArrayList<Card> cardList;

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
