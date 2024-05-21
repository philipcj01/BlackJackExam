package org.example.demos;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomCardDemo extends JFrame {
    private JLabel cardLabel;

    public RandomCardDemo() {
        super("Random Card Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        cardLabel = new JLabel();
        add(cardLabel, BorderLayout.CENTER);

        JButton showCardButton = new JButton("Show Random Card");
        showCardButton.addActionListener(e -> showRandomCard());
        add(showCardButton, BorderLayout.SOUTH);
    }

    private void showRandomCard() {
        // Array of card names
        String[] cards = {
                "2_of_spades.png", "3_of_spades.png", "4_of_spades.png", "5_of_spades.png", "6_of_spades.png",
        };

        Random random = new Random();
        String randomCard = cards[random.nextInt(cards.length)];

        ImageIcon cardImage = new ImageIcon("BlackJack/untitled/src/main/resources/png/" + randomCard);
        cardLabel.setIcon(cardImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RandomCardDemo demo = new RandomCardDemo();
            demo.setVisible(true);
        });
    }
}
