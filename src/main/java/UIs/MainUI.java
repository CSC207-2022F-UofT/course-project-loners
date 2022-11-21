package UIs;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainUI {
    JFrame frame = new JFrame("Main page");
    JButton profile = new JButton("Check my user information");
    JButton edit_preference = new JButton("Edit my preference");
    JButton profile_finder = new JButton("Click to meet new people!");
    public UIController uiController;
    private DataFetchControl dataFetchControl = new DataFetchControl();
    public MainUI(String email){
        frame.setVisible(true);
        uiController = new UIController(email);
        JLabel welcome_message = new JLabel("Welcome," + email);
        int id = dataFetchControl.fetch_id_fromEmail(email);

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));
        frame.add(welcome_message);
        frame.add(profile);
        frame.add(edit_preference);
        frame.add(profile_finder);

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profile) {
                    frame.setVisible(false);
                    uiController.launchMyProfileUI();
                }}
        });

        edit_preference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == edit_preference) {
                    frame.setVisible(false);
                    uiController.launchEditPreferenceUI();

                }}
        });

        profile_finder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profile_finder) {
                    frame.setVisible(false);
                    new ProfileFinderUI(email);
                }}
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        new MainUI("taka@mail");
    }
}

