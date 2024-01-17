package gui_package;


import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel implements UpdateGUI {

    private final PanelDzienZasoby panelZasoby;
    private final PanelDzienPracownicy panelPracownicy;
    private final PanelDzienWybiegi panelWybiegi;
    private final PanelDzienSklep panelSklep;
    private static int zmienna;


    public PanelDzien(Sklep sklep) //konstruktor domyslny
    {
        sklep.dodajObsewatoraGUI(this);

        panelZasoby = new PanelDzienZasoby(sklep);
        panelWybiegi = new PanelDzienWybiegi(sklep);
        panelSklep = new PanelDzienSklep(sklep);
        panelPracownicy = new PanelDzienPracownicy(sklep);

        panelPracownicy.getListaObserwatorow().add(panelSklep);
        panelPracownicy.getListaObserwatorow().add(panelWybiegi.getWybiegBezdomni());

        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        this.add(panelWybiegi, BorderLayout.CENTER);
        this.add(panelPracownicy, BorderLayout.EAST);
        this.add(panelZasoby, BorderLayout.NORTH);
        this.add(panelSklep, BorderLayout.WEST);
        zmienna++;
        if (zmienna == 1) {
            //.wczytajGre();
        } else {
            sklep.wczytajGre2();

        }

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
