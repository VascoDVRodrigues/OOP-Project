package main;

import WIP.Debug;
import WIP.Player;

public class Main {
	public static void main(String[] args) {
		Player p = new Player(100, "João");
		System.out.println(p);
		Debug g = new Debug(p, args[0]);



		p.bet();
		g.dhsdgyuf()
		g.pay()
		g.printDeck();

		// RiggedDeck d = new RiggedDeck("/home/andreps/Documents/POO/OOP-Project/files/card-file.txt");
		
		// RegularDeck d = new RegularDeck();
		
		// System.out.println(d);
		// d.shuffle();	
		// System.out.println(d);
	}
}
