package GUI;

import GameLogic.ChessPiece;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * It's a simple way to use a MassageBox to show the Game Over information to the users.
 */

public class GameOverController {

    private Logger logger= LogManager.getLogger();

    public void handleGameOver(ChessPiece.PieceColor lastColor, String playerName){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        alert.setHeaderText(String.format(playerName+" (the "+lastColor+" player) won this game."));
        alert.setContentText("If you want revenge click the New Game button!");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("The game ended and the user pressed the ok button.");
            }
        });
        logger.debug("The Game Over information showed.");
    }
}
