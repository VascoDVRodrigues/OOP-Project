package Main;
import WIP.RegularDeck;
import WIP.RiggedDeck;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
		
		// RiggedDeck d = new RiggedDeck("/home/andreps/Documents/POO/OOP-Project/files/card-file.txt");
		
		RegularDeck d = new RegularDeck();
		
		System.out.println(d);
		d.shuffle();	
		System.out.println(d);
	}
}
