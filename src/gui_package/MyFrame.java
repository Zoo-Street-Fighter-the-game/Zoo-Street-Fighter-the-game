package gui_package;

import pakiet_sklep.Sklep;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    public MyFrame(Sklep sklep) //konstruktor domyslny
    {
        PanelDzien panelDzien = new PanelDzien(sklep);
        this.setTitle("Zoo Street Figther The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        this.add(panelDzien);
    }

}
