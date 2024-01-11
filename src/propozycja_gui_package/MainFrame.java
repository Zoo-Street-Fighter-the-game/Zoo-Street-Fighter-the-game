package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private PanelNoc panelNoc;

    public MainFrame(DzienneZoo instance) {
        panelNoc = new PanelNoc(instance);
        this.setTitle("Zoo Street Fighter The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(panelNoc);
        this.setVisible(true);
    }
}
