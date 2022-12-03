package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import Controllers_Presenters.*;

/**
 * The ProfileFinderUI adds a window where the user can like or pass on other users.
 */
public class ProfileFinderUI implements ActionListener{
    /** the window to display*/
    // This class will show a profile on the screen based on the matching algorithm
    JFrame frame = new JFrame();
    /**The layout of the window to display*/
    GridLayout layout = new GridLayout(8, 2, 10, 5);
    /**Label the user's name*/
    JLabel nameLabel = new JLabel("Name: ");
    /**Label the user's gender*/
    JLabel genderLabel = new JLabel("Gender: ");
    /**Label the user's age*/
    JLabel ageLabel = new JLabel("Age: ");
    /**Label the user's bio*/
    JLabel bioLabel = new JLabel("Bio: ");
    /**Label the user's hobbies*/
    JLabel hobbiesLabel = new JLabel("Hobbies: ");
    /**Label the user's image*/
    JLabel imageLabel = new JLabel("Image: ");


    /**button to like*/
    JButton likeButton = new JButton("Like");
    /**button to pass*/
    JButton passButton = new JButton("Pass");
    /**button to go back*/
    JButton backButton = new JButton("Back");

    /**User's age*/
    JTextArea name;
    /**User's gender*/
    JTextArea gender;
    /**User's age*/
    JTextArea age;
    /**User's hobbies*/
    JTextArea hobbies;
    /**User's bio*/
    JTextArea bio;
    /**User's image*/
    JLabel image;

    /**Object list for the user's profile*/
    Object[] myProfile;
    /**Object list for the other user's profile*/
    Object[] otherProfile;
    // All other profile ids as strings
    /**Other ids as strings*/
    List<Integer> allOtherProfiles;
    /**current other profile*/
    int curr;
    /**the id of the profile*/
    String id;

    /**
     * Opens a new window displaying the profile to like or pass on
     * @param curr is the current id in terms of the otherprofiles list
     * @param id is my id
     */
    public ProfileFinderUI(int curr, String id){
        this.curr = curr;
        this.id = id;
        myProfile = getProfileWithId(Integer.parseInt(id));
        myProfile = (Object[]) myProfile[0];
        allOtherProfiles = ConnectProfilesControl.gatherConnections(Integer.parseInt(id));
        if (curr >= allOtherProfiles.size()){
            System.out.println("no more profiles!");
            JFrame matchFrame = new JFrame();
            matchFrame.setSize(300, 300);
            GridLayout matchLayout = new GridLayout(1, 1, 0,0);
            matchFrame.setLayout(matchLayout);
            JLabel statement = new JLabel("There are no more matches for you");
            matchFrame.add(statement);
            matchFrame.setLocationRelativeTo(null);
            new MainUI(Integer.parseInt((String)myProfile[0]), (String) myProfile[2]);
            matchFrame.setVisible(true);
        } else if (((String)myProfile[11]).contains(Integer.toString(allOtherProfiles.get(curr)))) {
            this.curr++;
            new ProfileFinderUI(this.curr, id);
        } else {
            otherProfile = getProfileWithId(allOtherProfiles.get(this.curr));

            BufferedImage theImage = (BufferedImage) otherProfile[1];
            otherProfile = (Object[]) otherProfile[0];

            name = new JTextArea((String) otherProfile[1]);
            age = new JTextArea((String) otherProfile[4]);
            bio = new JTextArea((String) otherProfile[5]);
            gender = new JTextArea((String) otherProfile[6]);
            hobbies = new JTextArea((String) otherProfile[9]);
            image = new JLabel(new ImageIcon(theImage));
            // Now we create the window


            frame.setSize(400, 800);

            layout.setRows(8);
            layout.setColumns(2);

            frame.setLayout(layout);

            likeButton.addActionListener(this);
            passButton.addActionListener(this);
            backButton.addActionListener(this);

            frame.add(nameLabel);
            frame.add(name);
            name.setEditable(false);
            frame.add(imageLabel);
            frame.add(image);
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
            frame.add(backButton);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    /**
     * Cleaner way to find a profile with an id
     * @param id the id that I want to find the other profile of
     * @return the corresponding profile to the param id
     */
    public Object[] getProfileWithId(int id){
        return DataFetchControl.fetch_fromid(id);
    }

    /**
     * Responds to button clicks
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == likeButton){
            // Use the LikesController to modufy the likes on a profile
            //Facade
            LikesController.modifyLikes((String) myProfile[11], Integer.parseInt((String) otherProfile[0]), myProfile);

            if (((String) myProfile[11]).contains((String) otherProfile[0]) &&
                    ((String)otherProfile[11]).contains((String)myProfile[0])){
                JFrame matchFrame = new JFrame();
                matchFrame.setSize(500, 300);
                GridLayout matchLayout = new GridLayout(2, 1, 0,0);
                matchFrame.setLayout(matchLayout);
                JLabel statement = new JLabel("You got a match with " + otherProfile[1] + "!");
                JLabel social = new JLabel("Their social media is: " + otherProfile[10]);
                matchFrame.add(statement);
                matchFrame.add(social);
                matchFrame.setLocationRelativeTo(null);

                matchFrame.setVisible(true);
            }

            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr, id);
        } else if (e.getSource() == passButton) {
            frame.setVisible(false);
            curr++;
            new ProfileFinderUI(curr, id);
        }else if(e.getSource() == backButton){
            frame.setVisible(false);
            new MainUI(Integer.parseInt((String)myProfile[0]), (String) myProfile[2]);
        }
    }
}
