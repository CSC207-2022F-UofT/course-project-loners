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
        if (!Authenticator.emailExists(email)){
            JOptionPane.showMessageDialog(null,
                    "Email is not registered. Head to the Register page to join us!");
        } else{
            if (Authenticator.emailMatchPassword(email, password)){
                JOptionPane.showMessageDialog(null, "Login successful!");
                logframe.setVisible(false);
                new UIController(email).launchMainUI();
            } else{
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        }
    }

    public String getEmail(String email, String password){
        if (Authenticator.emailMatchPassword(email, password)){
            return email;
        } return null;
    }

    public static void main(String[] args) {
        new LogUI();
    }
}
