package wip;

import java.util.HashMap;
import java.util.Map.Entry;

public class Payout {
    private static final String BLUE = "\u001B[38;2;0;157;224m";
    private static final String RESET = "\u001B[0;0m";

    protected String name;
    protected String key;
    protected HashMap<Integer, Integer> amounts;

    public Payout(String name, String key, int[] amounts) {
        this.name = name;
        this.key = key;
        this.amounts = new HashMap<Integer, Integer>();

        for (int i = 0; i < amounts.length; i++) {
            this.amounts.put(i + 1, amounts[i]);
        }
    }

    public Payout(String name, String key, int[] betValues, int[] amounts) {
        this.name = name;
        this.key = key;
        this.amounts = new HashMap<Integer, Integer>();

        if (betValues.length != amounts.length) {
            throw new IllegalArgumentException("Amounts need to be of the same size");
        }
        for (int i = 0; i < betValues.length; i++) {
            this.amounts.put(betValues[i], amounts[i]);
        }
    }

    public int getValue(int bet) {
        return this.amounts.get(bet);
    }

    public void modifyValue(int bet, int newValue) {
        this.amounts.put(bet, newValue);
    }

    public void addBet(int bet, int value) {
        this.amounts.put(bet, value);
    }

    public void removeBet(int bet) {
        this.amounts.remove(bet);
    }

    public int[] bets() {
        int[] bets = new int[this.amounts.size()];
        int i = 0;
        for (Entry<Integer, Integer> entry : this.amounts.entrySet()) {
            bets[i] = entry.getKey();
            i++;
        }
        return bets;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-16s ", name));
        for (Entry<Integer, Integer> entry : amounts.entrySet()) {
            str.append(String.format("%10d", entry.getValue()));
        }
        str.append("\n");
        return str.toString();
    }

    public String printHeader() {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        str1.append(BLUE + "────────────────");
        str2.append(String.format("%16s ", " "));
        str3.append(BLUE + "────────────────");
        for (Entry<Integer, Integer> entry : amounts.entrySet()) {
            str1.append("───────────");
            str2.append(String.format("%10s", entry.getKey() + " Cred."));
            str3.append("───────────");
        }
        str1.append("\n" + RESET);
        str2.append("\n");
        str3.append("\n" + RESET);

        return str1.append(str2.append(str3)).toString();
    }
}
