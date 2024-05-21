package vpf24.cphbusiness;

import vpf24.cphbusiness.interfaces.Card;

class FaceCard implements Card {
    private String name;
    private int value;
    private String suit;

    public FaceCard(String name, int value, String suit) {
        this.name = name;
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
        return name + " of " + suit;
    }

    @Override
    public String getName() {
        return name;
    }
}
