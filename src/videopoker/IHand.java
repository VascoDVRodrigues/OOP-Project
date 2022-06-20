package videopoker;

import java.util.ArrayList;

import videopoker.deck.Card;

public interface IHand {
    public void addToHand(Card c);

    public void removeFromHand(int idx);

    public void holdCards(ArrayList<Integer> idxs, ArrayList<Card> toReplace);
}
