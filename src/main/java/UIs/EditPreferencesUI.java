package UIs;

import Controllers_Presenters.EditPreferencesControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EditPreferencesUI {
    private static int id;
    private static JFrame frame;
    private static Container pane;

    public EditPreferencesUI(int id) {
        this.id = id;
    }

    public static void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when the window is closed
        frame.setSize(530, 250);
        frame.setVisible(true);
    }

    public static void addComponents() {
        String[] labels = {"Preferred age: ", "Preferred gender (male, female, other): ",
                "Preferred location (postal code, space in the middle): ", "Preferred location range (in km): "};
        String[] preferenceLabels = {"preferred age", "preferred gender", "preferred location", "location range"};
        HashMap<String, String> preferenceMap = new HashMap<>();

//        JLabel successMessage = new JLabel("Preferences successfully updated");
//        successMessage.setVisible(false);
//        pane.add(successMessage);

        // add labels and text fields that listen for and respond to typing
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            pane.add(label);

            JTextField textField = new JTextField(10);
//            label.setLabelFor(textField);
            pane.add(textField);

            String preferenceLabel = preferenceLabels[i];
            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String preferenceText = textField.getText();
                    preferenceMap.put(preferenceLabel, preferenceText);
                }
            });
        }

        // add a button that listens for and responds to a click
        JButton button = new JButton("Change Preferences");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // pass preferences label-text mapping to EditPreferencesControl
                EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, id);
                editPreferencesControl.passPreferences();
                /// present a success message, direct user back to MyProfileUI
//                successMessage.setVisible(true);

            }
        });
        pane.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // invoke the methods from the event-dispatching thread
            public void run() {
                buildBasicLayout();
                addComponents();
            }
        });
    }
}
