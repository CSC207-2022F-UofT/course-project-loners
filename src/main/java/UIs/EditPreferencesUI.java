/*
A user interface class for interacting with the display that allows for editing the preferred age, gender, and
location range of other users.
 */

package UIs;

import Controllers_Presenters.EditPreferencesControl;
import Controllers_Presenters.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
//        frame.setSize(670, 280);
        UIController.makeFrameFullSize(frame); // set size to full screen
        frame.setLocationRelativeTo(null); // open the window at the center of the screen
        frame.setVisible(true);

        // set constraints for placing the components
        constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.insets = new Insets(2, 2, 2, 2); // set external padding
        constraints.anchor = GridBagConstraints.WEST; // left align components
    }

    /**
     * Add components to the UI, including a JLabel for instructions, three JLabels and three JTextFields corresponding
     * to the three preference inputs, a JButton for performing actions on the input collected, a JLabel for presenting
     * a success message, and a JButton for returning to the main page. Add event listeners to the text fields and
     * buttons to respond to the input when typed and buttons when clicked. Pass the preference inputs to
     * EditPreferencesControl with id.
     *
     * @param id ID of the user who is interacting with this EditPreferencesUI
     */
    public static void addComponents(int id) {
        String[] labels = {"Preferred age: ", "Preferred gender (male, female, other): ",
                "Preferred location range (in km): "};
        String[] preferenceLabels = {"preferred age", "preferred gender", "location range"};
        HashMap<String, String> preferenceMap = new HashMap<>();

        // add a label containing instructions
        JLabel instructions = new JLabel("Press Return after each input");
        constraints.gridx = 1;
        constraints.gridy = 0;
        frame.add(instructions, constraints);

        // add labels and text fields that listen for and respond to typing
        for (int i = 0; i < labels.length; i++) {
            constraints.gridy = i + 1;

            JLabel label = new JLabel(labels[i]);
            constraints.gridx = 0;
            frame.add(label, constraints);

            JTextField textField = new JTextField();
            label.setLabelFor(textField);
            textField.setPreferredSize(new Dimension(210, 20));
            constraints.gridx = 1;
            frame.add(textField, constraints);

            String preferenceLabel = preferenceLabels[i];
            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { // return key press
                    String preferenceText = textField.getText();
                    preferenceMap.put(preferenceLabel, preferenceText);
                }
            });
        }

        // add a label containing a success message that appears when a "Change Preferences" button is clicked
        JLabel successMessage = new JLabel("Preferences successfully updated");
        constraints.gridx = 1;
        constraints.gridy = 4;
        frame.add(successMessage, constraints);
        successMessage.setVisible(false);

        // add the "Change Preferences" button that listens for and responds to a click
        JButton button = new JButton("Change Preferences");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // button click
                // pass preferences label-text mapping to EditPreferencesControl
                EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, id);
                editPreferencesControl.passPreferences();

                // present a success message
                successMessage.setVisible(true);
            }
        });
        constraints.gridx = 0;
        frame.add(button, constraints);

        // add a back button that listens for and responds to a click
        JButton back = new JButton("Back to Main Page");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // button click
                frame.setVisible(false); // "close" the screen
                new UIController(id).launchMainUI(); // direct user back to MainUI
            }
        });
        constraints.gridy = 5;
        frame.add(back, constraints);
    }

    /**
     * Build the user interface for the user with ID id, by calling the buildBasicLayout and addComponents methods.
     *
     * @param id ID of the user who is interacting with this EditPreferencesUI
     */
    public static void buildUI(int id) { // called by UIController
        SwingUtilities.invokeLater(new Runnable() { // invoke the methods from the event-dispatching thread
            public void run() {
                buildBasicLayout();
                addComponents(id);
            }
        });
    }

//    public static void main(String[] args) { /// for testing
//        buildUI(3);
//    }
}
