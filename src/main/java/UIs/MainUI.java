package UIs;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.UIController;
import Entities.Profile;
import Use_Cases.ObjectListToProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    JFrame frame = new JFrame("Main page");
    JButton profile = new JButton("User Info");
    JButton edit_preference = new JButton("Filter settings");
    JButton profile_finder = new JButton("Click here to match new people!");
    public MainUI(int id, String email){
        // get user's name to show in the main page
        Object[] user_data = DataFetchControl.fetch_fromid(id);
        Profile p = new ObjectListToProfile().returnObjListAsProfile(user_data) ;
        JLabel welcome_message = new JLabel("Welcome back, " + p.getName());

        frame.setLayout(new GridLayout(4,1));
        frame.add(welcome_message);
        frame.add(profile);
        frame.add(edit_preference);
        frame.add(profile_finder);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profile) {
                    frame.setVisible(false);
                    new UIController(id).launchMyProfileUI();
                }}
        });

        edit_preference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == edit_preference) {
                    frame.setVisible(false);
                    new UIController(email).launchEditPreferencesUI();
                }}
        });

        profile_finder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == profile_finder) {
                    frame.setVisible(false);
                    new UIController(id).launchProfileFinderUI();
                }}
        });
    }

    public static void main(String[] args) {
        new MainUI(2, "taka@mail");
    }
}

