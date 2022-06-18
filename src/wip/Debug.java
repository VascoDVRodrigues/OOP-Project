package wip;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import wip.commands.*;

public class Debug extends Game {
    protected Commands cmds;

    private int lastBet = -1;
    private boolean allowBet = true; 
    private boolean allowDeal = false;
    private boolean allowHold = false;
    private boolean allowAdvice = false;

    private CardAnalizer analizer = new CardAnalizer();
    private PayoutTable pay = new PayoutTable();
    private Stats stats;
    private Advisor advisor = new Advisor();

    public Debug(Player p, String cmd_file, String cardFile) throws FileNotFoundException {
        this.deck = new RiggedDeck(cardFile);
        this.player = p;
        this.cmds = new Commands(cmd_file);
        stats = new Stats(this.player.getCredits());
        System.out.println("Starting debug mode with variant\n" + pay);
    }

    public void play(){
        Command current;
        
        while (!cmds.isEmpty()) {
            current = this.cmds.getNextCommand();
            
            System.out.println(current);
            
            if (current.getType().equals("b")) {
                //First check if the player can bet
                if (!this.allowBet) {
                    System.out.println("b: illegal command\n");
                    continue;
                }
                
                //Check the amount
                int amount;
                if (current.getArgs().size()==1) {
                    amount = current.getArgs().get(0);
                } else if (current.getArgs().size()==0) {
                    //amount not specified
                    if (lastBet!=-1) {
                        //then the last amount must be betted
                        amount = lastBet;
                    } else {
                        amount = defaultAmount;
                    }
                } else {
                    System.out.println("b: illegal command\n");
                    continue;
                }

                if ( amount > maxBet ) {
                    System.out.println("b: illegal amount\n");
                    continue;
                }

                this.player.bet(amount);
                lastBet = amount;
                allowBet = false;
                allowDeal = true;
            } else if (current.getType().equals("d")) {
                if (!allowDeal) {
                    System.out.println("d: illegal command\n");
                    continue;
                }

                this.giveHand();
                System.out.print("\n");
                allowDeal = false;
                allowHold = true;
                allowAdvice = true;
            } else if (current.getType().equals("h")) {     
                if (!allowHold) {
                    System.out.println("h: illegal command\n");
                    continue;
                }           
                //Fetch the cards that the player wants to hold
                ArrayList<Integer> holdIdxs = current.getArgs();
                
                //Must convert all indexes to indexes starting at 0, in cmd file starts at 1
                for (int i = 0; i < holdIdxs.size(); i++) {
                    holdIdxs.set(i, holdIdxs.get(i) - 1 );
                }

                //Hold those cards, and replaced the dropped ones
                this.hand.holdCards(holdIdxs, this.deck.getCards(5-holdIdxs.size()));
                
                this.player.displayHand();

                String result = analizer.getPayTableResult(this.hand);
                
                int cashback;

                if (result.equals("O")) {
                    System.out.println("player loses and his credit is " + this.player.getCredits() + "\n");
                    cashback = 0;
                } else {
                    //Player won something
                    cashback = pay.getValue(result, lastBet);
                    this.player.increaseCredit( cashback );
                    System.out.println( "player wins with a " + result + " and his credit is " + this.player.getCredits() + "\n" );
                }
                
                stats.addStat(result, cashback, lastBet);
                
                allowBet = true;
                allowHold = false;
                allowAdvice = false;
            } else if ( current.getType().equals("$") ) {
                System.out.println("player's credit is " + this.player.getCredits() + "\n");
            } else if ( current.getType().equals("a") ) {
                if (!allowAdvice) {
                    System.out.println("a: illegal command");
                    continue;
                }

                ArrayList<Integer> holdList = advisor.getHoldList(this.analizer.getAdviceFromTable(this.hand), this.hand);

                if (holdList.size() == 0) {
                    System.out.println("player should discard everything");
                } else if (holdList.size() == 5) {
                    System.out.println("player should hold everything");
                } else {
                    StringBuilder str = new StringBuilder();
                    for (Integer i : holdList) {
                        str.append(i);
                        str.append(" ");
                    }

                    System.out.print("player should hold cards " + str.toString());
                }
            } else if ( current.getType().equals("s") ) {
                System.out.println(stats+"\n");
            }
        }

    }

    @Override
    public void giveHand(){  
        //Both the player and the game have acess to the same hand
        this.hand = new Hand(this.deck.getCards(5));
        this.player.setHand( this.hand );
    }
}
