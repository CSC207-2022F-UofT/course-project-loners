package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Controllers_Presenters.*;
import Entities.Profile;
import Use_Cases.ObjectListToProfile;

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

    JTextArea name;
    JTextArea gender;
    JTextArea age;
    JTextArea hobbies;
    JTextArea bio;

    Object[] myProfile;
    Object[] otherProfile;
    // All other profile ids as strings
    List<String> allOtherProfiles;
    int curr;

    public ProfileFinderUI(int curr){
        this.curr = curr;
        myProfile = getProfileWithEmail(LogUI.myEmail);
        DataFetchControl d = new DataFetchControl();
        ConnectProfilesControl c = new ConnectProfilesControl(d.fetch_id_fromEmail(LogUI.myEmail));
        allOtherProfiles = c.passPreferences();
        if (curr > allOtherProfiles.size()){
            System.out.println("no more profiles!");
        } else {
            otherProfile = getProfileWithId(Integer.parseInt(allOtherProfiles.get(this.curr)));

            name = new JTextArea((String) otherProfile[1]);
            age = new JTextArea((String) otherProfile[4]);
            bio = new JTextArea((String) otherProfile[5]);
            gender = new JTextArea((String) otherProfile[6]);
            hobbies = new JTextArea((String) otherProfile[9]);


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
    }

    public ProfileFinderUI(){
        curr = 0;
        myProfile = getProfileWithEmail(LogUI.myEmail);
        DataFetchControl d = new DataFetchControl();
        ConnectProfilesControl c = new ConnectProfilesControl(d.fetch_id_fromEmail(LogUI.myEmail));
        allOtherProfiles = c.passPreferences();
        otherProfile = getProfileWithId(Integer.parseInt(allOtherProfiles.get(curr)));

        name = new JTextArea((String)otherProfile[1]);
        age = new JTextArea((String)otherProfile[4]);
        bio = new JTextArea((String)otherProfile[5]);
        gender = new JTextArea((String)otherProfile[6]);
        hobbies = new JTextArea((String)otherProfile[9]);


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

    public Object[] getProfileWithEmail(String email){
        DataFetchControl d = new DataFetchControl();
        int myid = d.fetch_id_fromEmail(email);
        return d.fetch_fromid(myid);
    }

    public Object[] getProfileWithId(int id){
        DataFetchControl d = new DataFetchControl();
        return d.fetch_fromid(id);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == likeButton){
            String newLikes = (String) myProfile[11];

            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr);
        } else if (e.getSource() == passButton) {
            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr);
        }
    }

    public static void main(String[] args){
        new ProfileFinderUI();
    }

}
