package vpf24.cphbusiness;

import vpf24.cphbusiness.interfaces.Card;

public class Player {
    private Hand hand;

    public Player() {
        hand = new Hand();
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public int getHandValue() {
        return hand.getValue();
    }

    public String displayHand() {
        return hand.display();
    }

    public void clearHand() {
        hand.clear();
    }

    public Hand getHand() {
        return hand;
    }
}
