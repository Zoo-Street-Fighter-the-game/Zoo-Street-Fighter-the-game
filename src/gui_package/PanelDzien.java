package gui_package;


import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel implements UpdateGUI {

    private final PanelDzienPracownicy panelPracownicy;
    private static int zmienna;



    public PanelDzien(Sklep sklep) //konstruktor domyslny
    {
        sklep.dodajObsewatoraGUI(this);

        PanelDzienZasoby panelZasoby = new PanelDzienZasoby(sklep);
        PanelDzienWybiegi panelWybiegi = new PanelDzienWybiegi(sklep);
        PanelDzienSklep panelSklep = new PanelDzienSklep(sklep);
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

         if(zmienna!=1) {
            sklep.wczytajGre2();
        }

    }


    //GETTERY

    public PanelDzienPracownicy getPanelPracownicy() {
        return panelPracownicy;
    }


    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
