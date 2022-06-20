package videopoker;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import videopoker.commands.*;
import videopoker.deck.RiggedDeck;
import videopoker.exceptions.*;
import videopoker.helpers.*;

/**
 * This class is used to implement the Debug mode, it extends the abstract class
 * Game.
 * 
 * {@inheritDoc}
 * 
 * @see videopoker.Game
 */
public class Debug extends Game {
    protected Commands cmds;

    // Some private attributes to keep track of what commands can be used
    private int lastBet = -1;
    private boolean allowBet = true;
    private boolean allowDeal = false;
    private boolean allowHold = false;
    private boolean allowAdvice = false;

    // Attributes for getting the advices and the result of the hands
    private CardAnalizer analizer = new CardAnalizer();
    private PayoutTable pay = new PayoutTable();
    private Stats stats;
    private Advisor advisor = new Advisor();

    /**
     * This is the constructor for the Debug class.
     * <p>
     * It takes a Player object and String objects with the command and card files.
     * </p>
     * 
     * @param p         Player object with the player
     * @param cmd_file  String with the path to the command file
     * @param cardFilee String with the path to the card file
     * @see videopoker.deck.Card
     */
    public Debug(Player p, String cmd_file, String cardFile) throws FileNotFoundException {
        this.deck = new RiggedDeck(cardFile);
        this.player = p;
        this.cmds = new Commands(cmd_file);
        stats = new Stats(this.player.getCredits());
        // System.out.println("Starting debug mode with variant\n" + pay);
    }

    /**
     * Helper method to do the bet command.
     * <p>
     * It takes the current object Command.
     * </p>
     * 
     * @param current Current command object
     * @throws IllegalCommandException If the command is used illegally
     * @throws IllegalAmountException  If the amount of the bet is illegal
     * @see videopoker.commands.Command
     * @see videopoker.exceptions
     */
    private void bet(Command current) throws IllegalCommandException, IllegalAmountException {
        // First check if the player can bet
        if (!this.allowBet) {
            throw new IllegalCommandException("b");
        }

        // Check the amount
        int amount;
        if (current.getArgs().size() == 1) {
            amount = current.getArgs().get(0);
        } else if (current.getArgs().size() == 0) {
            // amount not specified
            if (lastBet != -1) {
                // then the last amount must be betted
                amount = lastBet;
            } else {
                amount = defaultAmount;
            }
        } else {
            throw new IllegalCommandException("b");
        }

        // amount must be between 0 and maxBet
        if (amount > maxBet || amount <= 0) {
            throw new IllegalAmountException();
        }

        this.player.bet(amount);
        System.out.println("player is betting " + amount + "\n");

        lastBet = amount;

        // Update the flags to keep track of allowed commands
        allowBet = false;
        allowDeal = true;
        allowAdvice = false;
    }

    /**
     * Helper method to do the deal command.
     * <p>
     * It takes the current object Command.
     * The function checks if the command is allowed, and if it is, it deals the
     * player a hand
     * </p>
     * 
     * @param current Current command object
     * @throws IllegalCommandException If the command is used illegally
     * @see videopoker.commands.Command
     * @see videopoker.exceptions
     */
    private void deal(Command current) throws IllegalCommandException {
        if (!allowDeal) {
            throw new IllegalCommandException("d");
        }

        // Check if there are enough cards
        if (((RiggedDeck) this.deck).getNumberOfCards() < 5) {
            throw new IllegalCommandException("d: not enough cards");
        }
        // count++;
        this.giveHand();
        // System.out.println(count + ".");
        this.player.displayHand();
        System.out.print("\n");

        // Update the flags to keep track of allowed commands
        allowDeal = false;
        allowHold = true;
        allowAdvice = true;
    }

    /**
     * Helper method to do the deal command.
     * <p>
     * The hold function allows the player to hold certain cards in his hand.
     * The function takes a list of indexes as arguments, and replaces the dropped
     * cards with new ones from the deck.
     * <\p>
     *
     * @param current Current command object
     * @see videopoker.commands.Command
     *
     */
    private void hold(Command current) throws IllegalCommandException {
        if (!allowHold) {
            throw new IllegalCommandException("h");
        }
        // Fetch the cards that the player wants to hold
        ArrayList<Integer> holdIdxs = current.getArgs();

        // Must convert all indexes to indexes starting at 0, in cmd file starts at 1
        int idx;
        boolean illegal = false;
        for (int i = 0; i < holdIdxs.size(); i++) {
            idx = holdIdxs.get(i) - 1;

            if (idx >= 5) { // Invalid index, hands have maximum of 5 cards
                illegal = true;
                break;
            } else {
                holdIdxs.set(i, idx);
            }
        }

        if (illegal) {
            throw new IllegalCommandException("h");
        }

        // Check if there are enough cards in the deck
        if (((RiggedDeck) this.deck).getNumberOfCards() < (5 - holdIdxs.size())) {
            throw new IllegalCommandException("h (not enough cards)");
        }

        // Hold those cards, and replaced the dropped ones
        this.hand.holdCards(holdIdxs, this.deck.getCards(5 - holdIdxs.size()));

        this.player.displayHand();

        // Use the analizer to check the result
        String result = analizer.getPayTableResult(this.hand);

        int cashback;

        if (result.equals("O")) {
            System.out.println("player loses and his credit is " + this.player.getCredits() + "\n");
            cashback = 0;
        } else {
            // Player won something
            cashback = pay.getValue(result, lastBet);
            this.player.increaseCredit(cashback);
            System.out.println(
                    "player wins with a " + result + " and his credit is " + this.player.getCredits() + "\n");
        }

        // Add the result to the stats object
        stats.addStat(result, cashback, lastBet);

        allowBet = true;
        allowHold = false;
        allowAdvice = false;
    }

    /**
     * Helper method to do the deal command.
     * <p>
     * The function takes the current command and checks if the advice command is
     * allowed. If it is, it
     * gets the advice from the table and then gets the hold list from the advisor.
     * If the hold list is
     * empty, the player should discard everything. If the hold list is 5, the
     * player should hold
     * everything. Otherwise, the player should hold the cards in the hold list
     * <\p>
     * 
     * @param current the current command
     * @throws IllegalCommandException if the command is used illegally
     */
    private void advice(Command current) throws IllegalCommandException {
        if (!allowAdvice) {
            throw new IllegalCommandException("a");
        }
        String condition = this.analizer.getAdviceFromTable(this.hand);
        System.out.println(condition);

        ArrayList<Integer> holdList = advisor.getHoldList(condition, this.hand);

        if (holdList.size() == 0) {
            System.out.println("player should discard everything\n");
        } else if (holdList.size() == 5) {
            System.out.println("player should hold everything\n");
        } else {
            StringBuilder str = new StringBuilder();
            for (Integer i : holdList) {
                str.append(i + 1); // +1 because advisor returns the true index (starts counting at 0)
                str.append(" ");
            }

            System.out.println("player should hold cards " + str.toString() + "\n");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Just goes through the commands and executes them, ignoring invalid ones. 
     */
    public void play() {
        Command current;
        while (!cmds.isEmpty()) {
            current = this.cmds.getNextCommand();

            System.out.println(current);

            if (current.getType().equals("b")) {
                try {
                    this.bet(current);
                } catch (IllegalCommandException | IllegalAmountException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else if (current.getType().equals("d")) {
                try {
                    this.deal(current);
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else if (current.getType().equals("h")) {
                try {
                    hold(current);
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else if (current.getType().equals("$")) {
                System.out.println("player's credit is " + this.player.getCredits() + "\n");
            } else if (current.getType().equals("a")) {
                try {
                    advice(current);
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else if (current.getType().equals("s")) {
                System.out.println(stats + "\n");
            }
        }
    }
}
