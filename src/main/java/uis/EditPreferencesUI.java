package uis;

import controllers.UIController;
import controllers.EditPreferencesControl;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Arrays;

/**
 * A User Interface class for interacting with the display that allows for editing the preferred age, gender, and
 * location range of other users.
 */
public class EditPreferencesUI {
    /** The window that contains the various components */
    private static JFrame frame;

    /** The object that allows for specifying the placement of each component, as part of the GridBagLayout */
    private static GridBagConstraints constraints;

    /**
     * Build the basic layout of the UI, including setting up the JFrame and adding initial constraints for the
     * components that will be added to the frame.
     */
    public static void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        frame.setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when the window is closed
        UIController.setFrameSize(frame); // set size to full screen
        frame.setVisible(true);

        // set constraints for placing the components
        constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.insets = new Insets(2, 2, 2, 2); // set external padding
        constraints.anchor = GridBagConstraints.WEST; // left align the components
    }

    /**
     * Add components to the UI, including three JLabels and three JTextFields corresponding to the three preference
     * inputs, a JButton for performing actions on the input collected, three JLabels for presenting the appropriate
     * message, and a JButton for returning to the main page. Add event listeners to the buttons to respond to button
     * clicks. Check the validity of the inputted preferences, and if valid, pass the preference inputs to
     * EditPreferencesControl along with id.
     *
     * @param id ID of the user who is interacting with the window built by EditPreferencesUI
     */
    public static void addComponents(int id) {
        String[] labels = {"Preferred age: ", "Preferred gender (male, female, other): ",
                "Preferred location range (in km): "};
        String[] preferenceLabels = {"preferred age", "preferred gender", "preferred location range"};
        HashMap<String, JTextField> preferenceFieldMap = new HashMap<>();
        HashMap<String, String> preferenceTextMap = new HashMap<>();

        // add and map labels and text fields
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            constraints.gridx = 0;
            constraints.gridy = i;
            frame.add(label, constraints);

            JTextField textField = new JTextField();
            label.setLabelFor(textField);
            textField.setPreferredSize(new Dimension(210, 20));
            constraints.gridx = 1; // and constraints.gridy = i
            frame.add(textField, constraints);

            preferenceFieldMap.put(preferenceLabels[i], textField);
        }

        // add a label containing a success message that appears when a "Change Preferences" button is clicked and
        // the preferences inputted are valid
        JLabel successMessage = new JLabel("Preferences successfully updated");
        successMessage.setForeground(Color.green.darker()); // set the success message color
        constraints.gridx = 1;
        constraints.gridy = 3;
        frame.add(successMessage, constraints);
        successMessage.setVisible(false);

        // add a label containing a failure message that appears when a "Change Preferences" button is clicked but
        // the preferences inputted are invalid
        JLabel failureMessage = new JLabel("Preferences could not be updated");
        failureMessage.setForeground(Color.red.darker()); // set the failure message color
        frame.add(failureMessage, constraints); // constraints.gridx = 1 and constraints.gridy = 3
        failureMessage.setVisible(false);

        // add a label containing a suggestion message that appears when a "Change Preferences" button is clicked but
        // the preferences inputted are invalid
        JLabel suggestionMessage = new JLabel("Please check your preferences and try again");
        suggestionMessage.setForeground(Color.red.darker()); // set the suggestion message color to match failure message
        constraints.gridy = 4; // and constraints.gridx = 1
        frame.add(suggestionMessage, constraints);
        suggestionMessage.setVisible(false);

        // add the "Change Preferences" button that listens for and responds to a click
        JButton changePreferencesButton = new JButton("Change Preferences");
        changePreferencesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePreferencesButton.addActionListener(e -> { // for a button click
            for (String label : preferenceFieldMap.keySet()) {
                String preferenceText = preferenceFieldMap.get(label).getText(); // retrieve the inputted text
                preferenceTextMap.put(label, preferenceText);
            }

            if (checkInputs(preferenceTextMap)) { // valid inputs
                // pass preferences label-text mapping to EditPreferencesControl
                EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceTextMap, id);
                editPreferencesControl.passPreferences();

                // present the success message
                failureMessage.setVisible(false);
                suggestionMessage.setVisible(false);
                successMessage.setVisible(true);

            } else { // invalid inputs
                // present the failure and suggestion messages
                successMessage.setVisible(false);
                failureMessage.setVisible(true);
                suggestionMessage.setVisible(true);
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        frame.add(changePreferencesButton, constraints);

        // add a back button that listens for and responds to a click
        JButton backButton = new JButton("Back to Main Page");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> { // button click
            frame.setVisible(false); // "close" the screen
            new UIController(id).launchMainUI(); // direct user back to MainUI
        });
        constraints.gridy = 4; // and constraints.gridx = 0;
        frame.add(backButton, constraints);
    }

    /**
     * Determine if the inputted preferences are among those accepted. The preferred age must be an integer between 0
     * and 100; preferred gender must be either male, female, or other; and preferred location range must be a
     * non-negative double.
     *
     * @param preferenceTextMap A mapping of preference labels to their corresponding text input
     * @return Whether all preference inputs are valid
     */
    public static boolean checkInputs(HashMap<String, String> preferenceTextMap) {
        // check if the inputted preferred age can be converted to an integer
        String ageInput = preferenceTextMap.get("preferred age");
        try {
            Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            return false;
        }

        // check if the inputted preferred gender is either male, female, or other
        String genderInput = preferenceTextMap.get("preferred gender");
        String[] genderInputs = {"male", "female", "other"};
        if (! Arrays.asList(genderInputs).contains(genderInput)) {
            return false;
        }

        // check if the inputted preferred location range can be converted to a double
        String locationRangeInput = preferenceTextMap.get("preferred location range");
        try {
            Double.parseDouble(locationRangeInput);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        // check if the inputted preferred age and preferred location range are within the accepted range
        int ageInt = Integer.parseInt(ageInput);
        double locationRangeDouble = Double.parseDouble(locationRangeInput);
        return (0 <= ageInt) && (ageInt <= 100) && (0 <= locationRangeDouble);
    }

    /**
     * Build the user interface for the user whose ID is id, by calling the buildBasicLayout and addComponents methods.
     *
     * @param id ID of the user who is interacting with the window build by EditPreferencesUI
     */
    public static void buildUI(int id) { // called by UIController
        SwingUtilities.invokeLater(() -> { // invoke the methods from the event-dispatching thread
            buildBasicLayout();
            addComponents(id);
        });
    }

    public static void main(String[] args) { /// for testing with an appropriately set up database.txt file
        buildUI(3);
    }
}
