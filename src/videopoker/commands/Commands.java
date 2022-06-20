package videopoker.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Commands implements ICommands{

    private ArrayList<Command> cmds = new ArrayList<Command>();
 
    /**
     * Saves the commands just like in the command file.
     * Indexes must be converted if necessary
     */
    public Commands(String cmd_file) throws FileNotFoundException {
        ArrayList<String> cmds_str = this.parseCmdFile(cmd_file);
        
        String current, next;

        while (!cmds_str.isEmpty()) {
            current = cmds_str.remove(0); //Get the first command in the list
            
            if (current.equals("b")) {                  
                //Check whats next to see the amount
                next = cmds_str.get(0); //Get doesnt remove from the list
                
                int amount;
                try {
                    amount = Integer.parseInt(next);    
                    //Dont forget to remove from the cmd list
                    cmds_str.remove(0);

                    ArrayList<Integer> args = new ArrayList<Integer>();
                    args.add(amount);
                    this.cmds.add( new Command("b", args ) );
                } catch (NumberFormatException e) {
                    //amount was not specified
                    this.cmds.add( new Command("b") );
                }
            } else if (current.equals("d")) {
                this.cmds.add(new Command("d"));
            } else if (current.equals("h")) {
                //Check whats next to find the cards to hold
                ArrayList<Integer> holdIdxs = new ArrayList<Integer>();
                while (true) {
                    try {
                        //just save the "index" as it is on the file
                        holdIdxs.add( Integer.parseInt( cmds_str.get(0) ) );
                        cmds_str.remove(0);
                    } catch (NumberFormatException e) {
                        break;
                        //Do nothing - not good ?????????
                    } catch (IndexOutOfBoundsException e) {
                        //no more cmds
                        break;
                    }
                }
                this.cmds.add( new Command("h", holdIdxs) );
            } else if ( current.equals("$") ) {
                this.cmds.add( new Command("$") );
            } else if ( current.equals("a") ) {
                this.cmds.add( new Command("a") );
            } else if ( current.equals("s") ) {
                this.cmds.add( new Command("s") );
            }
        }

    }

    public boolean isEmpty() {
        return this.cmds.isEmpty();
    }

    public Command getNextCommand() {
        if (!this.isEmpty()) {
            return this.cmds.remove(0);
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Commands c = new Commands(args[0]);
        Command current;
        // System.out.println(c);

        while (!c.isEmpty()) {
            current = c.getNextCommand();
            System.out.println(current);
            // System.out.println(current.getType());
            // System.out.println(current.getArgs().size());
        }

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Command c : this.cmds) {
            str.append( c.toString() );
            str.append("\n");
        }

        return str.toString();
    }

    /**
     * Function to parse the commands in the command file
     * May raise FileNotFoundException if the file doesent exist
     * Initializes the attribute cmds.
     * 
     * @param cmdFile path to the file with the commands
     * @return void
     * @throws FileNotFoundException
     */
    private ArrayList<String> parseCmdFile(String cmd_file) throws FileNotFoundException {
        String line = new String();
        ArrayList<String> cmds = new ArrayList<String>();

        File f = new File(cmd_file);
        Scanner reader = new Scanner(f);

        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line.equals("")) { //Skips blank lines
                continue;
            }

            for (String str: line.split(" ")) {
                cmds.add(str);
            }

        }
        reader.close();
        return cmds;
    }
    
}
