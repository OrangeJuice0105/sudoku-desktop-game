package sudoku.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class SceneChangeTest extends ApplicationTest {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        new App().start(stage);
    }

    @Test
    void clickingNewGame_shouldOpenGameView() {
        Button startButton = lookup("#startGameButton").queryAs(Button.class);
        interact(startButton::fire);
        verifyThat("#gameViewLabel", isVisible());
        verifyThat("#gameViewLabel", hasText("Game View"));
    }

    @Test
    void clickingNewGame_shouldMoveToNewScreen() {

        Scene oldScene = primaryStage.getScene();
        Object oldRoot = oldScene.getRoot();

        Button startButton = lookup("#startGameButton").queryAs(Button.class);
        interact(startButton::fire);

        Scene newScene = primaryStage.getScene();
        Object newRoot = newScene.getRoot();

        assertNotSame(oldRoot, newRoot, "Scene root should change after clicking New Game");
        
    }

    @Test
    void quitButton_shouldCloseApplication() {

        Button exitButton = lookup("#exitGameButton").queryAs(Button.class);

        interact(exitButton::fire);
        assertFalse(primaryStage.isShowing(), "Stage should be closed after clicking Quit");
    }



}