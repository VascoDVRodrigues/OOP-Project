package wip;

import java.util.ArrayList;

public class Stats {
    private static final String BLUE = "\u001B[38;2;0;157;224m";
    private static final String RESET = "\u001B[0;0m";

    int playersCredit;
    int allGains = 0;
    int allBets = 0;
    int nB;
    int nO;
    ArrayList<StatCombination> table;

    public Stats(int credit){
        playersCredit = credit;
        nB = 0;
        nO = 0;
        table = new ArrayList<StatCombination>();
        table.add(new StatCombination("Jack or Better","JOB"));
        table.add(new StatCombination("Two Pair","TP"));
        table.add(new StatCombination("Three of a Kind","TOK"));
        table.add(new StatCombination("Straight","S"));
        table.add(new StatCombination("Flush","F"));
        table.add(new StatCombination("Full House","FH"));
        table.add(new StatCombination("Four of a Kind","FOK"));
        table.add(new StatCombination("Straight Flush","SF"));
        table.add(new StatCombination("Royal Flush","RF"));
        table.add(new StatCombination("Other","O"));

    }

    public void addStat(String k, int cashback, int bet){
        nB++;
        for (StatCombination statCombination : table) {
            if(statCombination.key==k){
                statCombination.incrementValue();
                this.allBets += bet;
                this.playersCredit += (cashback-bet);
                this.allGains += cashback;
                return;
            }
        }
        nO++;
    }
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
        str.append(String.format("%-16s %d (%f%%)", "Credit", playersCredit, 100*(float)this.allGains/(float)this.allBets));
        return str.toString();
    }

}
