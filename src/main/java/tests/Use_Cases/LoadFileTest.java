package tests.Use_Cases;

import Use_Cases.LoadFile;
import org.junit.Test;

import javax.swing.*;

public class LoadFileTest {
    JFrame f = new JFrame();
    JButton b = new JButton();
    @Test
    public void testActionPerformed(){
        LoadFile loadFile = new LoadFile(f, b, 2);
        loadFile.setLoader();
        f.setVisible(true);
    }
}
