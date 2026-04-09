package sudoku.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.framework.junit5.ApplicationTest;

public class MainMenuUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("sudoku.view.bundles.en_EN");
        ResourceBundle authorsBundle = ResourceBundle.getBundle("sudoku.view.bundles.EnglishAuthorsResourceBundle");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sudoku/view/MainMenu.fxml"), bundle);
        
        MainMenuController controller = new MainMenuController();
        loader.setController(controller);
        
        stage.setScene(new Scene(loader.load()));
    
        controller.universityLabel.setText(authorsBundle.getString("university"));
        controller.author1Label.setText(authorsBundle.getString("247026"));
        controller.author2Label.setText(authorsBundle.getString("247027"));
        
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
            "UI should contain all expected labels. Actual: " + actualTexts + ", Expected: " + expectedTexts);
    }

    @Test
    void testLocalization() {
        verifyThat("#menuLabel", hasText("Sudoku Main Menu")); 
        verifyThat("#difficultyLabel", hasText("Difficulty Level"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"en_EN", "pl_PL", "vi_VI"})
    void testLocalizationForLocale(String localeString) {
        String bundleName = "sudoku.view.bundles." + localeString;
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        
        assertFalse(bundle.getString("MENU_TITLE").isEmpty());
        assertFalse(bundle.getString("DIFFICULTY_LEVEL").isEmpty());
        assertFalse(bundle.getString("START_GAME").isEmpty());
        
        ResourceBundle englishBundle = ResourceBundle.getBundle("sudoku.view.bundles.en_EN");
        if (!localeString.equals("en_EN")) {
            assertNotEquals(englishBundle.getString("MENU_TITLE"), bundle.getString("MENU_TITLE"));
        }
    }

    @Test
    void testBundleKeysArePresent() {
        
        ResourceBundle bundle = ResourceBundle.getBundle("sudoku.view.bundles.en_EN");
        
        List<String> requiredKeys = List.of(
            "MENU_TITLE", "DIFFICULTY_LEVEL", "EASY_DIFFICULTY", 
            "MEDIUM_DIFFICULTY", "HARD_DIFFICULTY", "START_GAME",
            "EXIT_GAME", "LOAD_GAME", "SAVE_GAME_TO_FILE", 
            "SAVE_GAME_TO_DATABASE", "LOAD_GAME_FROM_DB", "CONTRIBUTOR"
        );
        
        for (String key : requiredKeys) {
            assertTrue(bundle.containsKey(key), "Bundle should contain key: " + key);
            assertFalse(bundle.getString(key).isEmpty(), "Bundle key '" + key + "' should not be empty");
        }
    }

}
