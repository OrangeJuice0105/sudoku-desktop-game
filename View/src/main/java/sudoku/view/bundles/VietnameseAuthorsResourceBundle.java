package sudoku.view.bundles;

import java.util.ListResourceBundle;

public class VietnameseAuthorsResourceBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "university", "Đại học công nghệ Lodz" },
                { "247026", "Oskar Kacprzak" },
                { "247027", "Wojciech Kapica" },
        };

    }
}
