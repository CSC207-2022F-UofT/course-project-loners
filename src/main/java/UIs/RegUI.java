package UIs;
import Controllers_Presenters.RegControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JLabel postL = new JLabel("Postal code (e.g. M5S 1A4): ");
    JTextField post = new JTextField();
    String[] genders = {"male", "female", "other"};
    JLabel genderL = new JLabel("Gender: ");
    JComboBox gender = new JComboBox<String>(genders);
    JLabel picL = new JLabel("Upload your icon: ");
    JButton pic = new JButton("Select");
    public RegUI(){
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//        frame.add(picL);
//        frame.add(pic);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(age.getText());
                new RegControl(email.getText(), pw.getText(), name.getText(), age.getText(), gender.getSelectedItem().toString(), post.getText(), frame);
            }
        });

//        pic.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO: finish this for saving the picture
//            }
//        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        RegUI ui = new RegUI();
        ui.setVisible(true);
    }
}
