package wip;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Debug extends Game {
    protected ArrayList<String> cmds;

    private int lastBet = -1;
    private boolean allowBet = true; 
    private boolean allowDeal = false;

    public Debug(Player p, String cardFile, String cmd_file) {
        this.deck = new RiggedDeck(cardFile);
        this.player = p;
        this.cmds = new ArrayList<String>();
        parseCmdFile(cmd_file);
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
                //First check if the player can bet
                if (!this.allowBet) {
                    System.out.println("b: illegal command");
                }
                
                //Check whats next to see the amount
                next = this.cmds.get(0); //Get doesnt remove from the list
                
                int amount;
                try {
                    amount = Integer.parseInt(next);

                    if (amount > maxBet) {
                        System.out.println("b: illegal amount");
                        continue;
                    }

                    System.out.println("-cmd b " + amount);
                } catch (NumberFormatException e) {
                    //amount was not specified
                    System.out.println("-cmd b");

                    if (lastBet!=-1) {
                        //then the last amount must be betted
                        amount = lastBet;
                    } else {
                        amount = defaultAmount;
                    }
                }

                this.player.bet(amount);
                allowBet = false;
                allowDeal = true;
            } else if (current.equals("d")) {
                if (!allowDeal) {
                    System.out.println("d: illegal command");
                }

                System.out.println("-cmd d");
                this.giveHand();
            } else if (current.equals("h")) {

                //Fetch the cards that the player wants to hold
                
                return;
            }
        }

    }

    @Override
    public void giveHand(){     
        this.player.setHand( new Hand(this.deck.getCards(5)) );
    }
}
