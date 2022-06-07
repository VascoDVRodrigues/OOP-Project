package wip;

import java.util.ArrayList;

public class Player {
    protected int credits;
    public String name;
    protected ArrayList<Card> hand;

    public Player(int c, String name) {
        this.credits = c;
        this.name = name;
    }

    public Player(int c) {
        this.credits = c;
    }

    public void bet(int amount) {
        this.credits -= amount;
    }
    //default bet value
    public void bet(){
        this.credits -= 5;
    }

    @Override
    public String toString() {
        return "Player " + this.name + " has " + this.credits + " credits";
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;

    }
}
