package videopoker.helpers;

public class StatCombination {
    protected String name;
    protected int absolutValue;
    protected String key;

    StatCombination(String s, String k){
        this.name = s;
        this.absolutValue = 0;
        this.key = k;
    }
    public void incrementValue(){
        absolutValue++;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-16s %d\n", name, absolutValue));
        // str.append(name);
        // str.append("\t");
        // str.append(absolutValue);
        // str.append("\n");

        return str.toString();
    }
}
