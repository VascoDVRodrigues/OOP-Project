package wip;

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
        str.append(name);
        str.append(" ");
        str.append(absolutValue);
        str.append("\n");

        return str.toString();
    }
}
