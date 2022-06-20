package videopoker;

public interface IPlayer {
    public void increaseCredit(int amount);

    public void bet(int amount);

    public void setHand(Hand hand);
}
