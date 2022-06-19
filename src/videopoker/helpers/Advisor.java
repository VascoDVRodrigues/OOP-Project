package videopoker.helpers;

import java.util.*;

import videopoker.Hand;
import videopoker.deck.Card;

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
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 1 && card.getNumber() < 10) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
            i = 0;

            // holdList = this.xtoFlush(4, hand);
        } else if (condition == "3. Three aces") {
            int i = 0;
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 1)
                    holdList.remove(Integer.valueOf(i));
                i++;
            }
        } else if (condition == "4. Straight, flush, full house") {
            return holdList;
        } else if (condition == "5. Three of a kind (except aces)") {
            int[] hash = new int[13];
            for (Card card : hand.getCards()) {
                hash[card.getNumber() - 1]++;
            }
            int cardtokeep = 0;
            for (int i = 0; i < hash.length; i++) {
                if (hash[i] == 3)
                    cardtokeep = i;
            }
            int i = 0;
            for (Card card : hand.getCards()) {
                if (card.getNumber() == cardtokeep + 1)
                    holdList.remove(Integer.valueOf(i));
                i++;
            }

        } else if (condition == "6. 4 to a straight flush") {
            holdList = this.xtoFlush(4, hand);
        } else if (condition == "7. Two pair") {
            int[] hash = new int[13];
            for (Card card : hand.getCards()) {
                hash[card.getNumber() - 1]++;
            }
            int i = 0;
            for (Card card : hand.getCards()) {
                if (hash[card.getNumber() - 1] == 1) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }

        } else if (condition == "8. High pair") {
            int[] hash = new int[13];
            // Check how many cards there are with the same number
            for (Card card : hand.getCards()) {
                hash[card.getNumber() - 1]++;
            }

            int i = 0;
            for (Card card : hand.getCards()) {
                if (hash[card.getNumber() - 1] != 2) {
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
            for (Card card : hand.getCards()) {
                hash[card.getNumber() - 1]++;
            }

            // / Se existir mais do que 1 carta c mm numero retira-se essa carta
            // Aqui o naipe não interessa, so se procura por um straight e nao flush
            int i = 0;
            for (Card card : hand.getCards()) {
                if (hash[card.getNumber() - 1] > 1) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }

            // [0,0,1,1,1,1,0,0,1,0,0,0,0]
            // [0,0,0,1,0,0,1,1,1,1,0,0]

            holdList.removeAll(holdList);
            i = 0;
            int j = 0, count = 0;
            for (i = 0; i < hash.length; i++) {
                if (hash[i] > 0) {
                    // comecar a contar 1s
                    for (j = i; j < hash.length; j++) {
                        if (hash[j] > 0) {
                            count += 1;
                        } else {
                            break;
                        }
                    }
                    if (count == 4) {
                        break;
                    } else {
                        count = 0;
                    }
                }
            }
            // Cartas desde i até j estão no straight
            // System.out.println(i + " " + j);
            if (count == 4) { // sanity check
                for (int k = 0; k < hand.getCards().size(); k++) {
                    if ((i < hand.getCards().get(k).getNumber()) &&
                            (hand.getCards().get(k).getNumber() <= j)) {
                        holdList.add(k);
                    }
                }
            } else {
                System.out.println("erro no 4 to outside straight, classe advisor");
                System.out.println(hand);
            }

            // // OUTRA FORMA
            // Se existir mais do que 1 carta c mm numero retira-se essa carta
            // Aqui o naipe não interessa, so se procura por um straight e nao flush
            // int i = 0;
            // for (Card card : hand.getCards()) {
            // if (hash[card.getNumber() - 1] > 1) {
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // i++;
            // }
            // /////////////////// [0,0,0,1,0,0,1,1,1,1,0,0]
            // i = -1;
            // int hit = 0;
            // for (int j = 0; j < hash.length; j++) {
            // if (hash[j] > 0) {
            // hit++;
            // i++;
            // } else {
            // if (hit == 1) {
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // } else if (hit == 4) {
            // i++;
            // holdList.remove(Integer.valueOf(i));
            // return holdList;
            // }
            // }
            // }

        } else if (condition == "12. Low pair") {
            int[] hash = new int[13];
            for (Card card : hand.getCards()) {
                hash[card.getNumber() - 1]++;
            }

            int i = 0;
            for (Card card : hand.getCards()) {
                if (hash[card.getNumber() - 1] != 2) {
                    // isto remove o inteiro i e não o objeto com indice i
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }

            // Ou assim
            // holdList.removeAll(holdList);//so para limpar
            // //Se a carta aparece 2 vezes na mão ent é para dar hold
            // for (int i = 0; i < hand.getCards().size(); i++) {
            // if ( hash[hand.getCards().get(i).number - 1] == 2) {
            // holdList.add(i);
            // }
            // }
        } else if (condition == "13. AKQJ unsuited") {
            int i = 0;
            for (Card card : hand.getCards()) {
                if (card.getNumber() > 1 && card.getNumber() < 11) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else if (condition == "14. 3 to a straight flush (type 1)") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "15. 4 to an inside straight with 3 high cards") {
            return this.insideStraight(hand);
        } else if (condition == "16. QJ suited") {
            int i = 0;
            // nao é necessario procurar outros casos pq seriam high pairs
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 11 && card.getNumber() != 12) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }

        } else if (condition == "17. 3 to a flush with 2 high cards") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "18. 2 suited high cards") {
            int i = 0;
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 1 && card.getNumber() <= 10) {
                    holdList.remove(Integer.valueOf(i));
                } else {
                    int count = 0;
                    for (Card card1 : hand.getCards()) {
                        if ((card1.getNumber() == 1 || card1.getNumber() > 10) && (!card1.equals(card))
                                && (card.getNape() == card1.getNape())) {
                            count++;
                        }
                    }
                    // nao preocupar com mais do que 2 suited high cards porque é 3 to royale flush
                    if (count == 0) {
                        holdList.remove(Integer.valueOf(i));
                    }
                }
                i++;
            }
        } else if (condition == "19. 4 to an inside straight with 2 high cards") {
            return this.insideStraight(hand);
        } else if (condition == "20. 3 to a straight flush (type 2)") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "21. 4 to an inside straight with 1 high card") {
            return this.insideStraight(hand);

        } else if (condition == "22. KQJ unsuited") {
            int i = 0;
            for (Card card : hand.getCards()) {
                if (card.getNumber() < 11) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else if (condition == "23. JT suited") {
            holdList = this.xyUnsuited(10, 11, hand);
        } else if (condition == "24. QJ unsuited") {
            holdList = this.xyUnsuited(11, 12, hand);
        } else if (condition == "25. 3 to a flush with 1 high card") {
            holdList = this.xtoFlush(3, hand);
        } else if (condition == "26. QT suited") {
            holdList = this.xyUnsuited(10, 12, hand);
        } else if (condition == "27. 3 to a straight flush (type 3)") {
            return this.xtoFlush(3, hand);
        } else if (condition == "28. KQ, KJ unsuited") {
            int i = 0;

            for (Card card : hand.getCards()) {
                if (card.getNumber() <= 10) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }
        } else if (condition == "29. Ace") {
            // Only hold the ace
            int i = 0;
            // nao é necessario procurar outros casos pq seriam pairs
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 1) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }
        } else if (condition == "30. KT suited") {
            holdList = this.xyUnsuited(10, 13, hand);
        } else if (condition == "31. Jack, Queen or King") {
            int i = 0;
            // nao é necessario procurar outros casos pq seriam pairs
            for (Card card : hand.getCards()) {
                if (card.getNumber() <= 10) {
                    holdList.remove(Integer.valueOf(i));
                }
                i++;
            }
        } else if (condition == "32. 4 to an inside straight with no high cards") {
            return this.insideStraight(hand);

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

    private ArrayList<Integer> insideStraight(Hand hand) {
        int[] hash = new int[13];
        ArrayList<Integer> holdList = new ArrayList<Integer>();
        holdList.add(0);
        holdList.add(1);
        holdList.add(2);
        holdList.add(3);
        holdList.add(4);

        // Ver quantas cartas ha de cada num
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }

        // catch weird inside straight
        int i = 0;
        if (hash[0] > 0 && hash[1] > 0 && hash[2] > 0 && hash[3] > 0) {
            for (Card card : hand.getCards()) {
                if (card.getNumber() > 4) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else if (hash[0] > 0 && hash[10] > 0 && hash[11] > 0 && hash[12] > 0) {
            for (Card card : hand.getCards()) {
                if (card.getNumber() != 1 && card.getNumber() < 11) {
                    holdList.remove(Integer.valueOf(i));
                    return holdList;
                }
                i++;
            }
        } else {
            int missing = -1;
            // Para encontrar o buraco no straight
            for (int index = 0; index < hash.length; index++) {
                int neighbouring = 0;
                if (index == 0) {
                    if (hash[1] > 0)
                        neighbouring = 1;
                } else if (index == 12) {
                    if (hash[11] > 0)
                        neighbouring = 1;
                } else {
                    neighbouring += hash[index - 1];
                    neighbouring += hash[index + 1];
                }
                if (hash[index] == 0 && neighbouring == 2) {
                    if (index > missing) {
                        missing = index;
                    }
                    // break;
                }
            }

            // System.out.println("Hole is " + missing);

            // Para o caso em q a carta a discartar esta "sozinha"
            for (int index = 0; index < hash.length; index++) {
                int neighbouring = 0;
                if (index == 0) {
                    if (hash[1] > 0)
                        neighbouring = 1;

                    // Pq o as tb pode ser neighbor do king
                    if (hash[12] > 0) {// King
                        neighbouring = 1;
                    }
                } else if (index == 12) {
                    if (hash[11] > 0)
                        neighbouring = 1;
                } else {
                    neighbouring += hash[index - 1];
                    neighbouring += hash[index + 1];
                }
                if (hash[index] == 1 && neighbouring == 0) {
                    if (index != missing + 1 && index != missing - 1) {
                        for (Card card : hand.getCards()) {
                            if (card.getNumber() == index + 1) {
                                // isto remove o inteiro i e não o objeto com indice i
                                holdList.remove(Integer.valueOf(i));
                                return holdList;
                            }
                            i++;
                        }
                    }
                }
            }
            // [0,0,0,1,1,1,0,1,0,0,1,0,0]
            // 6S 7C 8D TH JH
            // KS QD JD 9H 7C

            // O buraco esta na carta missing
            // Para o caso em q a carta a discartar tem um neighbor a 1
            for (int index = 0; index < hash.length; index++) {
                int neighbouring = 0;
                if (index == 0) {
                    if (hash[1] > 0)
                        neighbouring = 1;
                } else if (index == 12) {
                    if (hash[11] > 0)
                        neighbouring = 1;
                } else {
                    neighbouring += hash[index - 1];
                    neighbouring += hash[index + 1];
                }
                if (hash[index] == 1 && neighbouring == 1) {
                    if (index != missing + 1 && index != missing - 1) {
                        for (Card card : hand.getCards()) {
                            if (card.getNumber() == index + 1) {
                                // isto remove o inteiro i e não o objeto com indice i
                                holdList.remove(Integer.valueOf(i));
                                return holdList;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        return holdList;
    }

    private ArrayList<Integer> xyUnsuited(int x, int y, Hand hand) {
        ArrayList<Integer> holdList = new ArrayList<Integer>();
        holdList.add(0);
        holdList.add(1);
        holdList.add(2);
        holdList.add(3);
        holdList.add(4);

        int i = 0;

        // nao é necessario procurar outros casos pq seriam pairs
        for (Card card : hand.getCards()) {
            if (card.getNumber() != x && card.getNumber() != y) {
                holdList.remove(Integer.valueOf(i));
            }
            i++;
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
        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }

        // Find the nape that has x cards
        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == x) {
                true_nape = key;
                break;
            }
        }

        // If a card in the hand has the nape then hold it
        for (int i = 0; i < hand.getCards().size(); i++) {
            if (hand.getCards().get(i).getNape() == true_nape) {
                holdList.add(i);
            }
        }

        return holdList;

        // int i = 0;
        // for (Card card : hand.getCards()) {
        // if (card.nape != true_nape) {
        // holdList.remove(Integer.valueOf(i));
        // }
        // i++;
        // }
    }
}
