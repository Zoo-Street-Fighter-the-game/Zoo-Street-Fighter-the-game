package gui_package;

import javax.swing.JFrame;
import java.awt.Dimension;

public class MyFrame extends JFrame{

    PanelDzien panelDzien = new PanelDzien();
    MyFrame()
    {
        this.setTitle("Tytul roboczy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        this.add(panelDzien);
    }

}
