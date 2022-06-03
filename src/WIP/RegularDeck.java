package WIP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegularDeck extends Deck {
    
    public RegularDeck(){
        this.cardlist = new Card[52];
        this.length = 52;
        
        int i = 0;
        for (int index = 1; index < 14; index++) {
            Card auxD = new Card('D', index);
            cardlist[i] = auxD;
            i++;
            Card auxS = new Card('S', index);
            cardlist[i] = auxS;
            i++;
            Card auxH = new Card('H', index);
            cardlist[i] = auxH;
            i++;
            Card auxC = new Card('C', index);
            cardlist[i] = auxC;
            i++;
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
        for (int i = 0; i < n; i++) {
            aux.add(cardlist[i]);
        }
        return aux;
    }

    public void shuffle() {
        List<Card> list = Arrays.asList(this.cardlist);
        
        Collections.shuffle(list);
        this.cardlist = list.toArray(this.cardlist);        
    }

    
}
