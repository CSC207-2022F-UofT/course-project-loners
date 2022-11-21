package Controllers_Presenters;

import UIs.MainUI;
import UIs.RegUI;
import Use_Cases.Authenticator;
import UIs.LogUI;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LogController{
    public Authenticator authenticator = new Authenticator();

    public LogController(String email, String password, JFrame logframe) {
        if (!Authenticator.email_exists(email)){
            JOptionPane.showMessageDialog(null,
                    "Email is not registered. Head to the Register page to join us!");
        } else{
            if (Authenticator.email_match_password(email, password)){
                JOptionPane.showMessageDialog(null, "Login successful!");
                logframe.setVisible(false);
                // Uncomment the follwowing 2 lines after MainUI is implemented
                 MainUI mainui = new MainUI(email);
                 mainui.setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        }
    }

    public String get_email(String email, String password){
        if (Authenticator.email_match_password(email, password)){
            return email;
        } return null;
    }

    public static void main(String[] args) {
        new LogUI();
    }
}
