package vpf24.cphbusiness.applications;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import vpf24.cphbusiness.BlackjackGame;
import vpf24.cphbusiness.interfaces.GameUI;

public class BlackjackJavalinApp implements GameUI {
    private BlackjackGame game;
    private final Javalin app;
    private String message = "Game started. Good luck!";
    private boolean gameOver = false;

    public BlackjackJavalinApp() {
        this.app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(7000);

        app.get("/", ctx -> {
            ctx.html(renderGameState());
        });

        app.get("/hit", ctx -> {
            if (!gameOver) {
                game.playerHits();
                checkWinner();
                ctx.html(renderGameState());
            }
        });

        app.get("/stand", ctx -> {
            if (!gameOver) {
                game.playerStands();
                checkWinner();
                ctx.html(renderGameState());
            }
        });

        app.get("/deal", ctx -> {
            game.startGame();
            resetGame();
            ctx.html(renderGameState());
        });
    }

    private void checkWinner() {
        String result = game.determineWinner();
        switch (result) {
            case "Player busts. Dealer wins!":
            case "Dealer busts. Player wins!":
            case "It's a push! No one wins.":
            case "Player wins!":
            case "Dealer wins!":
                message = result;
                gameOver = true;
                disableHitAndStandButtons();
                break;
            default:
                message = "Game continues";
                break;
        }
    }

    private void resetGame() {
        message = "Game started. Good luck!";
        gameOver = false;
        enableHitAndStandButtons();
    }

    private String renderGameState() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head>");
        html.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/styles.css\">");
        html.append("</head><body>");
        html.append("<h2>Black Jack</h2>");
        html.append("<p>").append(message).append("</p>");
        html.append("<div class='hand'>");
        html.append("<h3>Player's Hand (").append(game.player.getHandValue()).append(")</h3>");
        html.append(visualizeHand(game.getCardName("player")));
        html.append("</div>");
        html.append("<div class='hand'>");
        html.append("<h3>Dealer's Hand (").append(game.dealer.getHandValue()).append(")</h3>");
        html.append(visualizeHand(game.getCardName("dealer")));
        html.append("</div>");
        html.append("<div class='buttons'>");
        if (!gameOver) {
            html.append("<a href=\"/hit\">Hit</a>");
            html.append("<a href=\"/stand\">Stand</a>");
        }
        html.append("<a href=\"/deal\">Deal</a>");
        html.append("</div>");
        html.append("</body></html>");
        return html.toString();
    }

    private String visualizeHand(String cardNames) {
        StringBuilder visual = new StringBuilder();
        String[] cards = cardNames.split("\\s+");
        for (String card : cards) {
            visual.append("<img src='/png/").append(card).append("' style='width: 100px; height: auto;'/>");
        }
        return visual.toString();
    }

    @Override
    public void updatePlayerHand(String hand) {
    }

    @Override
    public void updateDealerHand(String hand) {
    }

    @Override
    public void updatePlayerTotal(int total) {
    }

    @Override
    public void updateDealerTotal(int total) {
    }

    @Override
    public void disableHitAndStandButtons() {
        gameOver = true;
    }

    @Override
    public void enableHitAndStandButtons() {
        gameOver = false;
    }

    @Override
    public void displayMessage(String message) {
        this.message = message;
    }

    @Override
    public void setGame(BlackjackGame game) {
        this.game = game;
        game.setGameUI(this);
        game.startGame();
    }
}
