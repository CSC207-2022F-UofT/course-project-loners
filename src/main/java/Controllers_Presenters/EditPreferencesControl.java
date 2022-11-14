package Controllers_Presenters;

import java.util.List;

public class EditPreferencesControl {
    private String prefAge;
    private String prefGender;
    private String prefLocation;

    // arguments for constructor passed in by EditPreferencesUI
    public EditPreferencesControl(List<String> preferences) {
        this.prefAge = preferences.get(0);
        this.prefGender = preferences.get(1);
        this.prefLocation = preferences.get(2);
    }

    public static void setPreferences() {
        // find the Profile object using DataFetchSend to get the corresponding user
        // create a new Preferences object with parameters of the appropriate type
    }

    public static void main(String[] args) {
        setPreferences();
    }
}
