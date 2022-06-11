package main;

import wip.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		// Player p = new Player(100, "João");
        // RegularDeck rd = new RegularDeck();
        // Stats s = new Stats(100);
        // CardAnalizer ca = new CardAnalizer();
        // for (int i = 0; i < 20; i++) {
        //     rd.shuffle();       
        //     String result = ca.getPayTableResult(new Hand(rd.getCards(5)));
        //     s.addStat(result);.
        // }
        // System.out.println(s);

        // RegularDeck rd = new RegularDeck();
        // rd.shuffle();

        // Hand h = new Hand(rd.getCards(5));

        // System.out.println("hand is " + h);
        

		Player p = new Player(10000, "João");
		System.out.println(p);
		
		Debug g = new Debug(p, args[0], args[1]);
		// g.showCmds();
        System.out.println("Starting!!!");
        g.play();
    }
}