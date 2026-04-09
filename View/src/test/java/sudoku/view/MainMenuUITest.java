package sudoku.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class MainMenuUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sudoku/view/MainMenu.fxml"), bundle);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @Test
    void app_shouldLaunch_andMainMenuLabel() {
        verifyThat("#menuLabel", hasText("Sudoku Main Menu"));

    }

    @Test
    void app_shouldLaunch_andDisplayAllLabelsOnScreen() {
        Set<Node> nodes = lookup(".label").queryAll();

        List<String> actualTexts = nodes.stream()
            .map(node -> ((Label) node).getText()).filter(text -> text != null && !text.isBlank())
            .sorted().toList();

        List<String> expectedTexts = List.of(
                "Lodz University of Technology",
                "Sudoku Main Menu",
                "Oskar Kacprzak", 
                "Wojciech Kapica",
                "Contributor: Duc Cam Thai"
        ).stream().sorted().toList();

        assertTrue(actualTexts.containsAll(expectedTexts),
            "UI should contain all expected labels");
    }

    @Test
    void testLocalization() {
        verifyThat("#menuTitleLabel", hasText("Sudoku")); 
        verifyThat("#difficultyLevelLabel", hasText("Difficulty Level"));
    }

    @Test
    void clickingNewGame_shouldOpenGameView() {
        clickOn("New Game");
        verifyThat(".label", hasText("Game View"));
    }

}
