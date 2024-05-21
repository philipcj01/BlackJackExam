package org.example.interfaces;

import org.example.BlackjackGame;

public interface GameUI {
    void updatePlayerHand(String hand);
    void updateDealerHand(String hand);
    void updatePlayerTotal(int total);
    void updateDealerTotal(int total);
    void disableHitAndStandButtons();
    void enableHitAndStandButtons();
    void displayMessage(String message);
    void setGame(BlackjackGame game);
}
