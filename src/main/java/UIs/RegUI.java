package UIs;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.RegControl;
import Controllers_Presenters.UIController;
import Use_Cases.PicHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegUI {
    JFrame frame = new JFrame("Registration page");
    JButton button = new JButton("Register");
    JLabel emailL = new JLabel("Email: ");
    JTextField email = new JTextField();
    JLabel pwL = new JLabel("Password: ");
    JTextField pw = new JTextField();
    JLabel nameL = new JLabel("Name: ");
    JTextField name = new JTextField();
    JLabel ageL = new JLabel("Age: ");
    JTextField age = new JTextField();
    JLabel postL = new JLabel("Postal code (with a whitespace e.g. M5S 1A4): ");
    JTextField post = new JTextField();
    String[] genders = {"male", "female", "other"};
    JLabel genderL = new JLabel("Gender: ");
    JComboBox gender = new JComboBox<String>(genders);
    JLabel picL = new JLabel("Upload your icon: ");
    JButton pic = new JButton("Select image");
    JButton back = new JButton("Back to Welcome page");

    public RegUI(){
        PicHolder loadFile = new PicHolder(frame, pic);

        frame.setLayout(new GridLayout(8,2));

        frame.add(emailL);
        frame.add(email);
        frame.add(pwL);
        frame.add(pw);
        frame.add(nameL);
        frame.add(name);
        frame.add(ageL);
        frame.add(age);
        frame.add(genderL);
        frame.add(gender);
        frame.add(postL);
        frame.add(post);
        frame.add(picL);
        loadFile.setLoader();
        frame.add(back);
        frame.add(button);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Give notification if there is missing input(s)
                if (email.getText().isEmpty() | pw.getText().isEmpty() | name.getText().isEmpty() |
                        age.getText().isEmpty()| gender.getSelectedItem().toString().isEmpty() | post.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Warning: \nmissing input(s), please try again");
                }

                try{
                    int id = new DataFetchControl().fetchLastId() + 1;
                    File outputfile = new File(String.format("saved_images/%s.jpg", id));
                    ImageIO.write((BufferedImage)loadFile.image, "jpg", outputfile);
                } catch(IOException error){
                    System.out.println(error);
                }
                new RegControl(email.getText(), pw.getText(), name.getText(), age.getText(), gender.getSelectedItem().toString(), post.getText(), frame);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WelcomeUI();
            }
        });
    }

    public static void main(String[] args) {
        new RegUI();
    }
}
