package videopoker.helpers;

import java.util.ArrayList;

/**
 * This class is used to store the statistics of the game.
 * <p>
 * It is composed of a list of StatCombination objects, keeping as well a
 * counter for the total value of gains and bets.
 * </p>
 * <p>
 * It also keeps track of the number of hands delt.
 * </p>
 */
public class Stats implements IStats{
    private static final String BLUE = "\u001B[38;2;0;157;224m";
    private static final String RESET = "\u001B[0;0m";

    int playersCredit;
    int allGains = 0;
    int allBets = 0;
    int nB;

    public int counter = 0;
    ArrayList<StatCombination> table;

    /**
     * Creates an instance of Stats with a given initial player credits. The table
     * of stats is then built with {@link videopoker.helpers.StatCombination}
     * objects of the default hand types.
     * 
     * @param credit The initial player credits.
     */
    public Stats(int credit) {
        playersCredit = credit;
        nB = 0;

        table = new ArrayList<StatCombination>();
        table.add(new StatCombination("Jack or Better", "JOB"));
        table.add(new StatCombination("Two Pair", "TP"));
        table.add(new StatCombination("Three of a Kind", "TOK"));
        table.add(new StatCombination("Straight", "S"));
        table.add(new StatCombination("Flush", "F"));
        table.add(new StatCombination("Full House", "FH"));
        table.add(new StatCombination("Four of a Kind", "FOK"));
        table.add(new StatCombination("Straight Flush", "SF"));
        table.add(new StatCombination("Royal Flush", "RF"));
        table.add(new StatCombination("Other", "O"));

    }

    /**
     * {@inheritDoc}
     */
    public void addStat(String k, int cashback, int bet) {
        nB++;

        k = (k == "F24" || k == "F5K" || k == "FA") ? "FOK" : k;

        for (StatCombination statCombination : table) {
            if (statCombination.key == k) {
                this.counter++;
                statCombination.incrementValue();

                this.allBets += bet;
                this.playersCredit += (cashback - bet);
                this.allGains += cashback;
                return;
            }
        }
    }

    /**
     * Prints the statistics table, iterating over every entry on the Stats Table,
     * showing the occurence of each type of hand and the total dealt hands.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Hand\t\tNb\n");
        str.append(BLUE + "────────────────────\n" + RESET);
        for (StatCombination statCombination : table) {
            str.append(statCombination);
        }
        str.append(BLUE + "────────────────────\n" + RESET);
        str.append(String.format("%-16s %d\n", "Total", nB));
        // str.append("Total\t\t");
        // str.append(nB);
        // str.append("\n");
        str.append(BLUE + "────────────────────\n" + RESET);
        str.append(String.format("%-16s %d (%.2f%%)", "Credit", playersCredit,
                100 * (float) this.allGains / (float) this.allBets));
        return str.toString();
    }

}
