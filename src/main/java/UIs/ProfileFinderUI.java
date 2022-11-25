package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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

    JTextArea name;
    JTextArea gender;
    JTextArea age;
    JTextArea hobbies;
    JTextArea bio;

    Object[] myProfile;
    Object[] otherProfile;
    // All other profile ids as strings
    List<Integer> allOtherProfiles;
    int curr;

    String email;

    public ProfileFinderUI(int curr, String email){
        this.curr = curr;
        this.email = email;
        myProfile = getProfileWithEmail(email);
        DataFetchControl d = new DataFetchControl();
        allOtherProfiles = ConnectProfilesControl.gatherConnections(d.fetch_id_fromEmail(email));
        if (curr > allOtherProfiles.size()){
            System.out.println("no more profiles!");
        } else {
            otherProfile = getProfileWithId(allOtherProfiles.get(this.curr));

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

    public ProfileFinderUI(String email){
        this.email = email;
        curr = 0;
        myProfile = getProfileWithEmail(email);
        DataFetchControl d = new DataFetchControl();
        allOtherProfiles = ConnectProfilesControl.gatherConnections(d.fetch_id_fromEmail(email));
        if (curr > allOtherProfiles.size()){
            System.out.println("nothing new here...");
        } else {
            otherProfile = getProfileWithId(allOtherProfiles.get(curr));

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
            String[] newLikesArray = newLikes.split(": ");
            List<String> newLikesList = Arrays.asList(newLikesArray);
            newLikesList.add((String)otherProfile[2]);
            newLikes = String.join(": ", newLikesList);
            myProfile[11] = newLikes;
            int myId = Integer.parseInt((String)myProfile[0]);
            DataSendControl c = new DataSendControl();
            c.send_toid(myId, myProfile);

            if (((String) myProfile[11]).contains((String) otherProfile[2])){
                JFrame matchFrame = new JFrame();
                matchFrame.setSize(100, 100);
                GridLayout matchLayout = new GridLayout(2, 1, 0,0);
                matchFrame.setLayout(matchLayout);
                JLabel statement = new JLabel("You got a match with " + (String)otherProfile[1] + "!");
                JLabel social = new JLabel("Their social media is: " + (String)otherProfile[10]);
                matchFrame.add(statement);
                matchFrame.add(social);

                matchFrame.setVisible(true);
            }

            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr, email);
        } else if (e.getSource() == passButton) {
            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr, email);
        }
    }
    public static void main (String[] args){
        new ProfileFinderUI("amelia@mail");
    }

}
