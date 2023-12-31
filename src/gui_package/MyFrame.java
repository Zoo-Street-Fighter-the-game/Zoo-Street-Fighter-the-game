package gui_package;

import DzienneZooPakiet.DzienneZoo;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    private PanelDzien panelDzien;
    public MyFrame(Sklep sklepik) //konstruktor domyslny
    {
        panelDzien = new PanelDzien(sklepik);
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
