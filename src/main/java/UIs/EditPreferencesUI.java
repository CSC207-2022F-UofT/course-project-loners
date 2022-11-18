package UIs;

import Controllers_Presenters.EditPreferencesControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EditPreferencesUI {
    private static String id;
    private static JFrame frame;
    private static JPanel panel;

    public EditPreferencesUI(String id) {
        this.id = id;
    }

    public static void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        panel = new JPanel(new SpringLayout());
//        panel.setOpaque(true);
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // terminate the program when the window is closed
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void addComponents() {
        // add labels and text fields that listen for and respond to typing
        String[] labels = {"Preferred age: ", "Preferred gender (male, female, other): ", "Preferred location (postal code): "};
        String[] preferenceLabels = {"preferred age", "preferred gender", "preferred location"};
        HashMap<String, String> preferenceMap = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            String preferenceLabel = preferenceLabels[i];
            JLabel label = new JLabel(labels[i], JLabel.TRAILING);
            panel.add(label);
            JTextField textField = new JTextField(10);
            label.setLabelFor(textField);
            panel.add(textField);

            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String preferenceText = textField.getText();
                    preferenceMap.put(preferenceLabel, preferenceText);
                }
            });
        }

        // add a button that listens and responds to a click
        JButton button = new JButton("Change Preferences"); // or "View Profiles"
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // pass preferences label-text mapping to EditPreferencesControl
                EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, id);
                editPreferencesControl.passPreferences();
                // present a success message

            }
        });
        panel.add(button);
    }

    public static void layOutComponents() {
        // add the components to the content frame of the JFrame
        Spring x = Spring.constant(6);
        for (int c = 0; c < 2; c++) {
            Spring width = Spring.constant(0);

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // invoke the methods from the event-dispatching thread
            public void run() {
                buildBasicLayout();
                addComponents();
                layOutComponents();
            }
        });
    }
}
