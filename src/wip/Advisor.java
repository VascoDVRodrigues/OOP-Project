package wip;

import java.util.*;

public class Advisor {
    public ArrayList<Integer> getHoldList(String condition, Hand hand) {
        ArrayList<Integer> holdList = new ArrayList<Integer>();
        holdList.add(0);
        holdList.add(1);
        holdList.add(2);
        holdList.add(3);
        holdList.add(4);
        if (condition == "1. Straight flush, four of a kind, royal flush") {
            return holdList;
        } else if (condition == "2. 4 to a royal flush") {
            // // Remove by value
            // int i = 0;
            // for (Card card : hand.cards) {
            // if (card.number != 1 && card.number < 10) {
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // i++;
            // }
            // i = 0;

            holdList = this.xtoFlush(4, hand);
        } else if (condition == "3. Three aces") {
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number != 1)
                    holdList.remove(Integer.valueOf(i));
                i++;
            }
        } else if (condition == "4. Straight, flush, full house") {
            return holdList;
        } else if (condition == "5. Three of a kind (except aces)") {
            int[] hash = new int[13];
            for (Card card : hand.cards) {
                hash[card.number - 1]++;
            }
            int cardtokeep = 0;
            for (int i = 0; i < hash.length; i++) {
                if (hash[i] == 3)
                    cardtokeep = i;
            }
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number == cardtokeep + 1)
                    holdList.remove(Integer.valueOf(i));
                i++;
            }

        } else if (condition == "6. 4 to a straight flush") {
            holdList = this.xtoFlush(4, hand);
        } else if (condition == "7. Two pair") {
            int[] hash = new int[13];
            for (Card card : hand.cards) {
                hash[card.number - 1]++;
            }
            int i = 0;
            for (Card card : hand.cards) {
                if (hash[card.number - 1] == 1) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }

        } else if (condition == "8. High pair") {
            int[] hash = new int[13];
            for (Card card : hand.cards) {
                hash[card.number - 1]++;
            }
            int i = 0;
            for (Card card : hand.cards) {
                if (hash[card.number - 1] == 2) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }
        } else if (condition == "9. 4 to a flush") {
            holdList = this.xtoFlush(4, hand);
        } else if (condition == "10. 3 to a royal flush") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "11. 4 to an outside straight") {
            int[] hash = new int[13];
            // Ver quantas cartas ha do mesmo numero
            for (Card card : hand.cards) {
                hash[card.number - 1]++;
            }

            // [0,0,1,1,1,1,0,0,1,0,0,0,0]
            int i = 0, j = 0, count = 0;
            for (i = 0; i < hash.length; i++) {
                if (hash[i] > 0) {
                    for (j = 0; j < hash.length; j++) {
                        if (hash[i] > 0) {
                            count += 1;
                        } else {
                            break;
                        }
                    }
                    if (count == 4) {
                        break;
                    }
                }
            }
            // Cartas desde i até j estão no straight
            if (count == 4) { // sanity check
                for (int k = 0; i < hand.cards.size(); i++) {
                    if ((i < hand.cards.get(k).number) && (hand.cards.get(k).number < j)) {
                        holdList.add(k);
                    }
                }
            } else {
                System.out.println("erro no 4 to outside straight, classe advisor");
            }

            // // OUTRA FORMA
            // // Se existir mais do que 1 carta c mm numero retira-se essa carta
            // // Aqui o naipe não interessa, so se procura por um straight e nao flush
            // int i = 0;
            // for (Card card : hand.cards) {
            // if (hash[card.number - 1] > 1) {
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // i++;
            // }

            // i = -1;
            // int hit = 0;
            // //
            // for (int j = 0; j < hash.length; j++) {
            // if (hash[j] > 0) {
            // hit++;
            // i++;
            // } else {
            // if (hit == 1){
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // else if(hit == 4){
            // i++;
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // }
            // }

        } else if (condition == "12. Low pair") {
            int[] hash = new int[13];
            for (Card card : hand.cards) {
                hash[card.number - 1]++;
            }
            int i = 0;
            for (Card card : hand.cards) {
                if (hash[card.number - 1] == 2) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }
        } else if (condition == "13. AKQJ unsuited") {
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number > 1 && card.number < 11) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else if (condition == "14. 3 to a straight flush (type 1)") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "15. 4 to an inside straight with 3 high cards") {

        } else if (condition == "16. QJ suited") {
            int i = 0;
            // nao é necessario procurar outros casos pq seriam high pairs
            for (Card card : hand.cards) {
                if (card.number != 11 && card.number != 12) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }

        } else if (condition == "17. 3 to a flush with 2 high cards") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "18. 2 suited high cards") {
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number != 1 && card.number <= 10) {
                    holdList.remove(Integer.valueOf(i));
                }else{
                    int count = 0;
                    for (Card card1 : hand.cards) {
                        if ((card1.number == 1 || card1.number > 10)&&(!card1.equals(card))&&(card.nape == card1.nape)) {
                            count ++;
                        }
                    }
                    //nao preocupar com mais do que 2 suited high cards porque é 3 to royale flush
                    if(count == 0){
                        holdList.remove(Integer.valueOf(i));
                    }
                }
                i++;
            }            
        } else if (condition == "19. 4 to an inside straight with 2 high cards") {

        } else if (condition == "20. 3 to a straight flush (type 2)") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "21. 4 to an inside straight with 1 high card") {

        } else if (condition == "22. KQJ unsuited") {

        } else if (condition == "23. JT suited") {

        } else if (condition == "24. QJ unsuited") {

        } else if (condition == "25. 3 to a flush with 1 high card") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "26. QT suited") {

        } else if (condition == "27. 3 to a straight flush (type 3)") {

        } else if (condition == "28. KQ, KJ unsuited") {

        } else if (condition == "29. Ace") {
            // Only hold the ace
            for (int i = 0; i < hand.cards.size(); i++) {
                // Find the ace, remove everythinh from the list and only add the ace
                if (hand.cards.get(i).nape == 'A') {
                    holdList.removeAll(holdList);
                    holdList.add(i);
                    break;
                }
            }
        } else if (condition == "30. KT suited") {

        } else if (condition == "31. Jack, Queen or King") {

        } else if (condition == "32. 4 to an inside straight with no high cards") {

        } else if (condition == "33. 3 to a flush with no high cards") {
            holdList = this.xtoFlush(3, hand);
        } else {
            holdList.remove(Integer.valueOf(0));
            holdList.remove(Integer.valueOf(1));
            holdList.remove(Integer.valueOf(2));
            holdList.remove(Integer.valueOf(3));
            holdList.remove(Integer.valueOf(4));
        }

        return holdList;
    }

    private ArrayList<Integer> xtoFlush(int x, Hand hand) {
        ArrayList<Integer> holdList = new ArrayList<Integer>();
        // holdList.add(0);
        // holdList.add(1);
        // holdList.add(2);
        // holdList.add(3);
        // holdList.add(4);

        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        // Check how many cards have the same nape
        for (Card card : hand.cards) {
            hash_nape.put(card.nape, hash_nape.get(card.nape) + 1);
        }

        // Find the nape that has x cards
        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == x) {
                true_nape = key;
                break;
            }
        }

        // If a card in the hand has the nape then hold it
        for (int i = 0; i < hand.cards.size(); i++) {
            if (hand.cards.get(i).nape == true_nape) {
                holdList.add(i);
            }
        }

        return holdList;

        // int i = 0;
        // for (Card card : hand.cards) {
        // if (card.nape != true_nape) {
        // holdList.remove(Integer.valueOf(i));
        // }
        // i++;
        // }
    }
}
