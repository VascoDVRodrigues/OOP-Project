package videopoker.deck;

import java.util.ArrayList;
import java.util.Collections;

public class RegularDeck extends Deck {
    
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

    @Override
    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
        //Pick n random cards from cardslist
        for (int i = 0; i < n; i++) {
            aux.add(cardList.get(i));
        }
        return aux;
    }

    public void shuffle() {        
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
