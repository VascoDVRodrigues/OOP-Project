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
        Stats s = new Stats(100);
        CardAnalizer ca = new CardAnalizer();
        for (int i = 0; i < 20; i++) {
            rd.shuffle();       
            String result = ca.getPayTableResult(new Hand(rd.getCards(5)));
            s.addStat(result);
        }
        System.out.println(s);

		// RiggedDeck d = new RiggedDeck("/home/andreps/Documents/POO/OOP-Project/files/card-file.txt");
		
		// RegularDeck d = new RegularDeck();
		
		// System.out.println(d);
		// d.shuffle();	
		// System.out.println(d);
	}
}
