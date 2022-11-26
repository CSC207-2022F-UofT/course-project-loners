package Controllers_Presenters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeControl {
    /**
     * The constructor of WelcomeControl, which
     * @param button_reg
     * @param button_log
     * @param frame
     */
    public WelcomeControl(JButton button_reg, JButton button_log, JFrame frame){
        button_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_log) {
                    frame.setVisible(false);
                    new UIController().launchLogUI();
                }}
        });

        button_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_reg) {
                    frame.setVisible(false);
                    new UIController().launchRegUI();
                }}
        });
    }
}
