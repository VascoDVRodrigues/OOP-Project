package WIP;

import java.util.ArrayList;
import java.util.Collections;

public class RegularDeck extends Deck {
    
    public RegularDeck(){
        this.cardlist = new ArrayList<Card> ();
        
        for (int index = 1; index < 14; index++) {
            Card auxD = new Card('D', index);
            cardlist.add(auxD);
            Card auxS = new Card('S', index);
            cardlist.add(auxS);
            Card auxH = new Card('H', index);
            cardlist.add(auxH);
            Card auxC = new Card('C', index);
            cardlist.add(auxC);
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
        //Pick n random cards from cardslist
        for (int i = 0; i < n; i++) {
            aux.add(cardlist.get(i));
        }
        return aux;
    }

    public void shuffle() {        
        Collections.shuffle(this.cardlist);
    }

    
}
