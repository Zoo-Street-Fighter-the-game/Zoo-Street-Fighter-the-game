package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(DzienneZoo instance) {
        PanelNoc panelNoc = new PanelNoc(instance);
        this.setTitle("Zoo Street Fighter The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(panelNoc);
        this.setVisible(true);
    }
}
