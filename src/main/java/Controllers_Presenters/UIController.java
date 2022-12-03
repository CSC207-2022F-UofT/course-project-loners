package Controllers_Presenters;

import UIs.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UIController {
    private int id;
    private Object[] data;

    public UIController() {}

    public UIController(int id){
        this.id = id;
        this.data = DataFetchControl.fetch_fromid(id);
    }

    public UIController(String email){ this.id = DataFetchControl.fetch_id_fromEmail(email); }

    public void launchMyProfileUI(){ new MyProfileUI(id); }
    public static void launchWelcomeUI() { WelcomeUI welUI = new WelcomeUI(); welUI.show(); }
    public void launchLogUI() { new LogUI(); }
    public void launchRegUI() { RegUI regUI = new RegUI(); regUI.show(); }
    public void launchMainUI() { MainUI mainUI = new MainUI(id); mainUI.show(); }
    public void launchEditPreferencesUI() {EditPreferencesUI.buildUI(id); }
    public void launchEditProfileUI(){
        new EditProfileUI(this.id);
    }
    public void launchProfileFinderUI(){ new ProfileFinderUI(0, Integer.toString(id)); }

    public boolean checkHasPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    public static void makeFrameFullSize(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }

    public static void addBackButton(JFrame frame, String targetUI){
        // If back button is clicked, direct user back to the previous page(WelcomeUI).
        JButton backButton = new JButton("Back to previous page");
        frame.add(backButton);
        backButton.addActionListener(e -> {
            frame.setVisible(false);
            if (Objects.equals(targetUI, "WelcomeUI")){
                launchWelcomeUI();
            }
        });
    }


}
