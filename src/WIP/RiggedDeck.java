package WIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiggedDeck extends Deck{

    public RiggedDeck(String filename){
        String[] cards;
        this.length = 0;
    
        String cardOrder = new String();

        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            
            //ASSUME QUE O FICHEIRO TEM SO UMA LINHA
            while (myReader.hasNextLine()) {
                cardOrder = myReader.nextLine();
            }
            myReader.close();

            cards = cardOrder.split(" ");
            this.length = cards.length;

            //Converter as cartas lidas para objetos carta
            cardlist = new Card[this.length];
            for (int i = 0; i < this.length; i++) {
                cardlist[i] = new Card(cards[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " doesn't exist :(");
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
        // for (int i = 0; i < n; i++) {
        //     //Procurar a carta cards[i] no array cardlist e coloca-la no array list a retornar
        //     for (Card c : this.cardlist) {
        //         if (c.equals(cards[i])){
        //             aux.add(c);
        //         }
        //     }
        // }
        return aux;
    }
}
