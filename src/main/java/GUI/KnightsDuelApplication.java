package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * Load the startpage.fxml contents to a stage and show it to the user.
 */
public class KnightsDuelApplication extends Application {

    private Logger logger= LogManager.getLogger();

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        logger.info("The starting page loaded.");
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/startpage.fxml")));
        stage.setTitle("Knights Duel");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}