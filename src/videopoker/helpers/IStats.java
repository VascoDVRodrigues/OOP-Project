package videopoker.helpers;

/**
 * Interface for statistics.
 */

public interface IStats {

    /**
     * This method is used to increment a specified hand stat.
     * <p>
     * The String <b>k</b> is used to identify the hand to be incremented, the
     * <b>cashback</b> is the amount of credits won and the <b>bet</b> is the amount
     * of credits betted.
     * </p>
     * 
     * <p>
     * This method increases the stats, the gains, and updates the player balance.
     * 
     * @param k        Short name of the type of hand
     * @param cashback Amount won by player
     * @param bet      Amount betted
     */
    public void addStat(String k, int cashback, int bet);
}
