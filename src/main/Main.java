package main;

import WIP.Player;
import WIP.CardAnalizer;
import WIP.RegularDeck;
import WIP.Stats;
import WIP.Hand;
public class Main {
	public static void main(String[] args) {
		Player p = new Player(100, "João");
        RegularDeck rd = new RegularDeck();
        rd.shuffle();
        System.out.println(rd);
        Hand h = new Hand(rd.getCards(5));
        System.out.println(h);
        CardAnalizer ca = new CardAnalizer(h);
        System.out.println(ca.evaluateHand());
		p.bet();
        Stats s = new Stats(100);
        s.addStat("k");
        s.addStat("k");
        s.addStat("k");
        s.addStat("TOK");
        System.out.println(s);

		// RiggedDeck d = new RiggedDeck("/home/andreps/Documents/POO/OOP-Project/files/card-file.txt");
		
		// RegularDeck d = new RegularDeck();
		
		// System.out.println(d);
		// d.shuffle();	
		// System.out.println(d);
	}
}
