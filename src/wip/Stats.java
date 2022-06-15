package wip;

import java.util.ArrayList;

public class Stats {
    int iCredit;
    int nB;
    int nO;
    ArrayList<StatCombination> table;

    public Stats(int credit){
        iCredit = credit;
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

    public void addStat(String k){
        nB++;
        for (StatCombination statCombination : table) {
            if(statCombination.key==k){
                statCombination.incrementValue();
                return;
            }
        }
        nO++;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Hand           Nb\n");
        for (StatCombination statCombination : table) {
            str.append(statCombination);
        }
        str.append("Total          ");
        str.append(nB);
        str.append("\n");
        return str.toString();
    }

}
