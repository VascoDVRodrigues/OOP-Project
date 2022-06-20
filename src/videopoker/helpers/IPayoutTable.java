package videopoker.helpers;


/**
 * Interface for the payout table.
 */
public interface IPayoutTable {

    /**
     * This method is used to modify a certain payout value of the table. It takes a
     * string for the specified hand and two ints one for the specified bet value
     * and another as the new payout value.
     * 
     * @param key      The string representing the hand to be modified.
     * @param bet      The int representing the bet value to be modified.
     * @param newValue The int representing the new payout value.
     */
    public void modifyValue(String key, int bet, int newValue);

    /**
     * This method is used to get the payout value of a certain hand. It takes a
     * string for the specified hand and an int for the specified bet value.
     * 
     * @param key The string representing the hand to be modified.
     * @param bet The int representing the bet value to be modified.
     * @return The int representing the payout value.
     */
    public int getValue(String key, int bet);

    /**
     * This method adds a new payout hand to the table. This method requires to be
     * passed the name of the new hand type, the short name of the hand type, and an
     * array of ints representing the payout values for each bet value.
     * <p>
     * The amounts array needs to be of the same length as the tables bet numbers.
     * </p>
     * 
     * @param name    The name of the new hand type.
     * @param key     The short name of the new hand type.
     * @param amounts The array of ints representing the payout values for each
     *                bet value.
     */
    public void addPayout(String name, String key, int[] amounts);
}
