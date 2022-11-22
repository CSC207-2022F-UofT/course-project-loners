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
    private int id;
    private JFrame frame;
    private GridBagConstraints constraints;

    /**
     * Construct an EditPreferencesUI, initializing id to the ID of the user who is interacting with the UI.
     *
     * @param id ID of the user who is interacting with this EditPreferencesUI
     */
    public EditPreferencesUI(int id) {
        this.id = id;
    }

    /**
     * Build the basic layout of the UI, including setting up the JFrame and adding initial constraints for the
     * components that will be added to the frame.
     */
    public void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        frame.setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when the window is closed
//        frame.setSize(670, 280);
        UIController.makeFrameFullSize(frame);
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
     * to the three preference inputs, a JButton for performing actions on the input collected, and a JLabel for presenting
     * a success message. Add event listeners to the text fields and button to respond to the input when typed and
     * button when clicked. Pass the preference inputs EditPreferencesControl.
     */
    public void addComponents() {
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

        // add a label containing a success message that appears when a button is clicked
        JLabel successMessage = new JLabel("Preferences successfully updated");
        constraints.gridx = 1;
        constraints.gridy = 4;
        frame.add(successMessage, constraints);
        successMessage.setVisible(false);

        // add the button that listens for and responds to a click
        JButton button = new JButton("Change Preferences");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // button click
                // pass preferences label-text mapping to EditPreferencesControl
                EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, id);
                editPreferencesControl.passPreferences();

                // present a success message
                successMessage.setVisible(true);

                /// direct user back to MyProfileUI

            }
        });
        constraints.gridx = 0;
        frame.add(button, constraints);
    }

    public void setVisible(boolean b) {
        if (b){
            this.buildBasicLayout();
            this.addComponents();
        }
    }

    public static void main(String[] args) { /// remove after testing, call methods from MyProfileUI
        SwingUtilities.invokeLater(new Runnable() { // invoke the methods from the event-dispatching thread
            public void run() {
                EditPreferencesUI test = new EditPreferencesUI(3);
                test.buildBasicLayout();
                test.addComponents();
            }
        });
    }
}
