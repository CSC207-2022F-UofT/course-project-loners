package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers_Presenters.*;

public class ProfileFinderUI implements ActionListener{
    // This class will show a profile on the screen based on the matching algorithm
    JFrame frame = new JFrame();
    GridLayout layout = new GridLayout(6, 2, 10, 20);
    JLabel nameLabel = new JLabel("Name: ");
    JLabel genderLabel = new JLabel("Gender: ");
    JLabel ageLabel = new JLabel("Age: ");
    JLabel bioLabel = new JLabel("Bio: ");
    JLabel hobbiesLabel = new JLabel("Hobbies: ");

    JButton likeButton = new JButton("Like");
    JButton passButton = new JButton("Pass");

    JTextArea name = new JTextArea();
    JTextArea gender = new JTextArea();
    JTextArea age = new JTextArea();
    JTextArea bio = new JTextArea();

    Object[] myProfile;
    Object[] otherProfile;

    public ProfileFinderUI(){
        frame.setSize(600, 600);

        layout.setRows(6);
        layout.setColumns(2);

        frame.setLayout(layout);

        // ProfileActionsManager otherProfiles = new ProfileActionsManager();

        likeButton.addActionListener(this);
        passButton.addActionListener(this);

        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(ageLabel);
        frame.add(hobbiesLabel);
        frame.add(bioLabel);
        frame.add(likeButton);
        frame.add(passButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

//    public Object[] getProfileWithEmail(String email){
//        DataFetchControl d = new DataFetchControl();
//        // This method is not written yet but I want to find a profile with an email
//        return d.fetch_fromemail(email);
//    }

    public Object[] getProfileWithId(int id){
        DataFetchControl d = new DataFetchControl();
        return d.fetch_fromid(id);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == likeButton){

        } else if (e.getSource() == passButton) {

        }
    }

    public static void main(String[] args){
        new ProfileFinderUI();
    }

}
