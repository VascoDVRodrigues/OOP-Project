package wip.commands;

import java.util.ArrayList;

public class Command {
    private String type;
    private ArrayList<Integer> args;

    /**
     * 
     */
    public Command(String type, ArrayList<Integer> args) {
        this.type = type;
        this.args = args;
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

    /**
     * 
     */
    public Command(String type) {
        this.type = type;
        this.args = new ArrayList<>();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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
