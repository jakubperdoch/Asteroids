package asteroids;

import asteroids.ui.GameScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameScreen gameScreen = new GameScreen();
        stage.setTitle("Asteroids");
        stage.setScene(gameScreen.getScene());
        stage.show();
    }
}
