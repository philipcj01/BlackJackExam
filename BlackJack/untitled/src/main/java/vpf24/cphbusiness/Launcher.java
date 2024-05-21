package vpf24.cphbusiness;

import vpf24.cphbusiness.applications.BlackjackGUI;
import vpf24.cphbusiness.applications.BlackjackJavalinApp;
import vpf24.cphbusiness.interfaces.GameUI;

import javax.swing.*;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Run Blackjack GUI");
        System.out.println("2. Run Blackjack Javalin App");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        GameUI gameUI;
        BlackjackGame game;
        switch (choice) {
            case 1:
                gameUI = new BlackjackGUI();
                game = new BlackjackGame(gameUI);
                gameUI.setGame(game);
                SwingUtilities.invokeLater(() -> ((BlackjackGUI) gameUI).setVisible(true));
                break;
            case 2:
                gameUI = new BlackjackJavalinApp();
                game = new BlackjackGame(gameUI);
                gameUI.setGame(game);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }
    }
}
