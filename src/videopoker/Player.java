package videopoker;

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

    /**
     * @return the credits
     */
    public int getCredits() {
        return this.credits;
    }

    public void increaseCredit(int amount) {
        this.credits += amount;
    }

    public void bet(int amount) {
        this.credits -= amount;
        // if (this.credits < 0) {
        //     // this.credits += amount;
        //     System.out.println("Player doesnt have enough credits :(");
        // }
    }

    @Override
    public String toString() {
        return "Player " + this.name + " has " + this.credits + " credits";
    }

    public void displayHand() {
        System.out.println("player's hand " + this.hand);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        // this.displayHand();
    }
}
