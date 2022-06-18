package wip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiggedDeck extends Deck{

    public RiggedDeck(String filename) throws FileNotFoundException{
        this.cardList = new ArrayList<Card>();
    
        String[] splitedLine;
        String line = new String();

        File file = new File(filename);
        Scanner myReader = new Scanner(file);

        while (myReader.hasNextLine()) {
            line = myReader.nextLine();

            if (line.equals("")) { //Skips blank lines
                continue;
            }

            splitedLine = line.split(" ");

            for (int i = 0; i < splitedLine.length; i++) {
                if (splitedLine[i].length() != 2) {
                    continue;
                }
                if ( Character.isDigit( splitedLine[i].charAt(0) ) ||
                    (splitedLine[i].charAt(0) == 'T') || 
                    (splitedLine[i].charAt(0) == 'Q') || 
                    (splitedLine[i].charAt(0) == 'J') || 
                    (splitedLine[i].charAt(0) == 'A') || 
                    (splitedLine[i].charAt(0) == 'K') )  {

                        if (splitedLine[i].charAt(1) == 'H' || 
                            splitedLine[i].charAt(1) == 'D' || 
                            splitedLine[i].charAt(1) == 'S' || 
                            splitedLine[i].charAt(1) == 'C') {
                                this.cardList.add( new Card(splitedLine[i]) ); 
                        } else {
                            // System.out.println("Invalid Nape character, ignored");
                        }

                } else {
                    // System.out.println("Invalid Card number, ignored");
                }

                
            }
        }
        myReader.close();
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();

        //Remove n cards from cardslist
        for (int i = 0; i < n; i++) {
            aux.add( cardList.remove(0) );
        }
        
        return aux;
    }
}
