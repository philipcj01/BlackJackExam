package vpf24.cphbusiness.interfaces;

public interface BlackJackInterface {
    void startGame();
    void playerHits();
    void playerStands();
    String getPlayerHand();
    String getDealerHand();
    String determineWinner();
    String getCardName(String playerType);

    void setGameUI(GameUI gameUI);
}
