package org.example;

import org.example.interfaces.Card;

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
