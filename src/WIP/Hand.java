package WIP;

public class Hand {
    protected Card [] cards;
    private int i;

    public Hand() {
        cards = new Card[5];
        i= 0;
    }
    public void addToHand(Card c) {
        cards[i] = c;
        i ++;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int index = 0; index < cards.length; index++) {
            str.append(cards[index]);
            str.append(" ");
        }
        return str.toString();
    }
}
