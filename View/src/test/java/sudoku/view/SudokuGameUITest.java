package sudoku.view;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;

import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class SudokuGameUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new App().start(stage);
        clickOn("New Game");
    }

    @Test
    void saveButtons_shouldBeVisible() {
        verifyThat("#saveGameToFileButton", isVisible());
        verifyThat("#saveGameToDatabaseButton", isVisible());
    }
}
