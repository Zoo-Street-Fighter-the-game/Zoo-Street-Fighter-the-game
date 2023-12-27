package gui_package;

import javax.swing.*;
import java.awt.*;

public class PanelDzien extends JPanel {

    PanelDzienZasoby panelZasoby = new PanelDzienZasoby();
    PanelDzienPracownicy panelPracownicy = new PanelDzienPracownicy();
    PanelDzienWybiegi panelWybiegi = new PanelDzienWybiegi();
    PanelDzienSklep panelSklep = new PanelDzienSklep();

    PanelDzien()
    {
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        this.add(panelWybiegi, BorderLayout.CENTER);
        this.add(panelPracownicy, BorderLayout.EAST);
        this.add(panelZasoby, BorderLayout.NORTH);
        this.add(panelSklep, BorderLayout.WEST);


    }

}
