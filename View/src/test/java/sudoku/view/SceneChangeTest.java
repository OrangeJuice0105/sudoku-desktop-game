package sudoku.view;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class SceneChangeTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }

    @Test
    void clickingNewGame_shouldOpenGameView() {
        clickOn("New Game");
        verifyThat("#gameViewLabel", isVisible());
        verifyThat("#gameViewLabel", hasText("Game View"));
    }

}