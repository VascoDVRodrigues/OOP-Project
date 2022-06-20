package videopoker.helpers;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class is the base element for the PayoutTable class.
 * <p>
 * It stores the values a hand can payout depending on the betted value.
 * </p>
 */
public class Payout {
    protected String name;
    protected String key;
    protected HashMap<Integer, Integer> amounts;

    /**
     * This is the constructor for the Payout class.
     * <p>
     * It takes the name of the hand type, the short name of the hand type, and an
     * array of ints corresponding to the price values for a sequancial bet values.
     * </p>
     * 
     * @param name    The name of the hand type.
     * @param key     The short name of the hand type.
     * @param amounts The array of ints representing the payout values for each
     *                bet.
     */
    public Payout(String name, String key, int[] amounts) {
        this.name = name;
        this.key = key;
        this.amounts = new HashMap<Integer, Integer>();

        for (int i = 0; i < amounts.length; i++) {
            this.amounts.put(i + 1, amounts[i]);
        }
    }

    /**
     * This is the constructor for the Payout class.
     * <p>
     * It takes the name of the hand type, the short name of the hand type, an array
     * of the existing bet values, and an array of ints corresponding to the price
     * values for said bet values.
     * </p>
     * <p>
     * This constructor is used to create a new Payout object to be added to an
     * already existing PayoutTable. For this reason it needs to be passed the
     * existing bet values.
     * </p>
     * 
     * @param name      The name of the hand type.
     * @param key       The short name of the hand type.
     * @param betValues The array of ints representing the bet values.
     * @param amounts   The array of ints representing the payout values for each
     *                  bet.
     */
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

    /**
     * This method is used to get the payout value of a certain bet.
     * 
     * @param bet The bet value from which to get the payout value.
     * @return The payout value.
     * 
     * @see videopoker.helpers.Payout#modifyValue(int, int)
     */
    public int getValue(int bet) {
        return this.amounts.get(bet);
    }

    /**
     * This method is used to modify the payout value of a certain bet.
     * 
     * @param bet      The bet value from which to get the payout value.
     * @param newValue The new payout value.
     * 
     * @see videopoker.helpers.Payout#getValue(int)
     */

    public void modifyValue(int bet, int newValue) {
        this.amounts.put(bet, newValue);
    }

    /**
     * This method is used to add a new payout value for a new bet value.
     * 
     * @param bet   The bet value for which to add the payout value.
     * @param value The new payout value.
     * 
     * @see videopoker.helpers.PayoutTable#removeBet(int)
     */
    public void addBet(int bet, int value) {
        this.amounts.put(bet, value);
    }

    /**
     * This method is used to remove a bet value from this hand type payout.
     * 
     * @param bet The bet value to remove.
     * 
     * @see videopoker.helpers.Payout#addBet(int, int)
     */

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

    /**
     * To correctly display the row of payout values in the payout table, the
     * toString method was Overrided to display the payout values in a table format.
     * 
     * @return The payout values in a table format.
     */
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

    /**
     * This method generates the header for the payout table.
     * <p>
     * It uses the number of amounts present in the Payout object in order to
     * generate the blue line as well as the number of bets.
     * </p>
     * 
     * @return The header for the payout table.
     */
    public String printHeader() {
        // Codes for colored text
        String BLUE = "\u001B[38;2;0;157;224m";
        String RESET = "\u001B[0;0m";
        
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        str1.append(BLUE + "────────────────");
        str2.append(String.format("%16s ", " "));
        str3.append(BLUE + "────────────────");
        for (Entry<Integer, Integer> entry : this.amounts.entrySet()) {
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
