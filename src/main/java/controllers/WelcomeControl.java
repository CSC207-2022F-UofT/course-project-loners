package controllers;

import javax.swing.*;

/**
 * Respond to the button press.
 */
public class WelcomeControl {
    /**
     * Directs the user to the corresponding pages(RegUI/LogUI) based on button press.
     *
     * @param buttonReg a button from WelcomeUI, direct user to RegUI if clicked
     * @param button_log a button from WelcomeUI, direct user to LogUI if clicked
     * @param WelFrame the frame of WelcomeUI
     */
    public WelcomeControl(JButton buttonReg, JButton button_log, JFrame WelFrame){
        button_log.addActionListener(e -> {
            if (e.getSource() == button_log) {
                WelFrame.setVisible(false);
                new UIController().launchLogUI();
            }});

        buttonReg.addActionListener(e -> {
            if (e.getSource() == buttonReg) {
                WelFrame.setVisible(false);
                new UIController().launchRegUI();
            }});
    }
}
