package wip;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Debug extends Game {
    protected ArrayList<String> cmds = new ArrayList<String>();

    private int lastBet = -1;
    private boolean allowBet = true; 
    private boolean allowDeal = false;

    private CardAnalizer analizer = new CardAnalizer();
    PayoutTable pay = new PayoutTable();

    public Debug(Player p, String cardFile, String cmd_file) {
        this.deck = new RiggedDeck(cardFile);
        this.player = p;
        parseCmdFile(cmd_file);
        System.out.println("Starting debug mode with variant\n" + pay);
    }

    /**
     * Function to parse the commands in the command file
     * May raise FileNotFoundException if the file doesent exist
     * Initializes the attribute cmds.
     * 
     * @param cmdFile path to the file with the commands
     * @return void
     */
    private void parseCmdFile(String cmd_file) {
        String line = new String();

        try {
            File f = new File(cmd_file);
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                line = reader.nextLine();

                if (line.equals("")) { //Skips blank lines
                    continue;
                }

                for (String str: line.split(" ")) {
                    cmds.add(str);
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + cmd_file + " doesn't exist :(");
        }
    }

    public void showCmds() {
        for (String cmd : this.cmds) {
            System.out.print(cmd);
        }
        System.out.println(" ");
    }

    public void play(){
        String current, next;
        
        while (!this.cmds.isEmpty()) {
            current = this.cmds.remove(0); //Get the first command in the list
            
            if (current.equals("b")) {
                System.out.print("-cmd b ");
                //First check if the player can bet
                if (!this.allowBet) {
                    System.out.println("\nb: illegal command");
                }
                
                //Check whats next to see the amount
                next = this.cmds.get(0); //Get doesnt remove from the list
                
                int amount;
                try {
                    amount = Integer.parseInt(next);

                    if (amount > maxBet) {
                        System.out.println(amount + "\nb: illegal amount\n");
                        continue;
                    }

                    System.out.println(amount);

                    //Dont forget to remove from the cmd list
                    this.cmds.remove(0);
                } catch (NumberFormatException e) {
                    //amount was not specified
                    System.out.print("\n");
                    if (lastBet!=-1) {
                        //then the last amount must be betted
                        amount = lastBet;
                    } else {
                        amount = defaultAmount;
                    }
                }

                this.player.bet(amount);
                lastBet = amount;
                allowBet = false;
                allowDeal = true;
            } else if (current.equals("d")) {
                if (!allowDeal) {
                    System.out.println("d: illegal command\n");
                    continue;
                }

                System.out.println("-cmd d");
                this.giveHand();
                System.out.print("\n");
            } else if (current.equals("h")) {
                //Fetch the cards that the player wants to hold
                ArrayList<Integer> holdIdxs = new ArrayList<Integer>();
                while (true) {
                    try {
                        //-1 because in the commands counting starts at 1
                        holdIdxs.add( Integer.parseInt( this.cmds.get(0) ) - 1 );
                        
                        this.cmds.remove(0);
                    } catch (NumberFormatException e) {
                        break;
                        //Do nothing - not good ?????????
                    } catch (IndexOutOfBoundsException e) {
                        //no more cmds
                        break;
                    }
                }
                System.out.print("-cmd h ");
                for (Integer i : holdIdxs) {
                    System.out.print( (i+1) + " ");
                }
                System.out.println(" ");

                this.hand.holdCards(holdIdxs, this.deck.getCards(5-holdIdxs.size()));
                
                this.player.displayHand();

                String result = analizer.getPayTableResult(this.hand);

                if (result.equals("O")) {
                    System.out.println("player loses and his credit is " + this.player.getCredits() + "\n");
                } else {
                    //Player won something
                    int cashBack = pay.getValue(result, lastBet);
                    this.player.increaseCredit(cashBack);
                    System.out.println( "player wins with a " + result + " and his credit is " + this.player.getCredits() + "\n" );
                }
                
                allowBet = true;
            } else if ( current.equals("$") ) {
                System.out.println("-cmd $");
                System.out.println("player's credit is " + this.player.getCredits() + "\n");
            } else if ( current.equals("a") ) {
                System.out.println("-cmd a");
                System.out.println("player should ");
                System.out.println("TODO: ADVICES\n");
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
