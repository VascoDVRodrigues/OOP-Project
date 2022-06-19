package videopoker.deck;

import java.util.ArrayList;
import java.util.Collections;

public class RegularDeck extends Deck {

    ArrayList<Card> bag = new ArrayList<Card>();
    
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
        int i=0;
        while (i < n) {
            aux.add(cardList.get(0));
            bag.add(cardList.remove(0));
            i++;
        }
        return aux;
    }

    public void shuffle() {        
        this.cardList.addAll(this.bag);
        this.bag.removeAll(this.bag);
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
