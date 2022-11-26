package Controllers_Presenters;

import javax.swing.*;

/**
 * Respond to the button press.
 */
public class WelcomeControl {
    /**
     * The constructor of WelcomeControl.
     * Respond to the button press and directs the user to the corresponding pages(RegUI/LogUI).
     *
     * @param button_reg a button from WelcomeUI, direct user to RegUI if clicked
     * @param button_log a button from WelcomeUI, direct user to LogUI if clicked
     * @param WelFrame the frame of WelcomeUI
     */
    public WelcomeControl(JButton button_reg, JButton button_log, JFrame WelFrame){
        button_log.addActionListener(e -> {
            if (e.getSource() == button_log) {
                WelFrame.setVisible(false);
                new UIController().launchLogUI();
            }});

        button_reg.addActionListener(e -> {
            if (e.getSource() == button_reg) {
                WelFrame.setVisible(false);
                new UIController().launchRegUI();
            }});
    }
}
