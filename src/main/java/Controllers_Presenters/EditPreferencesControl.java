package Controllers_Presenters;

import java.util.List;

public class EditPreferencesControl {
    private static String prefAge;
    private static String prefGender;
    private static String prefLocation;

    // arguments for constructor passed in by EditPreferencesUI
    public EditPreferencesControl(List<String> preferences) {
        this.prefAge = preferences.get(0);
        this.prefGender = preferences.get(1);
        this.prefLocation = preferences.get(2);
    }

    public static void setPreferences() {
        // create a new Preferences object with parameters of the appropriate type
    }

    public static void main(String[] args) {
        setPreferences();
    }
}
