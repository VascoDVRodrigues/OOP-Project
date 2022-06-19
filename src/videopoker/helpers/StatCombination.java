package videopoker.helpers;

/**
 * This class is the base class for the Stats class. It contains the name of a
 * hand type, as well as it short name and the number of occurrences of the hand
 * type.
 */
public class StatCombination {
    protected String name;
    protected int absolutValue;
    protected String key;

    /**
     * Constructs an instance of the StatCombination class with the given name, key,
     * and absolute value equal to 0.
     * 
     * @param name         The name of the hand type.
     * @param key          The short name of the hand type.
     * @param absolutValue The number of occurrences of the hand type.
     */
    StatCombination(String s, String k) {
        this.name = s;
        this.absolutValue = 0;
        this.key = k;
    }

    /**
     * Increments the absolute value of the hand type by 1.
     */
    public void incrementValue() {
        absolutValue++;
    }

    /**
     * 
     * @return The name of the hand type as well as it number of occurences.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-16s %d %f\n", name, absolutValue, (float) absolutValue / (float) 10000000));

        return str.toString();
    }
}
