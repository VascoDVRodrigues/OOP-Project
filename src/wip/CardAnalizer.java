package wip;

import java.util.*;

public class CardAnalizer {
    public String getPayTableResult(Hand hand){
        if(isFlush(hand) && isRoyal(hand) )return "RF";
        else if(isStraight(hand) && isFlush(hand))return "SF";
        else if(isFour(hand))return "FOK";
        else if(isFH(hand))return "FH";
        else if(isFlush(hand)) return "F";
        else if(isStraight(hand))return "S";
        else if(isTOK(hand))return "TOK";
        else if(isTP(hand))return "TP";
        else if(isJOB(hand))return "JOB";
        return "O";
    }
    private boolean isRoyal(Hand hand){
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
    private boolean isFlush(Hand hand){
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
    private boolean isStraight(Hand hand){
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
    private boolean isFour(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i : hash) {
            if(i == 4)return true;
        }
        return false;
    }
    private boolean isFH(Hand hand){
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
    private boolean isTOK(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i : hash) {
            if(i == 3)return true;
        }    
        return false;
    }
    private boolean isTP(Hand hand){
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
    private boolean isJOB(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i = 10; i < hash.length; i++) {
            if (hash[i] == 2) return true;
        }
        return false;
    }
    private boolean is3A(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        if(hash[0]==3) return true;
        return false;
    }
    private boolean is4toRF(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        if((hash[0]+hash[12]+hash[11]+hash[10]+hash[9]>=4) && isFlush(hand)) return true;
        return false;
    }
    private boolean isHP(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        
        return hash[0] == 2 || hash[12] == 2 || hash[11] == 2 || hash[10] == 2;
    }
    private boolean isLP(Hand hand){
        int[] hash = new int[13];
        for (Card card : hand.cards) {
            hash[card.number-1]++;
        }
        for (int i = 1; i < 10; i++) {
            if (hash[i]==2) return true;
        }
        
        return false;
    }
    private boolean hasCard(int c, Hand hand){
        for (Card card : hand.cards) {
            if(card.number == c) return true;
        }        
        return false;
    }

    public String getAdviceFromTable(Hand hand){
        if((isFlush(hand) && isRoyal(hand))||isFour(hand)||(isStraight(hand) && isFlush(hand)))return "1. Straight flush, four of a kind, royal flush";
        if(is4toRF(hand))return "2. 4 to a royal flush";
        if(is3A(hand))return "3. Three aces";
        if(isStraight(hand)||isFH(hand)||isFlush(hand))return "4. Straight, flush, full house";
        if(isTOK(hand))return "5. Three of a kind (except aces)";
        if(isTP(hand))return "7. Two pair";
        if(isHP(hand))return "8. High pair";
        if(isLP(hand))return "12. Low pair";
        if(hasCard(1,hand))return"29. Ace";
        if(hasCard(11,hand)||hasCard(12,hand)||hasCard(13,hand)) return "31. Jack, Queen or King";
        return "34. Discard everything";
    }
}
