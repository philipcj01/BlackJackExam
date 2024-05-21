package vpf24.cphbusiness;

import vpf24.cphbusiness.interfaces.Card;

class NumberCard implements Card {
    private int value;
    private String suit;

    public NumberCard(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public String getName() {
        return String.valueOf(value);
    }
}
