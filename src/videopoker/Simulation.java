package videopoker;

import java.util.ArrayList;

import videopoker.deck.RegularDeck;

import videopoker.helpers.*;

public class Simulation extends Game {
    private CardAnalizer analizer = new CardAnalizer();
    private PayoutTable pay = new PayoutTable();
    private Stats stats;
    private Advisor advisor = new Advisor();

    private int bet, nbdeals;

    /**
     * 
     */
    public Simulation(Player p, int bet, int nbdeals) {
        if (bet > maxBet) {
            System.out.println("Maximum bet is " + this.maxBet);
        }
        this.deck = new RegularDeck();

        this.player = p;

        stats = new Stats(this.player.getCredits());

        this.bet = bet;
        this.nbdeals = nbdeals;
    }

    @Override
    public void play() {
        // strategy is to bet, deal, ask for advice and follow the advice

        for (int i = 0; i < nbdeals; i++) {
            // BET
            this.player.bet(bet);

            // DEAL
            ((RegularDeck) this.deck).shuffle();
            this.giveHand();

            // System.out.println(this.hand);

            // GET ADVICE
            String condition = this.analizer.getAdviceFromTable(this.hand);
            // System.out.println(condition);

            // if (condition.equals("14. 3 to a straight flush (type 1)")) {
            // }

            ArrayList<Integer> holdList = advisor.getHoldList(condition, this.hand);
            // System.out.println(holdList);

            // FOLLOW THE ADVICE
            this.hand.holdCards(holdList, this.deck.getCards(5 - holdList.size()));
            // System.out.println(this.hand);

            String result = analizer.getPayTableResult(this.hand);
            // System.out.println(result);

            // System.out.println("--------");

            int cashback;
            if (result.equals("O")) {
                // System.out.println("player loses and his credit is " +
                // this.player.getCredits() + "\n");
                cashback = 0;
            } else {
                // Player won something
                cashback = pay.getValue(result, bet);

                this.player.increaseCredit(cashback);
                // System.out.println(
                // "player wins with a " + result + " and his credit is " +
                // this.player.getCredits() + "\n");
            }
            stats.addStat(result, cashback, bet);
        }

        // System.out.println(var1+ " "+var2+ " "+var3);
        System.out.println(stats + "\n");
        // System.out.println(stats.counter);

    }

}
