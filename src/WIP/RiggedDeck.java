package WIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiggedDeck extends Deck{

    public RiggedDeck(String filename){
        this.length = 0;
        this.cardlist = new ArrayList<Card>();
    
        String[] splitedLine;
        String line = new String();

        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);

            //ASSUME QUE O FICHEIRO TEM SO UMA LINHA 
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();

                if (line.equals("")) { //Skips blank lines
                    continue;
                }

                splitedLine = line.split(" ");

                for (int i = 0; i < splitedLine.length; i++) {
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
                                    this.cardlist.add( new Card(splitedLine[i]) ); 
                            } else {
                                System.out.println("Invalid Nape character, ignored");
                            }

                    } else {
                        System.out.println("Invalid Card number, ignored");
                    }

                    
                }
            }
            myReader.close();

            this.length = this.cardlist.size();            
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " doesn't exist :(");
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> aux = new ArrayList<Card>();
    

        for (int i = 0; i < n; i++) {
            //aux.append(this.cardList);
        }
        
        return aux;
    }
}
