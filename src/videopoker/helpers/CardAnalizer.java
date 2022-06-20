package videopoker.helpers;

import java.util.*;

import videopoker.Hand;
import videopoker.deck.Card;

/**
 * This class is used as a helper to evaluate and analyze the hand. 
 * <p> It recieves a hand and confronts it with all the possible hands, giving advice on the current hand </p>
 *
 *@param hand the hand to be analyzed
 */

public class CardAnalizer implements ICardAnalizer {

    /**
     * {@inheritDoc}
     */
    public String getPayTableResult(Hand hand) {
        if (isFlush(hand) && isRoyal(hand))
            return "RF";
        else if (isStraight(hand) && isFlush(hand))
            return "SF";
        else if (isFour(hand) == 1)
            return "FA";
        else if (isFour(hand) > 1 && isFour(hand) <= 4)
            return "F24";
        else if (isFour(hand) > 4)
            return "F5K";
        else if (isFH(hand))
            return "FH";
        else if (isFlush(hand))
            return "F";
        else if (isStraight(hand))
            return "S";
        else if (isTOK(hand))
            return "TOK";
        else if (isTP(hand))
            return "TP";
        else if (isJOB(hand))
            return "JOB";
        return "O";
    }

    /**
     * It checks if the hand is a royal flush
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean isRoyal(Hand hand) {
        ArrayList<Integer> royaList = new ArrayList<Integer>();
        royaList.add(1);
        royaList.add(10);
        royaList.add(11);
        royaList.add(12);
        royaList.add(13);
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (Card card : hand.getCards()) {
            aux.add(card.getNumber());
        }
        Collections.sort(aux);
        if (aux.equals(royaList))
            return true;
        return false;
    }

    /**
     * It checks if the hand is a flush
     * 
     * @param hand The hand to be evaluated.
     * @return A boolean value.
     */
    private boolean isFlush(Hand hand) {
        char aux = 'A';
        for (Card card : hand.getCards()) {
            if (aux == 'A') {
                aux = card.getNape();
            } else if (card.getNape() != aux) {
                return false;
            }
        }
        return true;
    }

    /**
     * It checks if the hand is a straight.
     * 
     * @param hand the hand to be evaluated
     * @return The method is returning a boolean value.
     */
    private boolean isStraight(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int start = 0;
        int hit = 0;
        // if the ace is a high ace
        if (hash[0] == 1 && hash[12] == 1 && hash[11] == 1 && hash[10] == 1 && hash[9] == 1)
            return true;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 0) {
                hit++;
                if (start == 0)
                    start = 1;
            } else {
                // se da um miss e ainda n comecou e ainda nao ha 5 cartas seguidas
                if (start != 0 && hit < 5)
                    return false;
            }
        }
        return true;
    }

    /**
     * If there are four of a kind, return the number of the card. Otherwise, return
     * 0
     * This does not return a boolean, because the payout table differentiates the
     * pay
     * according to the card that has 4 occurences on the hand.
     * 
     * @param hand The hand that is being evaluated.
     * @return The number of the card that is a four of a kind.
     */
    private int isFour(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        for (int index = 0; index < hash.length; index++) {
            if (hash[index] == 4) {
                return index + 1;
            }
        }
        return 0;
    }

    /**
     * If there is a pair of a card and an three of a kind of another, then it's a
     * full house
     * 
     * @param hand The hand to be checked
     * @return A boolean value.
     */
    private boolean isFH(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        for (int i : hash) {
            if (i == 3) {
                for (int j : hash) {
                    if (j == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If there are three of any card in the hand, return true
     * 
     * @param hand The hand that is being checked.
     * @return A boolean value.
     */
    private boolean isTOK(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        for (int i : hash) {
            if (i == 3)
                return true;
        }
        return false;
    }

    /**
     * It checks if the hand is a two pair.
     * 
     * @param hand The hand that is being evaluated.
     * @return The method is returning a boolean value.
     */
    private boolean isTP(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int aux = 0;
        for (Integer integer : hash) {
            if (integer == 2)
                aux++;
        }
        return aux == 2;
    }

    /**
     * If the hand has a Jack or better, return true
     * This includes a Jack, a Queen, a King or an Ace
     * 
     * @param hand The hand that is being checked.
     * @return A boolean value.
     */
    private boolean isJOB(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        if (hash[0] == 2)
            return true;

        for (int i = 10; i < hash.length; i++) {
            if (hash[i] == 2)
                return true;
        }
        return false;
    }

    /**
     * If the hand has three aces, return true
     * 
     * @param hand The hand that is being checked.
     * @return A boolean value.
     */
    private boolean is3A(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        if (hash[0] == 3)
            return true;
        return false;
    }

    /**
     * If there are 4 cards of the same nape, and those 4 cards can make up a royal
     * it returns true
     * 
     * @param hand the hand that is being checked
     * @return A boolean value.
     */
    private boolean is4toRF(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!(hash_nape.containsValue(4) || hash_nape.containsValue(5)))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) >= 4) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        if ((hash[0] + hash[12] + hash[11] + hash[10] + hash[9] >= 4))
            return true;
        return false;
    }

    /**
     * If the hand has two cards of the same number, and those cards are high Cards,
     * then the hand has a high pair
     * then the method returns true, otherwise it returns false.
     * 
     * @param hand The hand that is being checked.
     * @return A boolean value.
     */
    private boolean isHP(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }

        return hash[0] == 2 || hash[12] == 2 || hash[11] == 2 || hash[10] == 2;
    }

    /**
     * If the hand has two cards of the same number, and those cards are low Cards,
     * then the hand has a low pair
     * then the method returns true, otherwise it returns false.
     * 
     * @param hand The hand that is being checked.
     * @return A boolean value.
     */
    private boolean isLP(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        for (int i = 1; i < 10; i++) {
            if (hash[i] == 2)
                return true;
        }

        return false;
    }

    /**
     * If the hand has a card with the given number, return true, otherwise return
     * false.
     * 
     * @param c    The card number you want to check for
     * @param hand The hand that the player is currently holding.
     * @return A boolean value.
     */
    private boolean hasCard(int c, Hand hand) {
        for (Card card : hand.getCards()) {
            if (card.getNumber() == c)
                return true;
        }
        return false;
    }

    /**
     * If there are 4 cards of the same nape, and those 4 cards can make up a
     * straight it returns true
     * 
     * @param hand the hand that is being checked
     * @return A boolean value.
     */
    private boolean is4toSF(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(4))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 4) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        int start = 0;
        int skip = 0;
        int hit = 0;

        for (int i : hash) {
            if (i == 1) {
                hit++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 4)
                    skip++;
            }
        }
        if (skip <= 1)
            return true;

        return false;
    }

    /**
     * If the hand has 4 cards of the same nape, return true
     * 
     * @param hand the hand that is being checked
     * @return The method is returning a boolean value.
     */
    private boolean is4toF(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(4))
            return false;
        return true;
    }

    /**
     * If there are 3 cards of the same nape, and those 3 cards can make up a royal
     * it returns true, otherwise it returns false
     * 
     * @param hand the hand that is being checked
     * @return A boolean value.
     */
    private boolean is3toRF(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        if ((hash[0] + hash[12] + hash[11] + hash[10] + hash[9] >= 3))
            return true;
        return false;
    }

    /**
     * If there are four cards in a row, and the first card is not an ace, then it's
     * an outside straight.
     * The straight cannot contain an Ace because A234 and JQKA count as an inside
     * straight because they are at
     * an extreme end.
     * 
     * @param hand The hand to be checked
     * @return The method is returning a boolean value.
     */
    private boolean isOS(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }

        int start = 0;
        int hit = 0;
        for (int i : hash) {
            if (i > 0 && hit < 4) {
                hit++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 4) {
                    hit = 0;
                }
            }
        }
        return (hit == 4 && hash[0] != 1);
    }

    /**
     * If the hand contains at least 4 of the 5 highest cards, return true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean isHighUnsuited(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        if ((hash[0] + hash[12] + hash[11] + hash[10] >= 4))
            return true;
        return false;
    }

    /**
     * If there are three cards of the same suit, and the remaining two cards are
     * not of the same suit,
     * and the three cards are not a straight, then the hand contains a 3 to
     * straight flush type 1.
     * 
     * @param hand the hand to be evaluated
     * @return The method is returning a boolean value.
     */
    private boolean is3toSFT1(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        int start = 0;
        int skip = 0;
        int hit = 0;
        int highcard = 0;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 1) {
                hit++;
                if (i >= 10 || i == 0)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 3)
                    skip++;
            }
        }

        return (highcard >= skip && highcard != 0);
    }

    /**
     * If there are 4 cards to a straight, and 3 of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is4toIS3HC(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int start = hash[0] == 1 ? 1 : 0;
        int skip = 0;
        int hit = hash[0] == 1 ? 1 : 0;
        int highcard = hash[0] == 1 ? 1 : 0;

        for (int i = hash.length - 1; i >= 0; i--) {
            if (hash[i] > 0 && hit < 4) {
                hit++;
                if (i >= 10 || i == 0)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 4)
                    skip++;
            }
        }

        return (highcard == 3 && (skip == 1 || skip == 0) && hit == 4);
    }

    /**
     * If the hand contains a Queen and a Jack of the same suit, return true
     * 
     * @param hand the hand that is being checked
     * @return The method is returning a boolean value.
     */
    private boolean isQJS(Hand hand) {
        for (Card card : hand.getCards()) {
            if (card.getNumber() == 12) {
                for (Card card1 : hand.getCards()) {
                    if (card1.getNumber() == 11 && card.getNape() == card1.getNape()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If there are 3 cards to a flush, and 2 of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is3toF2HC(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }
        if (hash[0] + hash[12] + hash[11] + hash[10] >= 2)
            return true;
        return false;
    }

    /**
     * If the hand has two suited high cards it returns tru, otherwise it returns
     * false
     * 
     * @param hand the hand that is being checked
     * @return A boolean value.
     */
    private boolean is2SHC(Hand hand) {
        for (Card card : hand.getCards()) {

            if (card.getNumber() == 1 || card.getNumber() >= 11) {
                for (Card card1 : hand.getCards()) {
                    if ((card1.getNumber() == 1 || card1.getNumber() >= 11) && card.getNape() == card1.getNape()
                            && card1.getNumber() != card.getNumber()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If there are 4 cards to a straight, and 2 of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is4toIS2HC(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int start = 0;
        int skip = 0;
        int hit = 0;
        int highcard = 0;

        for (int i = hash.length - 1; i > 6; i--) {
            if (hash[i] > 0) {
                hit++;
                if (i >= 10)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0)
                    skip++;
            }
        }
        return (highcard == 2 && skip == 1 && hit == 4);
    }

    /**
     * If there are three cards of the same suit, and the remaining two cards are
     * not of the same suit,
     * and the three cards are not a straight, then the hand contains a 3 to
     * straight flush type 2.
     * 
     * @param hand the hand to be evaluated
     * @return The method is returning a boolean value.
     */
    private boolean is3toSFT2(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        // Ver quantas cartas de cada naipe ha
        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }

        // Se não existem 3 cartas do mm naipe já nao vai ser flush
        if (!hash_nape.containsValue(3))
            return false;

        // Ver qual é o naipe que tem 3 cartas
        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        // Ver quais são as cartas que teem o naipe que dará o flush
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        int highcard = 0;
        int skip = 0;
        int start = 0;
        int hit = 0;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 0 && hit < 3) {
                hit++;
                if (i >= 10)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 3) // Vai haver até 2 gaps, nunca mais
                    skip++;
            }
        }
        return ((highcard == 0 && skip == 1 && hit == 3) // Straight flush draw with one gap no High Card
                || (highcard == 1 && skip == 2 && hit == 3) // Straight flush draw with two gaps one High Card
                || (hash[0] == 1 && skip < 3)
                || (hit == 3 && skip == 0 && highcard == 0)); // Straight flush draw with no gaps
    }

    /**
     * If there are three cards of the same suit, and the remaining two cards are
     * not of the same suit,
     * and the three cards are not a straight, then the hand contains a 3 to
     * straight flush type 3.
     * 
     * @param hand the hand to be evaluated
     * @return The method is returning a boolean value.
     */
    private boolean is3toSFT3(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }

        int start = 0;
        int skip = 0;
        int hit = 0;
        int highcard = 0;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 1) {
                hit++;
                if (i >= 10 || i == 0)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 3)
                    skip++;
            }
        }

        return (highcard == 0 && skip == 2);
    }

    /**
     * If there are 4 cards to a straight, and 1 of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is4toIS1HC(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int start = 0;
        int skip = 0;
        int hit = 0;
        int highcard = hash[0] == 1 ? 1 : 0;

        for (int i = hash.length - 1; i > 0; i--) {
            if (hash[i] > 0 && hit < 4) {
                hit++;
                if (i >= 10)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if ((hash[0] == 1 && i < 5) || (start != 0 && i > 5))
                    skip++;

            }
        }
        if (hash[0] == 1 && hash[1] == 1 && hash[2] == 1 && hash[3] == 1)
            return true;
        return (highcard == 1 && skip == 1 && hit == 4);
    }

    /**
     * If there are 4 cards to a straight, and none of them are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is4toIS0HC(Hand hand) {
        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            hash[card.getNumber() - 1]++;
        }
        int start = 0;
        int skip = 0;
        int hit = 0;
        int highcard = 0;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 0 && hit < 4) {
                hit++;
                if (i >= 10 || i == 0)
                    highcard++;
                if (start == 0)
                    start = 1;
            } else {
                if (start != 0 && hit < 4) {
                    skip++;
                    hit = skip == 2 ? 0 : hit;
                    skip = skip == 2 ? 0 : skip;
                }
            }
        }

        if (highcard < 2 && skip == 1 && hit == 4)
            return true;

        return false;
    }

    /**
     * If the hand contains a Jack and a Ten of the same suit, then it
     * returns true, otherwise it returns false
     * 
     * @param hand The hand that is being checked.
     * @return The method is returning a boolean value.
     */
    private boolean isJTS(Hand hand) {
        for (Card card : hand.getCards()) {
            if (card.getNumber() == 11) {
                for (Card card1 : hand.getCards()) {
                    if (card1.getNumber() == 10 && card.getNape() == card1.getNape()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If there are 3 cards to a flush, and 1 of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is3toF1HC(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }
        if (hash[0] + hash[12] + hash[11] + hash[10] >= 1)
            return true;
        return false;
    }

    /**
     * If the hand has a Queen and a 10 of the same nape, then it returns true
     * 
     * @param hand The hand that is being checked
     * @return A boolean value
     */
    private boolean isQTS(Hand hand) {
        for (Card card : hand.getCards()) {
            if (card.getNumber() == 12) { // Check if card is Queen
                for (Card card1 : hand.getCards()) { // Search for the 10 and check if they have the same nape
                    if (card1.getNumber() == 10 && card.getNape() == card1.getNape()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If the hand contains a King and a Ten of the same nape, then it returns true
     * 
     * @param hand the hand that is being checked
     * @return The method is returning a boolean value.
     */
    private boolean isKTS(Hand hand) {
        for (Card card : hand.getCards()) {
            if (card.getNumber() == 13) {
                for (Card card1 : hand.getCards()) {
                    if (card1.getNumber() == 10 && card.getNape() == card1.getNape()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * If there are 3 cards to a flush, and none of those cards are high cards,
     * it returns true
     * 
     * @param hand The hand to be evaluated
     * @return A boolean value.
     */
    private boolean is3toF0HC(Hand hand) {
        HashMap<Character, Integer> hash_nape = new HashMap<Character, Integer>();
        hash_nape.put('H', 0);
        hash_nape.put('S', 0);
        hash_nape.put('D', 0);
        hash_nape.put('C', 0);
        char true_nape = 'A';

        for (Card card : hand.getCards()) {
            hash_nape.put(card.getNape(), hash_nape.get(card.getNape()) + 1);
        }
        if (!hash_nape.containsValue(3))
            return false;

        for (Character key : hash_nape.keySet()) {
            if (hash_nape.get(key) == 3) {
                true_nape = key;
                break;
            }
        }

        int[] hash = new int[13];
        for (Card card : hand.getCards()) {
            if (card.getNape() == true_nape)
                hash[card.getNumber() - 1]++;
        }
        if (hash[0] + hash[12] + hash[11] + hash[10] >= 0)
            return true;
        return false;
    }

   /**
    * If compares the hand with several cases using the methods defined in this class to
    * return the best case scenario 
    * 
    * @param hand the hand to be analyzed
    * @return The method is returning a string that is the advice for the player.
    */
    public String getAdviceFromTable(Hand hand) {
        if ((isFlush(hand) && isRoyal(hand)) || isFour(hand) != 0 || (isStraight(hand) && isFlush(hand)))
            return "1. Straight flush, four of a kind, royal flush";
        if (is4toRF(hand))
            return "2. 4 to a royal flush";
        if (is3A(hand))
            return "3. Three aces";
        if (isStraight(hand) || isFH(hand) || isFlush(hand))
            return "4. Straight, flush, full house";
        if (isTOK(hand))
            return "5. Three of a kind (except aces)";
        if (is4toSF(hand))
            return "6. 4 to a straight flush";
        if (isTP(hand))
            return "7. Two pair";
        if (isHP(hand))
            return "8. High pair";
        if (is4toF(hand))
            return "9. 4 to a flush";
        if (is3toRF(hand))
            return "10. 3 to a royal flush";
        if (isOS(hand))
            return "11. 4 to an outside straight";
        if (isLP(hand))
            return "12. Low pair";
        if (isHighUnsuited(hand))
            return "13. AKQJ unsuited";
        if (is3toSFT1(hand))
            return "14. 3 to a straight flush (type 1)";
        if (is4toIS3HC(hand))
            return "15. 4 to an inside straight with 3 high cards";
        if (isQJS(hand))
            return "16. QJ suited";
        if (is3toF2HC(hand))
            return "17. 3 to a flush with 2 high cards";
        if (is2SHC(hand))
            return "18. 2 suited high cards";
        if (is4toIS2HC(hand))
            return "19. 4 to an inside straight with 2 high cards";
        if (is3toSFT2(hand))
            return "20. 3 to a straight flush (type 2)";
        if (is4toIS1HC(hand))
            return "21. 4 to an inside straight with 1 high card";
        if (hasCard(13, hand) && hasCard(12, hand) && hasCard(11, hand))
            return "22. KQJ unsuited";
        if (isJTS(hand))
            return "23. JT suited";
        if (hasCard(12, hand) && hasCard(11, hand))
            return "24. QJ unsuited";
        if (is3toF1HC(hand))
            return "25. 3 to a flush with 1 high card";
        if (isQTS(hand))
            return "26. QT suited";
        if (is3toSFT3(hand))
            return "27. 3 to a straight flush (type 3)";
        if ((hasCard(13, hand) && hasCard(12, hand)) || (hasCard(13, hand) && hasCard(11, hand)))
            return "28. KQ, KJ unsuited";
        if (hasCard(1, hand))
            return "29. Ace";
        if (isKTS(hand))
            return "30. KT suited";
        if (hasCard(11, hand) || hasCard(12, hand) || hasCard(13, hand))
            return "31. Jack, Queen or King";
        if (is4toIS0HC(hand))
            return "32. 4 to an inside straight with no high cards";
        if (is3toF0HC(hand))
            return "33. 3 to a flush with no high cards";
        return "34. Discard everything";
    }
}
