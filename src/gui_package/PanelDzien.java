package gui_package;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel {

    private PanelDzienZasoby panelZasoby;
    private PanelDzienPracownicy panelPracownicy;
    private PanelDzienWybiegi panelWybiegi = new PanelDzienWybiegi();
    private PanelDzienSklep panelSklep;


    public PanelDzien(DzienneZoo zoo, Sklep sklepik) //konstruktor domyslny
    {
        panelZasoby = new PanelDzienZasoby(zoo);
        sklepik.dodajpanel(panelZasoby);
        panelPracownicy = new PanelDzienPracownicy(zoo);
        panelSklep = new PanelDzienSklep(zoo, sklepik);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        this.add(panelWybiegi, BorderLayout.CENTER);
        this.add(panelPracownicy, BorderLayout.EAST);
        this.add(panelZasoby, BorderLayout.NORTH);
        this.add(panelSklep, BorderLayout.WEST);

    }

    //GETTERY
    public PanelDzienZasoby getPanelZasoby() {
        return panelZasoby;
    }

    public PanelDzienPracownicy getPanelPracownicy() {
        return panelPracownicy;
    }

    public PanelDzienWybiegi getPanelWybiegi() {
        return panelWybiegi;
    }

    public PanelDzienSklep getPanelSklep() {
        return panelSklep;
    }
}
