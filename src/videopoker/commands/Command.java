package videopoker.commands;

import java.util.ArrayList;

/**
 * Class to implement a command
 */
public class Command {
    private String type;
    private ArrayList<Integer> args;

    /**
     * This class is used to implement a command.
     * <p>
     * Receives a string with the type of command and an ArrayList of integers representing the command's arguments
     * @param type  The type of command
     * @param args  The arguments of the command
     * </p>
     */
    public Command(String type, ArrayList<Integer> args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Overloaded constructor for the Command class
     * Usefull if the command has no arguments
     * 
     * @param type  The type of command 
     */
    public Command(String type) {
        this.type = type;
        this.args = new ArrayList<>();
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the args
     */
    public ArrayList<Integer> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        if (this.args.size() > 0) {
            StringBuilder str = new StringBuilder();
            str.append("-cmd " + this.type + " ");
            for (Integer i : this.args) {
                str.append( i );
                str.append(" ");
            }
            // str.append("\n");
            return  str.toString();
        } else {
            return "-cmd " + this.type;
        }
    }
}
