package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    JTextArea name = new JTextArea("n");
    JTextArea gender = new JTextArea("g");
    JTextArea age = new JTextArea("a");
    JTextArea hobbies = new JTextArea("h");
    JTextArea bio = new JTextArea("b");

    //Object[] myProfile = getProfileWithEmail();
    Object[] otherProfile;
    // All other profile ids as strings
    //List<String> allOtherProfiles = new ProfileActionsManager(myProfile[0]).ListOfConnections();
    int curr = 0;


    public ProfileFinderUI(){
        frame.setSize(600, 600);

        layout.setRows(6);
        layout.setColumns(2);

        frame.setLayout(layout);

        likeButton.addActionListener(this);
        passButton.addActionListener(this);

        frame.add(nameLabel);
        frame.add(name);
        name.setEditable(false);
        frame.add(genderLabel);
        frame.add(gender);
        gender.setEditable(false);
        frame.add(ageLabel);
        frame.add(age);
        age.setEditable(false);
        frame.add(hobbiesLabel);
        frame.add(hobbies);
        hobbies.setEditable(false);
        frame.add(bioLabel);
        frame.add(bio);
        bio.setEditable(false);
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
            curr++;
        } else if (e.getSource() == passButton) {
            curr++;
        }
    }

    public static void main(String[] args){
        new ProfileFinderUI();
    }

}
