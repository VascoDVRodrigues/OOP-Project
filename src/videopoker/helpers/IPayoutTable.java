package videopoker.helpers;

public interface IPayoutTable {
    public void modifyValue(String key, int bet, int newValue);

    public int getValue(String key, int bet);

    public void addPayout(String name, String key, int[] amounts);

    public void removeBet(int bet);
}
