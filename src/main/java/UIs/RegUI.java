package UIs;
import Controllers_Presenters.RegControl;
import Controllers_Presenters.UIController;
import Use_Cases.PictureHolder;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A "registration page" for this application, which allows user to enter their student email, password,
 * age, gender, postal code and social media, and also upload a picture as the icon.
 */
public class RegUI {
    private final JFrame frame = new JFrame("Registration page");
    private final JButton back_button = new JButton("Back to previous page");
    private final JButton reg_button = new JButton("Create account");
    private final JButton pic_button = new JButton("Select image");
    private final PictureHolder holder = new PictureHolder(frame, pic_button);
    private final JTextField email = new JTextField();
    private final JTextField pw = new JTextField();
    private final JTextField name = new JTextField();
    private final SpinnerModel age_range = new SpinnerNumberModel(18,1,100, 1);
    private final JSpinner age = new JSpinner(age_range);
    private final JTextField code = new JTextField();
    private final String[] genderOption = {"male", "female", "other"};
    private final JComboBox<String> gender = new JComboBox<>(genderOption);
    private final String[] platformOption = {"Instagram", "Snapchat", "Facebook", "Twitter"};
    private final JComboBox<String> platform = new JComboBox<>(platformOption);
    private final JTextField platform_info = new JTextField();

    /**
     * Build a frame as the registration page, which have
     * two buttons for back to previous page and submit their information, and
     * text boxes for user to enter their email, password, name, postal code, social media, and
     * option lists for user to choose their age, gender, social media platform.
     * User also have to upload an image as their icon.
     */
    public RegUI(){
        // set up the frame
        frame.setLayout(new GridLayout(9,2, 0,15));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        // Claim the rest of components (labels)
        JLabel emailLabel = new JLabel("Email: ");
        JLabel pwLabel = new JLabel("Password: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel codeLabel = new JLabel("Postal code (with a white space e.g. M5S 1A4): ");
        JLabel genderLabel = new JLabel("Gender: ");
        JLabel socialLabel = new JLabel("Social media (Please select a social media platform then enter your user name or url in the following box): ");
        JLabel picLabel = new JLabel("Upload an image as your icon: ");

        // add all components to frame
        frame.add(emailLabel);
        frame.add(email);
        frame.add(pwLabel);
        frame.add(pw);
        frame.add(nameLabel);
        frame.add(name);
        frame.add(ageLabel);
        frame.add(age);
        frame.add(genderLabel);
        frame.add(gender);
        frame.add(codeLabel);
        frame.add(code);
        frame.add(socialLabel);
        JPanel social_media = new JPanel(); // Claim a panel for asking social media
        social_media.setLayout(new GridLayout(2,1));
        social_media.add(platform);
        social_media.add(platform_info);
        frame.add(social_media); // Add the panel to frame
        frame.add(picLabel);
        holder.setLoader(); // add the upload picture button into the frame
        frame.add(back_button);
        frame.add(reg_button);
    }

    /**
     * Show the registration page to the user.
     */
    public void show(){
        setButtonReact();
        frame.setVisible(true); // make frame visible for user
    }

    /**
     * Set responds to different button press.
     * If register button is clicked, grab all the user's inputs and pass them to RegControl.
     * If back button is clicked, direct user back to the previous page(WelcomeUI).
     */
    private void setButtonReact(){
        reg_button.addActionListener(e -> {
            String[] lst_inputs = {Objects.requireNonNull(platform.getSelectedItem()).toString(), platform_info.getText(),
                    email.getText(), pw.getText(), name.getText(), age.getValue().toString(), Objects.requireNonNull(
                            gender.getSelectedItem()).toString(), code.getText()}; // a list of all user's inputs
            new RegControl(frame, lst_inputs, holder); // pass the inputs to the controller
        });
        back_button.addActionListener(e -> {
            frame.setVisible(false);
            new UIController().launchWelcomeUI();
        });
    }
}
