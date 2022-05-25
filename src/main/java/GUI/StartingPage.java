package GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * Handle the onClick events on the buttons in the startpage.fxml
 */

public class StartingPage {

    private Logger logger = LogManager.getLogger();

    @FXML
    private TextField playerOneTextField;

    @FXML
    private TextField playerTwoTextField;

    /**
     * Switch the scene to the gamepage.fxml content and show it.
     */
    @FXML
    public void startGame(@NotNull ActionEvent event) throws IOException {
        String playerOneName = playerOneTextField.getText();
        String playerTwoName = playerTwoTextField.getText();
        if(!playerOneName.isEmpty() && !playerTwoName.isEmpty()) {
            GameController.setPlayers(playerOneName, playerTwoName);
            logger.info("Player 1's name is set to {}, and Player 2's name is set to {}",
                    playerOneName, playerTwoName);
        }
        else{
            handleExit(event);
            return;
        }
        startGameAgain(event);
    }

    @FXML
    public void startGameAgain(@NotNull ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/gamepage.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
        logger.info("The game page is loaded.");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        logger.info("The game ended, now we quit.");
        System.out.println("Exiting...");
        Platform.exit();
    }
}