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
            // Remove by value
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number != 1 && card.number < 10) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
            i = 0;
            // Remove by nape
            HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
            hash_nape.put('H', 0);
            hash_nape.put('S', 0);
            hash_nape.put('D', 0);
            hash_nape.put('C', 0);
            char true_nape = 'A';

            for (Card card : hand.cards) {
                hash_nape.put(card.nape, hash_nape.get(card.nape) + 1);
            }

            for (Character key : hash_nape.keySet()) {
                if (hash_nape.get(key) >= 4) {
                    true_nape = key;
                    break;
                }
            }
            for (Card card : hand.cards) {
                if (card.nape != true_nape) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else if (condition == "3. Three aces") {
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number == 1)
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
                if(hash[i]==3)
                    cardtokeep =i;
            }
            int i = 0;
            for (Card card : hand.cards) {
                if (card.number == cardtokeep+1)
                    holdList.remove(Integer.valueOf(i));
                i++;
            }

        } else if (condition == "6. 4 to a straight flush") {

        } else if (condition == "7. Two pair") {

        } else if (condition == "8. High pair") {

        } else if (condition == "9. 4 to a flush") {

        } else if (condition == "10. 3 to a royal flush") {

        } else if (condition == "11. 4 to an outside straight") {

        } else if (condition == "12. Low pair") {

        } else if (condition == "13. AKQJ unsuited") {

        } else if (condition == "14. 3 to a straight flush (type 1)") {

        } else if (condition == "15. 4 to an inside straight with 3 high cards") {

        } else if (condition == "16. QJ suited") {

        } else if (condition == "17. 3 to a flush with 2 high cards") {

        } else if (condition == "18. 2 suited high cards") {

        } else if (condition == "19. 4 to an inside straight with 2 high cards") {

        } else if (condition == "20. 3 to a straight flush (type 2)") {

        } else if (condition == "21. 4 to an inside straight with 1 high card") {

        } else if (condition == "22. KQJ unsuited") {

        } else if (condition == "23. JT suited") {

        } else if (condition == "24. QJ unsuited") {

        } else if (condition == "25. 3 to a flush with 1 high card") {

        } else if (condition == "26. QT suited") {

        } else if (condition == "27. 3 to a straight flush (type 3)") {

        } else if (condition == "28. KQ, KJ unsuited") {

        } else if (condition == "29. Ace") {

        } else if (condition == "30. KT suited") {

        } else if (condition == "31. Jack, Queen or King") {

        } else if (condition == "32. 4 to an inside straight with no high cards") {

        } else if (condition == "33. 3 to a flush with no high cards") {

        } else {
            holdList.remove(Integer.valueOf(0));
            holdList.remove(Integer.valueOf(1));
            holdList.remove(Integer.valueOf(2));
            holdList.remove(Integer.valueOf(3));
            holdList.remove(Integer.valueOf(4));
        }

        return holdList;
    }
}
