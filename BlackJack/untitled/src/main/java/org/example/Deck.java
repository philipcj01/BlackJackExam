package org.example;

import org.example.interfaces.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        for (String suit : suits) {
            for (int i = 2; i <= 10; i++) {
                cards.add(new NumberCard(i, suit));
            }
            cards.add(new FaceCard("Jack", 10, suit));
            cards.add(new FaceCard("Queen", 10, suit));
            cards.add(new FaceCard("King", 10, suit));
            cards.add(new AceCard(suit));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }
}
