package wip;


import java.util.ArrayList;

public class Debug extends Game {
    protected Player player;
    
    public Debug(Player p, String cardFile) {
        deck = new RiggedDeck(cardFile);
        player = p;
    }

    public void bet(int amount){
        System.out.println("Player betted");
        
        this.player.bet(amount);
    }

    @Override
    public void giveHand(){        
        ArrayList<Card> hand = this.deck.getCards(5);
        this.player.setHand(hand);
    }
}
