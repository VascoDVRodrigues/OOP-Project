package videopoker.helpers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class is used to store the payout table for the game.
 */
public class PayoutTable implements IPayoutTable {

    protected Map<String, Payout> table;

    /**
     * Constructor function for the PayoutTable class.
     * It creates a new LinkedHashMap to store the Payout objects, the HashMap is
     * initialazed with the default Double Bonus 10/7 game variant values.
     * the HashMap uses as keys the short version of each hand.
     * 
     * <p>
     * Default implemented table:
     * </p>
     * 
     * <pre>
        |        Hand        | 1 credit | 2 credits | 3 credits | 4 credits | 5 credits |
        |:------------------:|:--------:|:---------:|:---------:|:---------:|-----------|
        | Royal Flush        | 250      | 500       | 750       | 1000      | 4000      |
        | Straight Flush     | 50       | 100       | 150       | 200       | 250       |
        | Four Aces          | 1600     | 320       | 480       | 640       | 800       |
        | Four 2-4           | 80       | 160       | 240       | 320       | 400       |
        | Four 5-K           | 50       | 100       | 150       | 200       | 250       |
        | Full House         | 10       | 20        | 30        | 40        | 50        |
        | Flush              | 7        | 14        | 21        | 28        | 35        |
        | Straight           | 5        | 10        | 15        | 20        | 25        |
        | Three of a Kind    | 3        | 6         | 9         | 12        | 15        |
        | Two Pair           | 1        | 2         | 3         | 4         | 5         |
        | Jacks or Better    | 1        | 2         | 3         | 4         | 5         |
        | Theoretical Return | 99.1%    | 99.1%     | 99.1%     | 99.1%     | 100.2%    |
     * </pre>
     * 
     * {@link videopoker.helpers.Payout}
     */
    public PayoutTable() {
        table = new LinkedHashMap<String, Payout>();

        int[][] amounts = {
                { 250, 500, 750, 1000, 4000 },
                { 50, 100, 150, 200, 250 },
                { 160, 320, 480, 640, 800 },
                { 80, 160, 240, 320, 400 },
                { 50, 100, 150, 200, 250 },
                { 10, 20, 30, 40, 50 },
                { 7, 14, 21, 28, 35 },
                { 5, 10, 15, 20, 25 },
                { 3, 6, 9, 12, 15 },
                { 1, 2, 3, 4, 5 },
                { 1, 2, 3, 4, 5 }
        };
        table.put("RF", new Payout("Royal Flush", "RF", amounts[0]));
        table.put("SF", new Payout("Straight Flush", "SF", amounts[1]));
        table.put("FA", new Payout("Four Aces", "FA", amounts[2]));
        table.put("F24", new Payout("Four 2-4", "F24", amounts[3]));
        table.put("F5K", new Payout("Four 5-K", "F5K", amounts[4]));
        table.put("FH", new Payout("Full House", "FH", amounts[5]));
        table.put("F", new Payout("Flush", "F", amounts[6]));
        table.put("S", new Payout("Straight", "S", amounts[7]));
        table.put("TOK", new Payout("Three of a Kind", "TOK", amounts[8]));
        table.put("TP", new Payout("Two Pair", "TP", amounts[9]));
        table.put("JOB", new Payout("Jack or Better", "JOB", amounts[10]));

    }

    /**
     * {@inheritDoc}
     */
    public void modifyValue(String key, int bet, int newValue) {
        table.get(key).modifyValue(bet, newValue);
    }

    /**
     * {@inheritDoc}
     */
    public int getValue(String key, int bet) {
        return table.get(key).getValue(bet);
    }

    /**
     * {@inheritDoc}
     */
    public void addPayout(String name, String key, int[] amounts) {
        Iterator<Entry<String, Payout>> iterator = table.entrySet().iterator();
        Entry<String, Payout> row = iterator.next();
        Payout values = row.getValue();

        if (amounts.length != values.amounts.size()) {
            throw new IllegalArgumentException("Amounts need to be of the same size as the rest of the table");
        }
        table.put(key, new Payout(name, key, values.bets(), amounts));
    }

    /**
     * This method is used to remove a certain hand type payout
     * from the payout table.
     * <p>
     * It takes the short name of the hand type to be removed as a parameter and
     * returns void.
     * </p>
     * 
     * @param key The short name of the hand type to be removed.
     */
    public void removePayout(String key) {
        table.remove(key);
    }

    /**
     * This method works similarly to the
     * <b>{@link videopoker.helpers.PayoutTable#addPayout}</b>, but instead of
     * adding a new hand type payout, it adds a new bet value for all existing
     * payout
     * hands.
     * <p>
     * It recieves a Map of Strings to ints, representing the value of the payout
     * for the specified String key, as well as the new betting value.
     * </p>
     * <p>
     * The Map, needs to have the same number of hands as the table, so it can be
     * iterated and his values copied to the payout table.
     * </p>
     * 
     * @param bets
     * @param bet
     */
    public void addBet(Map<String, Integer> bets, int bet) {
        // Check for size of bets
        if (bets.size() != table.size()) {
            System.out.println("Error: bets size does not match table size");
            return;
        }
        // Add bets to table
        for (Entry<String, Integer> entry : bets.entrySet()) {
            table.get(entry.getKey()).addBet(bet, entry.getValue());
        }
    }

    /**
     * This method works similarly to the
     * <b>{@link videopoker.helpers.PayoutTable#removePayout}</b>, but instead of
     * removing a hand type payout, it removes a bet value for all existing payout
     * hands.
     * <p>
     * It recieves an int representing the bet value to be removed. Iterating over
     * every hand in the table, and removing the his payout value from the table.
     * </p>
     * 
     * @param bet The int representing the bet value to be removed.
     */
    public void removeBet(int bet) {
        // Remove bets from table
        for (Entry<String, Payout> entry : table.entrySet()) {
            entry.getValue().removeBet(bet);
        }
    }

    /**
     * This method <b>@Overides</b> the <code>toString</code> method in order to
     * create a print function that outputs the payout values in a table format.
     * <p>
     * The method builds a string, retriving first the header of the table from one
     * of the rows in the table, and then each of the rows one by one from the
     * table.
     * </p>
     * 
     * @return The string representing the payout table.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        Iterator<Entry<String, Payout>> iterator = table.entrySet().iterator();
        Entry<String, Payout> row = iterator.next();
        Payout values = row.getValue();

        str.append(values.printHeader());
        while (iterator.hasNext()) {
            str.append(values);
            values = iterator.next().getValue();
        }
        str.append(values);

        return str.toString();
    }
}
