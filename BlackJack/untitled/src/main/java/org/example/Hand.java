package org.example;

import org.example.interfaces.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        int aces = 0;
        for (Card card : cards) {
            int cardValue = card.getValue();
            if (cardValue == 11) { // Assuming Ace is valued at 11 initially
                aces++;
            }
            value += cardValue;
        }

        // Adjust for Aces if value goes over 21
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.getName()).append(" ");
        }
        return sb.toString().trim();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }
}
