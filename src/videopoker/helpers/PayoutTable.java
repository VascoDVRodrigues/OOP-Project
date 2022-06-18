package videopoker.helpers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PayoutTable {

    protected Map<String, Payout> table;

    /**
     * Constructor function for the PayoutTable class.
     * It creates a new LinkedHashMap to store the Payout objects, the HashMap is initialazed with the default Double Bonus 10/7 game variant values.
     * the HashMap uses as keys the short version of each hand.
     * Default implemented table:          
     *    -----------------------------------------------------------------------------
     *      Hand          1 credit    2 credits    3 credits    4 credits   5 credits
     *    -----------------------------------------------------------------------------
     *      Royal Flush        250          500          750         1000        4000
     *      Straight Flush      50          100          150          200         250
     *      Four Aces          160          320          480          640         800
     *      Four 2–4            80          160          240          320         400
     *      Four 5–K            50          100          150          200         250
     *      Full House          10           20           30           40          50
     *      Flush                7           14           21           28          35
     *      Straight             5           10           15           20          25
     *      Three of a Kind      3            6            9           12          15
     *      Two Pair             1            2            3            4           5
     *      Jacks or Better      1            2            3            4           5
     *      Theoretical Return 99.1%       99.1%        99.1%        99.1%      100.2%
     *    -----------------------------------------------------------------------------
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

    public void modifyValue(String key, int bet, int newValue) {
        table.get(key).modifyValue(bet, newValue);
    }

    public int getValue(String key, int bet) {
        return table.get(key).getValue(bet);
    }

    public void addPayout(String name, String key, int[] amounts) {
        Iterator<Entry<String, Payout>> iterator = table.entrySet().iterator();
        Entry<String, Payout> row = iterator.next();
        Payout values = row.getValue();

        if (amounts.length != values.amounts.size()) {
            throw new IllegalArgumentException("Amounts need to be of the same size as    the rest of the table");
        }
        table.put(key, new Payout(name, key, values.bets(), amounts));
    }

    public void removePayout(String key) {
        table.remove(key);
    }

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

    public void removeBet(int bet) {
        // Remove bets from table
        for (Entry<String, Payout> entry : table.entrySet()) {
            entry.getValue().removeBet(bet);
        }
    }

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
