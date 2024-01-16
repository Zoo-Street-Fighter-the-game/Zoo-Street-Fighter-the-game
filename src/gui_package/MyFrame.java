package gui_package;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    private final PanelDzien panelDzien;

    public MyFrame(Sklep sklep) //konstruktor domyslny
    {
        panelDzien = new PanelDzien(sklep);
        this.setTitle("Zoo Street Figther The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        this.add(panelDzien);
    }
    //GETTERY
    public PanelDzien getPanelDzien() {
        return panelDzien;
    }


}
