package Controllers_Presenters;

import UIs.LogUI;
import UIs.RegUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeControl {
    public WelcomeControl(JButton button_reg, JButton button_log, JFrame frame){
        button_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_log) {
                    LogUI logui = new LogUI();
                    frame.setVisible(false);
                    logui.setVisible(true);
                }}
        });

        button_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_reg) {
                    RegUI regui = new RegUI();
                    frame.setVisible(false);
                    regui.setVisible(true);
                }}
        });
    }
}
