package vpf24.cphbusiness.applications;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import vpf24.cphbusiness.BlackjackGame;
import vpf24.cphbusiness.interfaces.GameUI;

public class BlackjackJavalinApp implements GameUI {
    private BlackjackGame game;
    private final Javalin app;

    public BlackjackJavalinApp() {
        this.app = Javalin.create(config -> {
            config.addStaticFiles("/png", Location.CLASSPATH);
        }).start(7000);

        app.get("/", ctx -> {
            ctx.html(renderGameState());
        });

        app.get("/hit", ctx -> {
            game.playerHits();
            ctx.html(renderGameState());
        });

        app.get("/stand", ctx -> {
            game.playerStands();
            ctx.html(renderGameState());
        });

        app.get("/deal", ctx -> {
            game.startGame();
            ctx.html(renderGameState());
        });
    }

    private String renderGameState() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head></head><body>");
        html.append("<h2>Game State</h2>");
        html.append("<p>").append(game.getGameState()).append("</p>");
        html.append("<h3>Player's Hand</h3>");
        html.append(visualizeHand(game.getCardName("player")));
        html.append("<h3>Dealer's Hand</h3>");
        html.append(visualizeHand(game.getCardName("dealer")));
        html.append("<p><a href=\"/hit\">Hit</a> | <a href=\"/stand\">Stand</a> | <a href=\"/deal\">Deal</a></p>");
        html.append("</body></html>");
        return html.toString();
    }

    private String visualizeHand(String cardNames) {
        StringBuilder visual = new StringBuilder();
        String[] cards = cardNames.split("\\s+");
        for (String card : cards) {
            visual.append("<img src='/").append(card).append("' style='width: 100px; height: auto;'/>");
        }
        return visual.toString();
    }

    @Override
    public void updatePlayerHand(String hand) {
        // No need to implement for Javalin
    }

    @Override
    public void updateDealerHand(String hand) {
        // No need to implement for Javalin
    }

    @Override
    public void updatePlayerTotal(int total) {
        // No need to implement for Javalin
    }

    @Override
    public void updateDealerTotal(int total) {
        // No need to implement for Javalin
    }

    @Override
    public void disableHitAndStandButtons() {
        // No need to implement for Javalin
    }

    @Override
    public void enableHitAndStandButtons() {
        // No need to implement for Javalin
    }

    @Override
    public void displayMessage(String message) {
        // No need to implement for Javalin
    }

    @Override
    public void setGame(BlackjackGame game) {
        this.game = game;
        game.setGameUI(this);  // Ensure the game has a reference to this UI
        game.startGame();  // Start the game
    }
}
