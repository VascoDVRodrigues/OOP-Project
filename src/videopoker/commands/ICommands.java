package videopoker.commands;

public interface ICommands {
    /**
     * If the command list is not empty, remove and return the first command in the list.
     * 
     * @return The first command in the list.
     */
    public Command getNextCommand();

    /**
     * Returns true if the list of commands is empty.
     * 
     * @return The boolean value of the isEmpty() method of the cmds ArrayList.
     */
    public boolean isEmpty();
}
