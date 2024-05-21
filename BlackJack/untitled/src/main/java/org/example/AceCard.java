package org.example;

import org.example.interfaces.Card;

class AceCard implements Card {
    private static final int DEFAULT_VALUE = 11;
    private String name = "ace";
    private String suit;

    public AceCard(String suit) {
        this.suit = suit;
    }

    @Override
    public int getValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Ace of " + suit;
    }

    @Override
    public String getName() {
        return name;
    }
}
