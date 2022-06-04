package WIP;

import java.util.*;

public class CardAnalizer {
    
    public Hand hand;

    public CardAnalizer(Hand h){
        this.hand = h;
    }
    public void updateHand(Hand h){
        this.hand = h;
    }
    /*
     * 1- Royal Flush
     * 2- Straight Flush
     * 3- Four of a kind
     * 4- Full House
     * 5- Flush
     * 6- Straight
     * 7- Three of a kind
     * 8- Two Pairs
     * 9- Jacks or better
     * 0- Nothing
     */
    public int evaluateHand(){
        if(isFlush() && isRoyal() )return 1;
        else if(isStraight() && isFlush())return 2;
        else if(isFour())return 3;
        else if(isFH())return 4;
        else if(isFlush()) return 5;
        else if(isStraight())return 6;
        else if(isTOK())return 7;
        else if(isTP())return 8;
        else if(isJOB())return 9;
        return 0;
    }
    private boolean isRoyal(){
        ArrayList<Integer> royaList = new ArrayList<Integer>();
        royaList.add(1);
        royaList.add(10);
        royaList.add(11);
        royaList.add(12);
        royaList.add(13); 
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Card card : hand.cards) {
            aux.add(card.number);
        }
        Collections.sort(aux);
        if (aux.equals(royaList)) return true;
        return false;
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
        int aux = 0;
        ArrayList<Integer> aux1 = new ArrayList<Integer>();
        for (Card card : hand.cards) {
            aux1.add(card.number);
        }
        Collections.sort(aux1);
        for (Integer integer : aux1) {
            if (aux == 0){
                aux = integer;
            }else if (integer == aux){
                return false;
            }else{
                if(aux +1 != integer){
                    return false;
                }else{
                    aux++;
                }
            }
        }
        return true;
    }
    private boolean isFour(){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i : hash) {
            if(i == 4)return true;
        }
        return false;
    }
    private boolean isFH(){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i : hash) {
            if(i == 3){
                for (int j : hash) {
                    if(j==2){
                        return true;
                    }
                }
            }
        }
        return false;    }
    private boolean isTOK(){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i : hash) {
            if(i == 3)return true;
        }    
        return false;
    }
    private boolean isTP(){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        int aux = 0;
        for (Integer integer : hash) {
            if (integer == 2) aux++;
        }
        return aux == 2;
    }
    private boolean isJOB(){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i = 10; i < hash.length; i++) {
            if (hash[i] == 2) return true;
        }
        return false;
    }
}
