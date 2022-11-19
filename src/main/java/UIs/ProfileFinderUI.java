package UIs;

import javax.swing.*;
import java.awt.*;
import Controllers_Presenters.*;

public class ProfileFinderUI {
    // This class will show a profile on the screen based on the matching algorithm
    JFrame frame = new JFrame();
    GridLayout layout = new GridLayout(5, 2, 10, 20);
    JLabel nameLabel = new JLabel("Name: ");
    JLabel genderLabel = new JLabel("Gender: ");
    JLabel ageLabel = new JLabel("Age: ");
    JLabel bioLabel = new JLabel("Bio: ");

    JButton likeButton = new JButton("Like");
    JButton passButton = new JButton("Pass");

    JTextArea name = new JTextArea();
    JTextArea gender = new JTextArea();
    JTextArea age = new JTextArea();
    JTextArea bio = new JTextArea();

    public ProfileFinderUI(){
        frame.setSize(600, 600);

        layout.setRows(5);
        layout.setColumns(2);

        frame.setLayout(layout);

        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(ageLabel);
        frame.add(bioLabel);
        frame.add(likeButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new ProfileFinderUI();
    }

}
