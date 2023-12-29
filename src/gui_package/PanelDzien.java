package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel implements UpdateGUI {

    private PanelDzienZasoby panelZasoby;
    private PanelDzienPracownicy panelPracownicy;
    private PanelDzienWybiegi panelWybiegi;
    private PanelDzienSklep panelSklep;


    public PanelDzien(DzienneZoo zoo, Sklep sklep) //konstruktor domyslny
    {
        sklep.dodajObsewatoraGUI(this);

        panelZasoby = new PanelDzienZasoby(zoo, sklep);
        panelWybiegi = new PanelDzienWybiegi(zoo, sklep, new Wybieg_podstawowy(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.DUZY));
        panelPracownicy = new PanelDzienPracownicy(zoo, sklep);
        panelSklep = new PanelDzienSklep(zoo, sklep);
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

    @Override
    public void UpdateGUI() {
        this.repaint();
        this.revalidate();
    }
}
