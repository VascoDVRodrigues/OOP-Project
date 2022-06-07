package wip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Variant {
    private static final String FORMAT = "%-16s %10s %10s %10s %10s %10s%n";
    private static final String BLUE = "\u001B[38;2;0;157;224m";
    private static final String RESET = "\u001B[0;0m";

    protected String name;
    protected int[] oneBet;
    protected int[] twoBet;
    protected int[] threeBet;
    protected int[] fourBet;
    protected int[] fiveBet;

    /**
     * Default constructor for the class Variant, it initializes the name of the
     * variant and the payouts for each hand
     * acording to the Double Bonus 10/7 game variant.
     * It stores in each array the payouts for each hand, according to the ammount
     * of money the player has bet.
     */
    public Variant() {
        this.name = "Double Bonus 10/7";
        this.oneBet = new int[] { 250, 50, 160, 80, 50, 10, 7, 5, 3, 1, 1 };
        this.twoBet = new int[] { 500, 100, 320, 160, 100, 20, 14, 10, 6, 2, 2 };
        this.threeBet = new int[] { 750, 150, 480, 240, 150, 30, 21, 15, 9, 3, 3 };
        this.fourBet = new int[] { 1000, 200, 640, 320, 200, 40, 28, 20, 12, 4, 4 };
        this.fiveBet = new int[] { 4000, 250, 800, 400, 250, 50, 36, 25, 15, 5, 5 };
    }

    /**
     * Constructor for the class Variant, it initializes the name of the variant and
     * the payouts for each hand
     * with the values obtained from the input file passed as an argument.
     * The values are stored in seperate arrays according to the ammount of money
     * the player has bet.
     * 
     * @param name     Name of the game variant
     * @param filepath Path to the input file
     */
    public Variant(String name, String filepath) {
        this.name = name;
        int[][] values = fileLoad(filepath);
        this.oneBet = values[0];
        this.twoBet = values[1];
        this.threeBet = values[2];
        this.fourBet = values[3];
        this.fiveBet = values[4];

    }

    /**
     * Constructor for the class Variant, it initializes the name of the variant and
     * the payouts for each hand
     * with the values from the array passed as an argument.
     * The values are stored in seperate arrays according to the ammount of money
     * the player has bet.
     * 
     * @param name   Name of the game variant
     * @param values 2D Array with the values of the payouts for each hand
     */
    public Variant(String name, int[][] values) {
        this.name = name;
        this.oneBet = values[0];
        this.twoBet = values[1];
        this.threeBet = values[2];
        this.fourBet = values[3];
        this.fiveBet = values[4];
    }

    /**
     * This function evaluates based on the betted amount the amount of money that
     * the player wins with his current hand.
     * For the four of a kind hand, the amount of credits won depend on the cards
     * that compose the four of a kind, requiring
     * modefier value.
     * 
     * @param bet  Amount of credits betted
     * @param hand Hand to be searched
     * @param mod  Modifier value for four of a kind hand
     * @return Amount of credits won
     */
    public int gains(int bet, int hand, int mod) {

        if (hand == 3) {
            hand = hand + mod;
        }
        switch (bet) {
            case 1:
                return oneBet[hand];
            case 2:
                return twoBet[hand];
            case 3:
                return threeBet[hand];
            case 4:
                return fourBet[hand];
            case 5:
                return fiveBet[hand];
            default:
                return 0;
        }
    }

    /**
     * Utility function to load the payouts from a file.
     * 
     * @param filepath Path to the input file
     * @return 2D Array with the payouts for each hand
     */
    private int[][] fileLoad(String filepath) {
        int[][] values = new int[5][11];
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 11; j++) {

                    values[i][j] = myReader.nextInt();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return values;
    }

    @Override
    public String toString() {
        String result = "";
        result = result + BLUE + String.format(
                "─────────────────────────────────────────────────────────────────────────%n") + RESET;
        result = result + String.format(FORMAT, this.name, "1 Credit", "2 Credits", "3 Credits",
                "4 Credits", "5 Credits");
        result = result + BLUE + String.format(
                "─────────────────────────────────────────────────────────────────────────%n") + RESET;
        result = result + String.format(FORMAT, "Royal Flush", this.oneBet[0], this.twoBet[0], this.threeBet[0],
                this.fourBet[0], this.fiveBet[0]);
        result = result + String.format(FORMAT, "Straight Flush", this.oneBet[1], this.twoBet[1], this.threeBet[1],
                this.fourBet[1], this.fiveBet[1]);
        result = result + String.format(FORMAT, "Four Aces", this.oneBet[2], this.twoBet[2], this.threeBet[2],
                this.fourBet[2], this.fiveBet[2]);
        result = result + String.format(FORMAT, "Four 2-4", this.oneBet[3], this.twoBet[3], this.threeBet[3],
                this.fourBet[3], this.fiveBet[3]);
        result = result + String.format(FORMAT, "Four 5-K", this.oneBet[4], this.twoBet[4], this.threeBet[4],
                this.fourBet[4], this.fiveBet[4]);
        result = result + String.format(FORMAT, "Full House", this.oneBet[5], this.twoBet[5], this.threeBet[5],
                this.fourBet[5], this.fiveBet[5]);
        result = result + String.format(FORMAT, "Flush", this.oneBet[6], this.twoBet[6], this.threeBet[6],
                this.fourBet[6], this.fiveBet[6]);
        result = result + String.format(FORMAT, "Straight", this.oneBet[7], this.twoBet[7], this.threeBet[7],
                this.fourBet[7], this.fiveBet[7]);
        result = result + String.format(FORMAT, "Three of a Kind", this.oneBet[8], this.twoBet[8], this.threeBet[8],
                this.fourBet[8], this.fiveBet[8]);
        result = result + String.format(FORMAT, "Two Pair", this.oneBet[9], this.twoBet[9], this.threeBet[9],
                this.fourBet[9], this.fiveBet[9]);
        result = result + String.format(FORMAT, "Jacks or Better", this.oneBet[10], this.twoBet[10], this.threeBet[10],
                this.fourBet[10], this.fiveBet[10]);
        result = result + BLUE + String.format(
                "─────────────────────────────────────────────────────────────────────────%n") + RESET;
        return result;

    }

    public static void main(String[] args) {

        Variant v = new Variant();
        System.out.println(v);
    }

}
