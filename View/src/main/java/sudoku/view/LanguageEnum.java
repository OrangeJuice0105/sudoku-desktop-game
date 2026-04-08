package sudoku.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageEnum {
    ENGLISH("English"),
    POLISH("Polski"),
    VIETNAMESE("Tiếng Việt");

    private final String displayName;
    private static LanguageEnum selectedLanguage = null;

    LanguageEnum(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static LanguageEnum getSelectedLanguage() {
        if (selectedLanguage != null) {
            return selectedLanguage;
        }

        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();

        return switch (language) {
            case "pl" -> POLISH;
            case "vi" -> VIETNAMESE;
            default -> ENGLISH;
        };
    }

    public static void setSelectedLanguage(LanguageEnum language) {
        selectedLanguage = language;
    }

    public static ResourceBundle getResourceBundle() {
        LanguageEnum selectedLanguage = getSelectedLanguage();

        if (selectedLanguage != null) {
            return switch (selectedLanguage) {
                case ENGLISH -> ResourceBundle.getBundle("sudoku.view.bundles.en_EN");
                case POLISH -> ResourceBundle.getBundle("sudoku.view.bundles.pl_PL");
                case VIETNAMESE -> ResourceBundle.getBundle("sudoku.view.bundles.vi_VI");
                default -> null;
            };
        } else {
            // If no language is selected, use the default locale
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();

            return switch (language) {
                case "pl" -> ResourceBundle.getBundle("sudoku.view.bundles.pl_PL");
                case "vi" -> ResourceBundle.getBundle("sudoku.view.bundles.vi_VI");
                default -> ResourceBundle.getBundle("sudoku.view.bundles.en_EN");
            };
        }
    }

    public static ResourceBundle getAuthorsResourceBundle() {

        return switch (getSelectedLanguage()) {
            case POLISH -> ResourceBundle.getBundle("sudoku.view.bundles.PolishAuthorsResourceBundle");
            case VIETNAMESE -> ResourceBundle.getBundle("sudoku.view.bundles.VietnameseAuthorsResourceBundle");
            default -> ResourceBundle.getBundle("sudoku.view.bundles.EnglishAuthorsResourceBundle");
        };
    }
}
