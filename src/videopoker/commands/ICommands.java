package videopoker.commands;

public interface ICommands {
    public Command getNextCommand();

    public boolean isEmpty();
}
