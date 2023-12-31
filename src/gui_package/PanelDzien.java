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


    public PanelDzien(Sklep sklep) //konstruktor domyslny
    {
        sklep.dodajObsewatoraGUI(this);

        panelZasoby = new PanelDzienZasoby(sklep);
        panelWybiegi = new PanelDzienWybiegi(sklep);
        panelSklep = new PanelDzienSklep(sklep);
        panelPracownicy = new PanelDzienPracownicy(sklep);

        panelPracownicy.getListaObserwatorow().add(panelSklep);

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
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
