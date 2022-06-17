package wip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import wip.commands.*;

public class Debug extends Game {
    protected Commands cmds;

    private int lastBet = -1;
    private boolean allowBet = true; 
    private boolean allowDeal = false;

    private CardAnalizer analizer = new CardAnalizer();
    private PayoutTable pay = new PayoutTable();

    public Debug(Player p, String cmd_file, String cardFile) throws FileNotFoundException {
        this.deck = new RiggedDeck(cardFile);
        this.player = p;
        this.cmds = new Commands(cmd_file);
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
            } else if (current.getType().equals("h")) {                
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

                if (result.equals("O")) {
                    System.out.println("player loses and his credit is " + this.player.getCredits() + "\n");
                } else {
                    //Player won something
                    this.player.increaseCredit( pay.getValue(result, lastBet) );
                    System.out.println( "player wins with a " + result + " and his credit is " + this.player.getCredits() + "\n" );
                }
                
                allowBet = true;
            } else if ( current.getType().equals("$") ) {
                System.out.println("player's credit is " + this.player.getCredits() + "\n");
            } else if ( current.getType().equals("a") ) {
                System.out.print("player should ");
                System.out.println(this.analizer.getAdviceFromTable(this.hand));
                System.out.println(" ");
                // System.out.println("TODO: ADVICES\n");
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
