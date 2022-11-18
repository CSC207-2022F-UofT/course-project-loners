package UIs;

import Controllers_Presenters.EditPreferencesControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class EditPreferencesUI {
    private static JFrame frame;
    private static JPanel panel;

    public static void buildBasicLayout() {
        frame = new JFrame("Preference Editor");
        panel = new JPanel(new SpringLayout());
//        panel.setOpaque(true);
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // terminates the program when the window is closed
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void addComponents() {
        // add labels and text fields that listen for and respond to typing
        String[] labels = {"Preferred age: ", "Preferred gender (male, female, other): ", "Preferred location: "};
        List<String> preferenceTexts = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i], JLabel.TRAILING);
            panel.add(label);
            JTextField textField = new JTextField(10);
            label.setLabelFor(textField);
            panel.add(textField);

            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String text = textField.getText();
                    preferenceTexts.add(text);
                }
            });
        }

        // pass text from JTextFields to EditPreferencesControl
        EditPreferencesControl preferences = new EditPreferencesControl(preferenceTexts);

        // add a button that listens and responds to a click
        JButton button = new JButton("Click here");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
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
