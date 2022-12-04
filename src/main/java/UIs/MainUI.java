package UIs;

import Controllers_Presenters.DataController;
import Controllers_Presenters.UIController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    JFrame frame = new JFrame("Main page");
    JButton profile = new JButton("User Info");
    JButton editPreference = new JButton("Filter settings");
    JButton profileFinder = new JButton("Click here to match new people!");

    /**
     * The constructor of MainUI, which function as a "main page" for users.
     * @param id user id, assuming it is valid
     * @param email user's email, assuming it is valid
     */
    public MainUI(int id, String email){
        // get user's name to show in the main page
        Object[] user_data = (Object[]) DataController.fetchFromId(id)[0]; // get user info based on id
        JLabel welcome_message = new JLabel("Welcome back, " + user_data[0]);

        // add components to the frame
        frame.setLayout(new GridLayout(4,1));
        frame.add(welcome_message);
        frame.add(profile);
        frame.add(editPreference);
        frame.add(profileFinder);

        // setting the frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        // add responses to the buttons
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profile) {
                    frame.setVisible(false);
                    new UIController(id).launchMyProfileUI();
                }}
        });
        editPreference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editPreference) {
                    frame.setVisible(false);
                    new UIController(email).launchEditPreferencesUI();
                }}
        });
        profileFinder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profileFinder) {
                    UIController controller = new UIController(id);
                    if (controller.checkIfPreference()){
                        JOptionPane.showMessageDialog(null, "Please set up the filter first.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else{
                        frame.setVisible(false);
                        controller.launchProfileFinderUI();
                    }
                }}
        });
    }
}

