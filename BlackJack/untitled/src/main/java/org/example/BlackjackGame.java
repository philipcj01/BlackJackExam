package org.example;

import org.example.interfaces.BlackJackInterface;
import org.example.interfaces.Card;
import org.example.interfaces.GameUI;

import java.util.List;

public class BlackjackGame implements BlackJackInterface {
    public Player player;
    private final Deck deck;
    public Player dealer;
    private GameUI gameUI;

    public BlackjackGame(GameUI gameUI) {
        this.gameUI = gameUI;
        deck = new Deck();
        player = new Player();
        dealer = new Player();
        deck.shuffle();
    }

    @Override
    public void startGame() {
        deck.shuffle();
        player.clearHand();
        dealer.clearHand();
        dealInitialCards();
        updateUI();
    }

    private void dealInitialCards() {
        player.addCardToHand(deck.drawCard());
        dealer.addCardToHand(deck.drawCard());
        player.addCardToHand(deck.drawCard());
        dealer.addCardToHand(deck.drawCard());
    }

    @Override
    public void playerHits() {
        player.addCardToHand(deck.drawCard());
        updateUI();
    }

    @Override
    public void playerStands() {
        dealerTurn();
        updateUI();
        gameUI.displayMessage(determineWinner());
    }

    private void dealerTurn() {
        while (dealer.getHandValue() < 17) {
            dealer.addCardToHand(deck.drawCard());
        }
    }

    private void updateUI() {
        gameUI.updatePlayerHand(getPlayerHand());
        gameUI.updateDealerHand(getDealerHand());
        gameUI.updatePlayerTotal(player.getHandValue());
        gameUI.updateDealerTotal(dealer.getHandValue());
    }

    @Override
    public String getPlayerHand() {
        return player.displayHand();
    }

    @Override
    public String getDealerHand() {
        return dealer.displayHand();
    }

    @Override
    public String getGameState() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player's Hand:\n").append(player.displayHand())
                .append("\nDealer's Hand:\n").append(dealer.displayHand())
                .append("\n");
        return sb.toString();
    }

    @Override
    public String determineWinner() {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        if (playerValue > 21) {
            return "Player busts. Dealer wins!";
        } else if (dealerValue > 21) {
            return "Dealer busts. Player wins!";
        } else if (playerValue == dealerValue) {
            return "It's a push! No one wins.";
        } else if (playerValue > dealerValue) {
            return "Player wins!";
        } else {
            return "Dealer wins!";
        }
    }

    @Override
    public String getCardName(String playerType) {
        StringBuilder cardName = new StringBuilder();

        List<Card> playerCards;
        if (playerType.equalsIgnoreCase("player")) {
            playerCards = player.getHand().getCards();
        } else if (playerType.equalsIgnoreCase("dealer")) {
            playerCards = dealer.getHand().getCards();
        } else {
            throw new IllegalArgumentException("Invalid player type: " + playerType);
        }

        for (Card card : playerCards) {
            String valueName;
            int value = card.getValue();
            String name = card.getName();
            String suit = card.getSuit().toLowerCase();

            switch (name.toLowerCase()) {
                case "ace":
                    valueName = "ace";
                    break;
                case "jack":
                    valueName = "jack";
                    break;
                case "queen":
                    valueName = "queen";
                    break;
                case "king":
                    valueName = "king";
                    break;
                default:
                    valueName = String.valueOf(value);
                    break;
            }

            cardName.append(valueName).append("_of_").append(suit).append(".png ");
        }

        return cardName.toString().trim();
    }

    @Override
    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }
}
