package WIP;

import java.util.*;

public class CardAnalizer {
    
    public Hand hand;

    CardAnalizer(Hand h){
        this.hand = h;
    }

    private boolean isFlush(){
        char aux = 'A';
        for (Card card : hand.cards) {
            if (aux == 'A'){
                aux = card.nape;
            }else if (card.nape != aux){
                return false;
            }
        }
        return true;
    }
    private boolean isStraight(){
        Collections.sort(hand, new Comparator<Card>() {
        public int compare(Card c1, Card c2) {
            return Integer.compare(c1.number,c2.number);
        }});  
        int aux = 0;
        for (Card card : hand.cards) {
            if (aux == 0){
                aux = card.number;
            }else if (card.number == aux){
                return false;
            }else{
                if(aux +1 != card.number){
                    return false;
                }else{
                    aux++;
                }
            }
        }
        return true;
    }
}
