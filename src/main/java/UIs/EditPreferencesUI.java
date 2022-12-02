package UIs;

import Controllers_Presenters.UIController;
import Controllers_Presenters.EditPreferencesControl;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * A User Interface class for interacting with the display that allows for editing the preferred age, gender, and
 * location range of other users.
 */
public class EditPreferencesUI {
    private static JFrame frame;
    private static GridBagConstraints constraints;

    /**
     * Build the basic layout of the UI, including setting up the JFrame and adding initial constraints for the
     * components that will be added to the frame.
     */
    public static void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        frame.setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when the window is closed
        UIController.makeFrameFullSize(frame); // set size to full screen
        frame.setVisible(true);

        // set constraints for placing the components
        constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.insets = new Insets(2, 2, 2, 2); // set external padding
        constraints.anchor = GridBagConstraints.WEST; // left align the components
    }

    /**
     * Add components to the UI, including three JLabels and three JTextFields corresponding to the three preference
     * inputs, a JButton for performing actions on the input collected, a JLabel for presenting a success message, and
     * a JButton for returning to the main page. Add event listeners to the buttons to respond to button clicks. Pass
     * the preference inputs to EditPreferencesControl along with id.
     *
     * @param id ID of the user who is interacting with the EditPreferencesUI
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

        // add a label containing a success message that appears when a "Change Preferences" button is clicked
        JLabel successMessage = new JLabel("Preferences successfully updated");
        successMessage.setForeground(Color.green.darker()); // set the success message color
        constraints.gridx = 1;
        constraints.gridy = 3;
        frame.add(successMessage, constraints);
        successMessage.setVisible(false);

        // add the "Change Preferences" button that listens for and responds to a click
        JButton changePreferencesButton = new JButton("Change Preferences");
        changePreferencesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePreferencesButton.addActionListener(e -> { // for a button click
            for (String label : preferenceFieldMap.keySet()) {
                String preferenceText = preferenceFieldMap.get(label).getText(); // retrieve the inputted text
                preferenceTextMap.put(label, preferenceText);
            }

            // pass preferences label-text mapping to EditPreferencesControl
            EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceTextMap, id);
            editPreferencesControl.passPreferences();

            // present a success message
            successMessage.setVisible(true);
        });
        constraints.gridx = 0; // and constraints.gridy = 3
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
     * Build the user interface for the user with ID id, by calling the buildBasicLayout and addComponents methods.
     *
     * @param id ID of the user who is interacting with the EditPreferencesUI
     */
    public static void buildUI(int id) { // called by UIController
        SwingUtilities.invokeLater(() -> { // invoke the methods from the event-dispatching thread
            buildBasicLayout();
            addComponents(id);
        });
    }

//    public static void main(String[] args) { /// for testing with an appropriately set up database.txt file
//        buildUI(3);
//    }
}
