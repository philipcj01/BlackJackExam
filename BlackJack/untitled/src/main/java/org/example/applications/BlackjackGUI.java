package org.example.applications;

import org.example.BlackjackGame;
import org.example.interfaces.GameUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGUI extends JFrame implements GameUI {
    private JButton hitButton, standButton, dealButton;
    private JLabel playerTotalLabel, dealerTotalLabel;
    private JLabel playerLabel, dealerLabel;
    private BlackjackGame game;

    public BlackjackGUI() {
        super("Blackjack Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initializeComponents();
        addComponentsToFrame();
    }

    private void initializeComponents() {
        playerTotalLabel = new JLabel();
        dealerTotalLabel = new JLabel();
        playerLabel = new JLabel();
        dealerLabel = new JLabel();

        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        dealButton = new JButton("Deal");

        addActionListeners();
    }

    private void addComponentsToFrame() {
        JPanel playerPanel = createPanel(playerTotalLabel, playerLabel);
        JPanel dealerPanel = createPanel(dealerTotalLabel, dealerLabel);

        JPanel gamePanel = new JPanel(new GridLayout(1, 2));
        gamePanel.add(playerPanel);
        gamePanel.add(dealerPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(dealButton);

        add(gamePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createPanel(JLabel totalLabel, JLabel cardLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(totalLabel, BorderLayout.NORTH);
        panel.add(cardLabel, BorderLayout.CENTER);
        return panel;
    }

    private void addActionListeners() {
        hitButton.addActionListener(e -> game.playerHits());
        standButton.addActionListener(e -> game.playerStands());
        dealButton.addActionListener(e -> game.startGame());
    }

    @Override
    public void updatePlayerHand(String hand) {
        playerLabel.setIcon(combineCardImages(game.getCardName("player")));
    }

    @Override
    public void updateDealerHand(String hand) {
        dealerLabel.setIcon(combineCardImages(game.getCardName("dealer")));
    }

    @Override
    public void updatePlayerTotal(int total) {
        playerTotalLabel.setText("Player Total: " + total);
    }

    @Override
    public void updateDealerTotal(int total) {
        dealerTotalLabel.setText("Dealer Total: " + total);
    }

    @Override
    public void disableHitAndStandButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    @Override
    public void enableHitAndStandButtons() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    @Override
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void setGame(BlackjackGame game) {
        this.game = game;
        game.setGameUI(this);  // Ensure the game has a reference to this UI
        game.startGame();  // Start the game
    }

    private ImageIcon combineCardImages(String cardNames) {
        List<BufferedImage> cardImages = new ArrayList<>();
        String[] names = cardNames.split("\\s+");

        try {
            for (String name : names) {
                BufferedImage cardImage = ImageIO.read(Paths.get("BlackJack/untitled/src/main/resources/png/" + name).toFile());
                cardImages.add(cardImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cardImages.isEmpty()) {
            return null;
        }

        int width = cardImages.get(0).getWidth();
        int height = cardImages.get(0).getHeight();

        BufferedImage combinedImage = new BufferedImage(width * cardImages.size(), height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combinedImage.getGraphics();

        int x = 0;
        for (BufferedImage cardImage : cardImages) {
            g.drawImage(cardImage, x, 0, null);
            x += width;
        }
        g.dispose();

        return new ImageIcon(combinedImage);
    }
}
