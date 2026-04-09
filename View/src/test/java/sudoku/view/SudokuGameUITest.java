package sudoku.view;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;

import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;

public class SudokuGameUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {

        GameController controller = new GameController(DifficultyEnum.EASY);

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/sudoku/view/SudokuGame.fxml"),
            LanguageEnum.getResourceBundle()
        );
        loader.setController(controller);

        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void saveButtons_shouldBeVisible() {
        verifyThat("#saveGameToFileButton", isVisible());
        verifyThat("#saveGameToDatabaseButton", isVisible());
    }

    @Test
    void saveButtons_shouldBeEnabled() {
        verifyThat("#saveGameToFileButton", isEnabled());
        verifyThat("#saveGameToDatabaseButton", isEnabled());
    }

    @Test
    void saveToFileButton_shouldBeClickable() {
        assertDoesNotThrow(() -> clickOn("#saveGameToFileButton"));
    }

    @Test
    void saveToDatabaseButton_shouldBeClickable() {
        assertDoesNotThrow(() -> clickOn("#saveGameToDatabaseButton"));
    }

    @Test
    void sudokuBoard_shouldBeVisible() {
        verifyThat("#sudokuBoardGridPane", isVisible());
    }

    @Test
    void sudokuBoard_mustNotBeNull() {
        GridPane board = lookup("#sudokuBoardGridPane").queryAs(GridPane.class);
        assertNotNull(board);
    }

    @Test
    void sudokuBoard_shouldContain81Cells() {
        GridPane board = lookup("#sudokuBoardGridPane").queryAs(GridPane.class);
        long cellCount = board.getChildren().stream()
                .filter(node -> node instanceof TextField).count();
        assertEquals(81, cellCount);
    }

}