package wip;

import java.util.ArrayList;

public class Player {
    protected int credits;
    public String name;
    protected Hand hand;

    public Player(int c, String name) {
        this.credits = c;
        this.name = name;
    }

    public Player(int c) {
        this.credits = c;
    }

    public void bet(int amount) {
        this.credits -= amount;
        if (this.credits < 0) {
            this.credits += amount;
            System.out.println("Player doesnt have enough credits :( (E AGORA?)");
        }
        System.out.println("player is betting " + amount + "\n");
    }
    
    @Override
    public String toString() {
        return "Player " + this.name + " has " + this.credits + " credits";
    }

    public void setHand(Hand hand){
        this.hand = hand;
        System.out.println("player's hand " + this.hand + "\n");
    }
}
