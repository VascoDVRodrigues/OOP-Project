package main;

import java.io.FileNotFoundException;

import videopoker.*;
import videopoker.exceptions.*;

public class Main {
    public static void main(String[] args) {
        Player p;
        // Parse input parameters
        if (args[0].equals("-d")) { // debug mode
            if (args.length != 4) {
                System.out.println("Invalid number of arguments");
            }

            try {
                p = new Player(Integer.parseInt(args[1]), "André");
                // System.out.println(p);
            } catch (NumberFormatException e) {
                System.out.println(
                        "First argument is number of credits, must be integer, " + args[0] + " is not an integer.. :(");
                System.out.println(e);
                return;
            } catch (InvalidPlayer e) {
                System.out.println(e.getMessage());
                return;
            }

            try {
                Debug g = new Debug(p, args[2], args[3]);

                g.play();
            } catch (FileNotFoundException e) {
                System.out.println("File doesn't exist :(");
                System.out.println(e);
            }
        } else if (args[0].equals("-s")) { // Simulation mode
            if (args.length != 4) {
                System.out.println("Invalid number of arguments");
            }

            try {
                p = new Player(Integer.parseInt(args[1]), "André");
                // System.out.println(p);
            } catch (NumberFormatException e) {
                System.out.println(
                        "First argument is number of credits, must be integer, " + args[0] + " is not an integer.. :(");
                System.out.println(e);
                return;
            } catch (InvalidPlayer e) {
                System.out.println(e.getMessage());
                return;
            }

            int bet;
            try {
                bet = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Second argument is bet amount, must be integer, " + args[0] + " is not an integer.. :(");
                System.out.println(e);
                return;
            }

            int nbdeals;
            try {
                nbdeals = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Third argument is number of deals, must be integer, " + args[0] + " is not an integer.. :(");
                System.out.println(e);
                return;
            }

            Simulation g;
            try {
                g = new Simulation(p, bet, nbdeals);
                g.play();
            } catch (InvalidSimulationMode e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Invalid option " + args[0]);
        }
    }
}

// Player p = new Player(100, "João");
// RegularDeck rd = new RegularDeck();
// Stats s = new Stats(100);
// CardAnalizer ca = new CardAnalizer();
// for (int i = 0; i < 20; i++) {
// rd.shuffle();
// String result = ca.getPayTableResult(new Hand(rd.getCards(5)));
// s.addStat(result);.
// }
// System.out.println(s);

// RegularDeck rd = new RegularDeck();
// rd.shuffle();

// Hand h = new Hand(rd.getCards(5));

// System.out.println("hand is " + h);