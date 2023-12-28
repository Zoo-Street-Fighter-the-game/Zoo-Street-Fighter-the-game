package gui_package;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel {

    private PanelDzienZasoby panelZasoby = new PanelDzienZasoby();
    private PanelDzienPracownicy panelPracownicy = new PanelDzienPracownicy();
    private PanelDzienWybiegi panelWybiegi = new PanelDzienWybiegi();
    private PanelDzienSklep panelSklep = new PanelDzienSklep();

    public PanelDzien() //konstruktor domyslny
    {
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
